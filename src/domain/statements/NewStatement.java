package domain.statements;

import domain.ProgramState;
import domain.exceptions.ExpressionException;
import domain.exceptions.StatementException;
import domain.exceptions.ValueException;
import domain.expressions.IExpression;
import domain.values.ReferenceValue;

import java.io.IOException;

public class NewStatement implements IStatement{
    private String variableName;
    private IExpression expression;

    public NewStatement(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, IOException, ValueException, ExpressionException {
        var value = expression.evaluate(state.getSymbolTable(), state.getHeap());

        if(!state.getSymbolTable().isDefined(this.variableName))
            throw new StatementException("Variable " + this.variableName + " is not defined in the symbol table");

        if(!(state.getSymbolTable().get(this.variableName) instanceof ReferenceValue))
            throw new StatementException("Variable " + this.variableName + " is not a reference value");

        if(!value.getType().equals(state.getSymbolTable().get(this.variableName).getType()))
            throw new StatementException("Variable " + this.variableName + " is not of the same type as the expression");

        var address = state.getHeap().allocate(value);
        state.getSymbolTable().put(this.variableName, new ReferenceValue(address, value.getType()));

        return null;
    }

    @Override
    public String toString() {
        return "new(" + variableName + ", " + expression + ")";
    }
}
