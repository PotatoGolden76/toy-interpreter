package domain.expressions;

import domain.exceptions.ExpressionException;
import domain.exceptions.StatementException;
import domain.exceptions.TypeException;
import domain.exceptions.ValueException;
import domain.structures.Heap;
import domain.structures.IDictionary;
import domain.types.BooleanType;
import domain.types.IType;
import domain.values.BooleanValue;
import domain.values.IValue;

@SuppressWarnings("unused")
public class LogicExpression implements IExpression {

    final IExpression e1;
    final IExpression e2;
    final int operator;

    public LogicExpression(IExpression e1, IExpression e2, int operator) {
        this.e1 = e1;
        this.e2 = e2;
        this.operator = operator;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable, Heap heap) throws ExpressionException, ValueException, StatementException {
        IValue v1 = this.e1.evaluate(symbolTable, heap);
        IValue v2 = this.e2.evaluate(symbolTable, heap);

        if (v1.getType().equals(new BooleanType())) {
            throw new ExpressionException("Operand " + v1 + " is not an integer.");
        }
        if (v2.getType().equals(new BooleanType())) {
            throw new ExpressionException("Operand " + v2 + " is not an integer.");
        }

        BooleanValue b1 = (BooleanValue) v1;
        BooleanValue b2 = (BooleanValue) v2;

        return switch (operator) {
            case 1 -> new BooleanValue(b1.getValue() && b2.getValue());
            case 2 -> new BooleanValue(b1.getValue() || b2.getValue());
            default -> null;
        };
    }

    //Type Check
    @Override
    public BooleanType typeCheck(IDictionary<String, IType> typeEnvironment) throws TypeException {
        IType type1, type2;
        type1 = e1.typeCheck(typeEnvironment);
        type2 = e2.typeCheck(typeEnvironment);
        if (type1.equals(new BooleanType())) {
            throw new TypeException("First operand is not a boolean.");
        }
        if (type2.equals(new BooleanType())) {
            throw new TypeException("Second operand is not a boolean.");
        }
        return new BooleanType();
    }

    public String toString() {
        return switch (this.operator) {
            case 1 -> this.e1.toString() + " AND " + this.e2.toString();
            case 2 -> this.e1.toString() + " OR " + this.e2.toString();

            default -> "";
        };
    }
}
