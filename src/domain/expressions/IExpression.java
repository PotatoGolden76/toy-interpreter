package domain.expressions;

import domain.exceptions.ExpressionException;
import domain.exceptions.ValueException;
import domain.structures.IDictionary;
import domain.values.IValue;

public interface IExpression {

    IValue evaluate(IDictionary<String, IValue> symbolTable) throws ExpressionException, ValueException;
}
