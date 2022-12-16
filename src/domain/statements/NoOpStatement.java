package domain.statements;

import domain.ProgramState;

public class NoOpStatement implements IStatement{
    @Override
    public ProgramState execute(ProgramState state) {
        return null;
    }

    @Override
    public String toString() {
        return "[NoOp]";
    }
}
