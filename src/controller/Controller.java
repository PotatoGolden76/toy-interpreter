package controller;

import domain.InterpreterException;
import domain.ProgramState;
import domain.statements.IStatement;
import domain.structures.Stack;
import repository.Repository;

import java.io.IOException;
import java.util.Scanner;

public class Controller {
    Repository r;
    boolean step = false;

    public Controller(Stack initialStack, boolean step, String log) throws IOException {
        this.r = new Repository(new ProgramState(initialStack), log);
        this.step = step;
    }

    public void run() throws InterpreterException, IOException {
        if (this.step) {
            this.stepByStep();
        } else {
            this.fullExecution();
        }
    }

    private void fullExecution() throws InterpreterException, IOException {
        while(!this.r.getState().getStack().isEmpty()) {
            stepByStep();
        }
    }

    private void stepByStep() throws InterpreterException, IOException {


        Stack currentStack = (Stack) this.r.getState().getStack();
        if(currentStack.isEmpty())
            throw new InterpreterException("Nothing to execute.");

        IStatement currentInstruction = currentStack.pop();
        this.r.setState(currentInstruction.execute(this.r.getState()));

        System.out.println(this.r.toString());

        this.r.logProgramState();
        if(this.step) {
            new Scanner(System.in).next();
            this.stepByStep();
        }

    }

    public Repository getRepository() {
        return r;
    }
}
