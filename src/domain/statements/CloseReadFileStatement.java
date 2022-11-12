package domain.statements;

import domain.InterpreterException;
import domain.ProgramState;
import domain.expressions.VariableExpression;
import domain.values.StringValue;

import java.io.IOException;

public class CloseReadFileStatement implements IStatement {

    VariableExpression e;

    public CloseReadFileStatement(VariableExpression varf) {
        this.e = varf;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException, IOException {
        StringValue v = (StringValue) this.e.evaluate(state.getSymbolTable());
        if(!state.getFileTable().isDefined(v)) {
            throw new InterpreterException("File not open");
        }
        state.getFileTable().get(v).close();
        state.getFileTable().remove(v);
        return state;
    }

    @Override
    public String toString() {
        return "close read: " + this.e.toString();
    }
}