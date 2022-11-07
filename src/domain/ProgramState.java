package domain;

import domain.statements.IStatement;
import domain.structures.Dictionary;
import domain.structures.IStack;
import domain.structures.Stack;

public class ProgramState {
    Stack executionStack;


    Dictionary symbolTable;

    public IStack<IStatement> getStack() {
        return this.executionStack;
    }

    public Dictionary getSymbolTable() {
        return this.symbolTable;
    }
}
