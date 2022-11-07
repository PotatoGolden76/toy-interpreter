package domain.expressions;

import domain.InterpreterException;
import domain.structures.IDictionary;
import domain.types.IValue;

public class ValueExpression implements IExpression {
    IValue v;

    public ValueExpression(IValue value) {
        this.v = value;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable) {
        return this.v;
    }

    @Override
    public String toString() {
        return  this.v.toString();
    }
}
