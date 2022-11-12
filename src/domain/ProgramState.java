package domain;

import domain.statements.IStatement;
import domain.structures.*;

public class ProgramState {
    Stack executionStack;
    Queue output = new Queue();
    SymbolDictionary symbolTable = new SymbolDictionary();

    FileDictionary fileTable = new FileDictionary();

    public ProgramState(Stack initialStack) {
        this.executionStack = initialStack;
    }

    public IStack<IStatement> getStack() {
        return this.executionStack;
    }

    public SymbolDictionary getSymbolTable() {
        return this.symbolTable;
    }

    public FileDictionary getFileTable() {
        return fileTable;
    }

    public Queue getOutput() {
        return output;
    }

    @Override
    public String toString() {
        return ">>> Current State: \n" + executionStack.toString() +
                "\n" + output.toString() +
                "\n" + symbolTable.toString() +
                "\n" + fileTable.toString();
    }
}
