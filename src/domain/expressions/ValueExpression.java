package domain.expressions;

import domain.structures.Heap;
import domain.structures.IDictionary;
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

    @Override
    public String toString() {
        return  this.v.toString();
    }
}
