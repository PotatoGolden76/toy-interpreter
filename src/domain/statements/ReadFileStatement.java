package domain.statements;

import domain.InterpreterException;
import domain.ProgramState;
import domain.expressions.IExpression;
import domain.types.IntType;
import domain.types.StringType;
import domain.values.IValue;
import domain.values.IntValue;
import domain.values.StringValue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileStatement implements IStatement{
    IExpression e;
    String id;

    public ReadFileStatement(IExpression e, String id) {
        this.e = e;
        this.id = id;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException, IOException {
        IValue v = this.e.evaluate(state.getSymbolTable());

        if(!state.getSymbolTable().isDefined(this.id)) {
            throw new InterpreterException("Variable " + id + " not defined");
        }
        if(!state.getSymbolTable().get(this.id).getType().equals(new IntType())) {
            throw new InterpreterException("Variable " + id + " not a IntValue");
        }
        if(!v.getType().equals(new StringType())) {
            throw new InterpreterException("File name not a StringValue");
        }

        if(!state.getFileTable().isDefined((StringValue) v)) {
            throw new InterpreterException("File not open");
        }

        BufferedReader br = state.getFileTable().get((StringValue) v);
        String line = br.readLine();
        if(line == null) {
            state.getSymbolTable().put(this.id, new IntType().defaultValue());
        } else {
            state.getSymbolTable().put(this.id, new IntValue(Integer.parseInt(line)));
        }
        return state;
    }

    @Override
    public String toString() {
        return "read: " + this.e.toString() + " into " + this.id;
    }
}
