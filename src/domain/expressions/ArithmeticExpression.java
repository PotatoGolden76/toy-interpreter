package domain.expressions;

import domain.exceptions.ExpressionException;
import domain.exceptions.StatementException;
import domain.exceptions.TypeException;
import domain.exceptions.ValueException;
import domain.structures.Heap;
import domain.structures.IDictionary;
import domain.types.IType;
import domain.types.IntType;
import domain.values.IValue;
import domain.values.IntValue;

public class ArithmeticExpression implements IExpression {

    final IExpression e1;
    final IExpression e2;
    final char operator;

    public ArithmeticExpression(IExpression e1, IExpression e2, char operator) {
        this.e1 = e1;
        this.e2 = e2;
        this.operator = operator;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable, Heap heap) throws ExpressionException, ValueException, StatementException {
        IValue v1 = this.e1.evaluate(symbolTable, heap);
        IValue v2 = this.e2.evaluate(symbolTable, heap);

        if (v1.getType().equals(new IntType())) {
            throw new ExpressionException("Operand " + v1 + " is not an integer.");
        }
        if (v2.getType().equals(new IntType())) {
            throw new ExpressionException("Operand " + v2 + " is not an integer.");
        }

        IntValue int1 = (IntValue) v1;
        IntValue int2 = (IntValue) v2;
//        System.out.println(v1 + " " + v2);
//        System.out.println(int1 + " " + int2);
        switch (operator) {
            case '+':
//                System.out.println(new IntValue(int1.getValue() + int2.getValue()) + "");
                return new IntValue(int1.getValue() + int2.getValue());
            case '-':
                return new IntValue(int1.getValue() - int2.getValue());
            case '*':
                return new IntValue(int1.getValue() * int2.getValue());
            case '/':
                return new IntValue(int1.getValue() / int2.getValue());
            default:
                return null;
        }
    }

    public IType typeCheck(IDictionary<String, IType> typeEnvironment) throws TypeException {
        IType type1, type2;
        type1 = e1.typeCheck(typeEnvironment);
        type2 = e2.typeCheck(typeEnvironment);
        if (type1.equals(new IntType())) {
            throw new TypeException("First operand is not an integer.");
        }
        if (type2.equals(new IntType())) {
            throw new TypeException("Second operand is not an integer.");
        }
        return new IntType();
    }


    @Override
    public String toString() {
        return switch (this.operator) {
            case 1 -> this.e1.toString() + " + " + this.e2.toString();
            case 2 -> this.e1.toString() + " - " + this.e2.toString();
            case 3 -> this.e1.toString() + " * " + this.e2.toString();
            case 4 -> this.e1.toString() + " / " + this.e2.toString();
            default -> "";
        };
    }
}
