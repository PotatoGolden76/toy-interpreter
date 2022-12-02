package domain.expressions;

import domain.exceptions.ExpressionException;
import domain.exceptions.StatementException;
import domain.exceptions.ValueException;
import domain.structures.Heap;
import domain.structures.IDictionary;
import domain.values.IValue;
import domain.values.ReferenceValue;

public class ReadHeapExpression implements IExpression{
    private IExpression expression;

    public ReadHeapExpression(IExpression exp) {
        this.expression = exp;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable, Heap heap) throws ExpressionException, ValueException, StatementException {
        IValue value = expression.evaluate(symbolTable, heap);

        if(!(value instanceof ReferenceValue)) {
            throw new StatementException("The expression is not a reference value!");
        }

        if(!heap.isDefined(((ReferenceValue) value).getAddress())) {
            throw new StatementException("The address is not in the heap!");
        }

        return heap.get(((ReferenceValue) value).getAddress());
    }

    @Override
    public String toString() {
        return "readHeap(" + expression + ")";
    }
}
