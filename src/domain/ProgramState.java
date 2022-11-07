package domain;

import domain.statements.IStatement;
import domain.structures.Dictionary;
import domain.structures.IStack;
import domain.structures.Queue;
import domain.structures.Stack;

public class ProgramState {
    Stack executionStack;
    Queue output = new Queue();
    Dictionary symbolTable = new Dictionary();

    public ProgramState(Stack initialStack) {
        this.executionStack = initialStack;
    }

    public IStack<IStatement> getStack() {
        return this.executionStack;
    }

    public Dictionary getSymbolTable() {
        return this.symbolTable;
    }

    public Queue getOutput() {
        return output;
    }

    @Override
    public String toString() {
        return ">>> Current State: \n" + executionStack.toString() +
                "\n" + output.toString() +
                "\n" + symbolTable.toString();
    }
}
