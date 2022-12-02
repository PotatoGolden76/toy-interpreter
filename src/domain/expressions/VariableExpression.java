package domain.expressions;

import domain.structures.Heap;
import domain.structures.IDictionary;
import domain.values.IValue;

public class VariableExpression implements IExpression{
    final String id;

    public VariableExpression(String variable) {
        this.id = variable;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable, Heap heap) {
        return symbolTable.get(id);
    }

    @Override
    public String toString() {
        return  this.id;
    }
}
