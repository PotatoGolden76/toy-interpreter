package domain.statements;

import domain.ProgramState;

public class NoOpStatement implements IStatement{
    @Override
    public ProgramState execute(ProgramState state) {
        return state;
    }

    @Override
    public String toString() {
        return "[NoOp]";
    }
}
