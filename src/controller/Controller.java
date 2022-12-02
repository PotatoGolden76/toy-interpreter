package controller;

import domain.exceptions.ExpressionException;
import domain.exceptions.InterpreterException;
import domain.ProgramState;
import domain.exceptions.StatementException;
import domain.exceptions.ValueException;
import domain.statements.IStatement;
import domain.structures.Stack;
import domain.structures.SymbolDictionary;
import domain.types.IType;
import domain.types.ReferenceType;
import domain.values.IValue;
import domain.values.ReferenceValue;
import repository.Repository;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Controller {
    Repository r;
    boolean step;

    public Controller(Stack initialStack, boolean step, String log) throws IOException {
        this.r = new Repository(new ProgramState(initialStack), log);
        this.step = step;
    }

    public void run() throws InterpreterException, IOException, ExpressionException {
        if (this.step) {
            this.stepByStep();
        } else {
            this.fullExecution();
        }
    }

    private void fullExecution() throws InterpreterException, IOException, ExpressionException {
        while (!this.r.getState().getStack().isEmpty()) {
            stepByStep();
        }
    }

    HashMap<Integer, IValue> runGC(SymbolDictionary symbols, HashMap<Integer, IValue> heap) {
        HashMap<Integer, IValue> newHeap = new HashMap<>();

        for (IValue val : Collections.list(symbols.elements())) {
            if (val instanceof ReferenceValue) {
                int address = ((ReferenceValue) val).getAddress();
                if (heap.containsKey(address)) {
                    newHeap.put(address, heap.get(address));
                }

                if(val.getType() instanceof ReferenceType) {
                    if(heap.containsKey(address)) {
                        IValue value = heap.get(address);
                        while(value instanceof ReferenceValue) {
                            int address2 = ((ReferenceValue) value).getAddress();
                            if(heap.containsKey(address2)) {
                                newHeap.put(address2, heap.get(address2));
                            }
                            value = heap.get(address2);
                        }
                    }
                }
            }
        }
        return newHeap;
    }

    private void stepByStep() throws InterpreterException, IOException, ExpressionException {


        Stack currentStack = (Stack) this.r.getState().getStack();
        if (currentStack.isEmpty())
            throw new InterpreterException("Nothing to execute.");

        IStatement currentInstruction = currentStack.pop();
        try {
            this.r.setState(currentInstruction.execute(this.r.getState()));
        } catch (StatementException | ValueException e) {
            throw new RuntimeException(e);
        }

        System.out.println(this.r.toString());

        this.r.getState().getHeap().setContent(runGC(this.r.getState().getSymbolTable(), this.r.getState().getHeap().getContent()));
        this.r.logProgramState();
        if (this.step) {
            new Scanner(System.in).next();
            this.stepByStep();
        }

    }

    public Repository getRepository() {
        return r;
    }
}
