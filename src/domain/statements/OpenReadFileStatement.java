package domain.statements;

import domain.exceptions.ExpressionException;
import domain.ProgramState;
import domain.exceptions.StatementException;
import domain.exceptions.ValueException;
import domain.expressions.IExpression;
import domain.types.StringType;
import domain.values.IValue;
import domain.values.StringValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenReadFileStatement implements IStatement{
    final IExpression e;

    public OpenReadFileStatement(IExpression e) {
        this.e = e;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, IOException, ValueException, ExpressionException {
        IValue v = this.e.evaluate(state.getSymbolTable());

        if(v.getType().equals(new StringType())) {
            throw new StatementException("File name not a StringValue");
        }
        if(state.getFileTable().isDefined((StringValue) v)) {
            throw new StatementException("File already open");
        }

        state.getFileTable().put((StringValue) v, new BufferedReader(new FileReader(((StringValue) v).getValue())));
        return state;
    }

    @Override
    public String toString() {
        return "open read: " + this.e.toString();
    }
}
