package domain.expressions;

import domain.exceptions.ExpressionException;
import domain.exceptions.StatementException;
import domain.exceptions.TypeException;
import domain.exceptions.ValueException;
import domain.structures.Heap;
import domain.structures.IDictionary;
import domain.types.IType;
import domain.values.IValue;

public interface IExpression {

    IValue evaluate(IDictionary<String, IValue> symbolTable, Heap heap) throws ExpressionException, ValueException, StatementException;

    //Type check
    IType typeCheck(IDictionary<String, IType> typeEnvironment) throws TypeException;
}
