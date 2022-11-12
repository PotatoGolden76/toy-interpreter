package domain.expressions;

import domain.InterpreterException;
import domain.structures.IDictionary;
import domain.values.IValue;

public interface IExpression {

    public IValue evaluate(IDictionary<String, IValue> symbolTable) throws InterpreterException;
}
