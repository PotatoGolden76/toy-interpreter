package domain.statements;

import domain.InterpreterException;
import domain.ProgramState;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IStatement {
    ProgramState execute(ProgramState state) throws InterpreterException, IOException;
}
