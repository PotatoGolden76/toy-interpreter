package domain;

import domain.statements.IStatement;
import domain.structures.*;

public class ProgramState {
    final Stack executionStack;
    final Queue output = new Queue();
    final SymbolDictionary symbolTable = new SymbolDictionary();

    final FileDictionary fileTable = new FileDictionary();

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
                "\n" + output +
                "\n" + symbolTable +
                "\n" + fileTable;
    }
}
