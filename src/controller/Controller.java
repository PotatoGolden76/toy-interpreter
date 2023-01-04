package controller;

import domain.exceptions.*;
import domain.ProgramState;
import domain.statements.IStatement;
import domain.structures.Stack;
import domain.structures.SymbolDictionary;
import domain.types.IType;
import domain.types.ReferenceType;
import domain.values.IValue;
import domain.values.ReferenceValue;
import repository.Repository;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Controller {
    Repository r;
    boolean step;

    ExecutorService executor;

    public Controller(Stack initialStack, boolean step, String log) throws IOException, TypeException {
        this.r = new Repository(new ProgramState(initialStack), log);
        this.step = step;
    }

    public Controller(Repository clone, boolean step) {
        this.r = clone;
        this.step = step;
    }


    public Controller clone() {
        return new Controller(this.r.clone(), this.step);
    }

    public void run() throws InterpreterException, IOException, ExpressionException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> programList = removeCompletedPrograms(r.getState());
        while (programList.size() > 0) {
            conservativeGC(programList);
            allStep(programList);
            programList = removeCompletedPrograms(r.getState());
        }
        executor.shutdownNow();
        r.setState(programList);
    }

    private List<ProgramState> removeCompletedPrograms(List<ProgramState> state) {
        return state.stream().filter(Predicate.not(ProgramState::isCompleted)).collect(Collectors.toList());
    }

    public void allStep(List<ProgramState> programs) throws InterruptedException {
        programs.forEach(p -> {
            try {
                r.logProgramState(p);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        List<Callable<ProgramState>> callList = programs.stream()
                .map((ProgramState p) -> (Callable<ProgramState>) (p::oneStep))
                .toList();

        List<ProgramState> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(Objects::nonNull)
                .toList();

        programs.addAll(newPrgList);

        programs.forEach(p -> {
            try {
                r.logProgramState(p);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        this.r.setState(programs);
    }

    void conservativeGC(List<ProgramState> programs) {
        List<Integer> addresses = new ArrayList<>();
        for (ProgramState p : programs) {
            for (IValue v : Collections.list(p.getSymbolTable().elements())) {
                if (v instanceof ReferenceValue) {
                    addresses.add(((ReferenceValue) v).getAddress());
                }
            }

            for (IValue v : p.getHeap().getValues()) {
                if (v instanceof ReferenceValue) {
                    addresses.add(((ReferenceValue) v).getAddress());
                    if (p.getHeap().isDefined(((ReferenceValue) v).getAddress())) {
                        IValue value = p.getHeap().get(((ReferenceValue) v).getAddress());
                        while (value instanceof ReferenceValue) {
                            int address2 = ((ReferenceValue) value).getAddress();
                            if (!addresses.contains(address2)) {
                                addresses.add(address2);
                            }
                            value = p.getHeap().get(address2);
                        }
                    }
                }
            }
        }

        programs.get(0).getHeap().setContent(programs.get(0).getHeap().getContent().entrySet().stream()
                .filter(e -> addresses.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

    }

    public Repository getRepository() {
        return r;
    }

    public boolean getStep() {
        return step;
    }

    public String getLog() {
        return r.getLog();
    }
}
