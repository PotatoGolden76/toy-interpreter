package domain.statements;

import domain.InterpreterException;
import domain.ProgramState;

public interface IStatement {
    ProgramState execute(ProgramState state) throws InterpreterException;
}
