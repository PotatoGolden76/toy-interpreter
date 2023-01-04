package domain.statements;

import domain.ProgramState;
import domain.exceptions.ExpressionException;
import domain.exceptions.StatementException;
import domain.exceptions.TypeException;
import domain.exceptions.ValueException;
import domain.structures.IDictionary;
import domain.types.IType;

import java.io.IOException;

public interface IStatement {
    ProgramState execute(ProgramState state) throws StatementException, IOException, ValueException, ExpressionException, TypeException;

    //Type Check
    IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws TypeException;
}
