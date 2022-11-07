package domain.expressions;

import domain.structures.IDictionary;
import domain.types.IValue;

public class VariableExpression implements IExpression{
    String id;

    public VariableExpression(String variable) {
        this.id = variable;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable) {
        return symbolTable.get(id);
    }

    @Override
    public String toString() {
        return  this.id;
    }
}
