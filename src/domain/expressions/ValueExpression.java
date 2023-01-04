package domain.expressions;

import domain.structures.Heap;
import domain.structures.IDictionary;
import domain.types.IType;
import domain.values.IValue;

public class ValueExpression implements IExpression {
    final IValue v;

    public ValueExpression(IValue value) {
        this.v = value;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable, Heap heap) {
        return this.v;
    }

    //Type check
    public IType typeCheck(IDictionary<String, IType> typeEnvironment) {
        return this.v.getType();
    }

    @Override
    public String toString() {
        return  this.v.toString();
    }
}
