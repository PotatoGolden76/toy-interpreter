package domain.statements;

import domain.ProgramState;
import domain.exceptions.ExpressionException;
import domain.exceptions.StatementException;
import domain.exceptions.ValueException;

import java.io.IOException;

public interface IStatement {
    ProgramState execute(ProgramState state) throws StatementException, IOException, ValueException, ExpressionException;
}
