package domain.statements;

import domain.InterpreterException;
import domain.ProgramState;
import domain.expressions.IExpression;
import domain.types.StringType;
import domain.values.IValue;
import domain.values.StringValue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenReadFileStatement implements IStatement{
    IExpression e;

    public OpenReadFileStatement(IExpression e) {
        this.e = e;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException, FileNotFoundException {
        IValue v = this.e.evaluate(state.getSymbolTable());

        if(!v.getType().equals(new StringType())) {
            throw new InterpreterException("File name not a StringValue");
        }
        if(state.getFileTable().isDefined((StringValue) v)) {
            throw new InterpreterException("File already open");
        }

        state.getFileTable().put((StringValue) v, new BufferedReader(new FileReader(((StringValue) v).getValue())));
        return state;
    }

    @Override
    public String toString() {
        return "open read: " + this.e.toString();
    }
}
