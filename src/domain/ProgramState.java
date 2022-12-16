package domain;

import domain.exceptions.ExpressionException;
import domain.exceptions.InterpreterException;
import domain.exceptions.StatementException;
import domain.exceptions.ValueException;
import domain.statements.IStatement;
import domain.structures.*;

import java.io.IOException;

public class ProgramState {

    private int id;

    public static int nextId = 0;
    final Stack executionStack;
    Queue output = new Queue();
    SymbolDictionary symbolTable = new SymbolDictionary();

    FileDictionary fileTable = new FileDictionary();

    Heap heap = new Heap();

    public ProgramState(Stack initialStack) {
        this.executionStack = initialStack;
        this.id = nextId;
        nextId++;
    }

    public ProgramState(Stack initialStack, SymbolDictionary clone, Queue output, FileDictionary fileTable, Heap heap, IStatement statement) {
        this.executionStack = initialStack;
        this.symbolTable = clone;
        this.output = output;
        this.fileTable = fileTable;
        this.heap = heap;
        this.executionStack.push(statement);
        this.id = nextId;
        nextId++;
    }

    public synchronized void setID(int id) {
        this.id = id;
    }

    public synchronized int getID() {
        return this.id;
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

    public boolean isCompleted() {
        return this.executionStack.isEmpty();
    }

    public ProgramState oneStep() throws InterpreterException, StatementException, ValueException, IOException, ExpressionException {
        if (this.isCompleted()) {
            throw new InterpreterException("Stack is empty");
        }
        IStatement currentStatement = this.executionStack.pop();
        return currentStatement.execute(this);
    }

    @Override
    public String toString() {
        return "ID: " + this.getID() + "\n" +
                ">>> Current State: \n" + executionStack.toString() +
                "\n" + output +
                "\n" + symbolTable +
                "\n" + fileTable +
                "\n" + heap;
    }
}
