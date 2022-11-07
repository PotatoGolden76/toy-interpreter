package domain.statements;

import domain.InterpreterException;
import domain.ProgramState;

public class NoOpStatement implements IStatement{
    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        return state;
    }

    @Override
    public String toString() {
        return "[NoOp]";
    }
}
