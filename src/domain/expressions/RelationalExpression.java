package domain.expressions;

import domain.exceptions.ExpressionException;
import domain.exceptions.ValueException;
import domain.structures.IDictionary;
import domain.values.IValue;

public class RelationalExpression implements IExpression {

    private final IExpression left;
    private final IExpression right;
    private final String operator;

    public RelationalExpression(IExpression left, IExpression right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable) throws ExpressionException, ValueException {
        IValue leftValue = this.left.evaluate(symbolTable);
        IValue rightValue = this.right.evaluate(symbolTable);

        return switch (this.operator) {
            case "<" -> leftValue.lessThan(rightValue);
            case "<=" -> leftValue.lessThanOrEqual(rightValue);
            case "==" -> leftValue.equals(rightValue);
            case "!=" -> leftValue.notEqual(rightValue);
            case ">" -> leftValue.greaterThan(rightValue);
            case ">=" -> leftValue.greaterThanOrEqual(rightValue);
            default -> throw new ExpressionException("Invalid operator.");
        };
    }

    @Override
    public String toString() {
        return this.left.toString() + " " + this.operator + " " + this.right.toString();
    }
}
