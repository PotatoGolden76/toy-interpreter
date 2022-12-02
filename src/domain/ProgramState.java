package domain;

import domain.statements.IStatement;
import domain.structures.*;

public class ProgramState {
    final Stack executionStack;
    final Queue output = new Queue();
    final SymbolDictionary symbolTable = new SymbolDictionary();

    final FileDictionary fileTable = new FileDictionary();

    final Heap heap = new Heap();

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

    public Heap getHeap() {
        return heap;
    }

    public Queue getOutput() {
        return output;
    }

    @Override
    public String toString() {
        return ">>> Current State: \n" + executionStack.toString() +
                "\n" + output +
                "\n" + symbolTable +
                "\n" + fileTable +
                "\n" + heap;
    }
}
