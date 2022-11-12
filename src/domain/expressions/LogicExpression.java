package domain.expressions;

import domain.InterpreterException;
import domain.structures.IDictionary;
import domain.types.BooleanType;
import domain.values.BooleanValue;
import domain.values.IValue;

public class LogicExpression implements IExpression {

    IExpression e1, e2;
    int operator;

    public LogicExpression(IExpression e1, IExpression e2, int operator) {
        this.e1 = e1;
        this.e2 = e2;
        this.operator = operator;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable) throws InterpreterException {
        IValue v1 = this.e1.evaluate(symbolTable);
        IValue v2 = this.e2.evaluate(symbolTable);

        if (!v1.getType().equals(new BooleanType())) {
            throw new InterpreterException("Operand " + v1 + " is not an integer.");
        }
        if (!v2.getType().equals(new BooleanType())) {
            throw new InterpreterException("Operand " + v2 + " is not an integer.");
        }

        BooleanValue b1 = (BooleanValue) v1;
        BooleanValue b2 = (BooleanValue) v2;

        return switch (operator) {
            case 1 -> new BooleanValue(b1.getValue() && b2.getValue());
            case 2 -> new BooleanValue(b1.getValue() || b2.getValue());
            default -> null;
        };
    }

    public String toString() {
        return switch (this.operator) {
            case 1 -> this.e1.toString() + " AND " + this.e2.toString();
            case 2 -> this.e1.toString() + " OR " + this.e2.toString();

            default -> "";
        };
    }
}
