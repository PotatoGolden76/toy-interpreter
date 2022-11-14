package domain.statements;

import domain.exceptions.ExpressionException;
import domain.exceptions.ValueException;
import domain.expressions.IExpression;
import domain.ProgramState;

public class PrintStatement implements IStatement{
    final IExpression e;

    public PrintStatement(IExpression e) {
        this.e = e;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ValueException, ExpressionException {
        String s = this.e.evaluate(state.getSymbolTable()).toString();
        state.getOutput().push(s);
        return state;
    }

    @Override
    public String toString() {
        return "print: " + this.e.toString();
    }
}
