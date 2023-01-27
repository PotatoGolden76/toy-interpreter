package domain.statements;

import domain.ProgramState;
import domain.exceptions.*;
import domain.structures.IDictionary;
import domain.types.IType;

import java.io.IOException;

public interface IStatement {
    ProgramState execute(ProgramState state) throws StatementException, IOException, ValueException, ExpressionException, TypeException, InterpreterException;

    //Type Check
    IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws TypeException;
}
