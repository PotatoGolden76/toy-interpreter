package domain.statements;

import domain.ProgramState;
import domain.exceptions.StatementException;
import domain.expressions.VariableExpression;
import domain.values.StringValue;

import java.io.IOException;

public class CloseFileStatement implements IStatement {

    final VariableExpression e;

    public CloseFileStatement(VariableExpression varf) {
        this.e = varf;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, IOException {
        StringValue v = (StringValue) this.e.evaluate(state.getSymbolTable());
        if(!state.getFileTable().isDefined(v)) {
            throw new StatementException("File not open");
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
