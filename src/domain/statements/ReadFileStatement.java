package domain.statements;

import domain.exceptions.ExpressionException;
import domain.ProgramState;
import domain.exceptions.StatementException;
import domain.exceptions.TypeException;
import domain.exceptions.ValueException;
import domain.expressions.IExpression;
import domain.structures.IDictionary;
import domain.types.IType;
import domain.types.IntType;
import domain.types.StringType;
import domain.values.IValue;
import domain.values.IntValue;
import domain.values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements IStatement{
    final IExpression e;
    final String id;

    public ReadFileStatement(IExpression e, String id) {
        this.e = e;
        this.id = id;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, IOException, ValueException, ExpressionException {
        IValue v = this.e.evaluate(state.getSymbolTable(), state.getHeap());

        if(!state.getSymbolTable().isDefined(this.id)) {
            throw new StatementException("Variable " + id + " not defined");
        }
        if(state.getSymbolTable().get(this.id).getType().equals(new IntType())) {
            throw new StatementException("Variable " + id + " not a IntValue");
        }
        if(v.getType().equals(new StringType())) {
            throw new StatementException("File name not a StringValue");
        }

        if(!state.getFileTable().isDefined((StringValue) v)) {
            throw new StatementException("File not open");
        }

        BufferedReader br = state.getFileTable().get((StringValue) v);
        String line = br.readLine();
        if(line == null) {
            state.getSymbolTable().put(this.id, new IntType().defaultValue());
        } else {
            state.getSymbolTable().put(this.id, new IntValue(Integer.parseInt(line)));
        }
        return null;
    }

    //Type Check
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws TypeException {
        IType type = this.e.typeCheck(typeEnvironment);
        if(!type.equals(new StringType())) {
            if(typeEnvironment.isDefined(this.id)) {
                if(!typeEnvironment.get(this.id).equals(new IntType())) {
                    return typeEnvironment;
                }
                else {
                    throw new TypeException("ReadFileStatement: The variable is not an int.");
                }
            }
            else {
                throw new TypeException("ReadFileStatement: The variable is not defined.");
            }
        }
        else {
            throw new TypeException("ReadFileStatement: The variable is not a string.");
        }
    }

    @Override
    public String toString() {
        return "read: " + this.e.toString() + " into " + this.id;
    }
}
