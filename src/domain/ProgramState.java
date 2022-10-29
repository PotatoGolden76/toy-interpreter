package domain;

import domain.statements.IStatement;

public class ProgramState {
    IStack<IStatement> executionStack;

    public IStack<IStatement> getStack() {
        return this.executionStack;
    }


}
