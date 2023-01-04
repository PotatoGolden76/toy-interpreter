package domain.statements;

import domain.ProgramState;
import domain.exceptions.StatementException;
import domain.exceptions.TypeException;
import domain.expressions.VariableExpression;
import domain.structures.IDictionary;
import domain.types.IType;
import domain.types.StringType;
import domain.values.StringValue;

import java.io.IOException;

public class CloseFileStatement implements IStatement {

    final VariableExpression e;

    public CloseFileStatement(VariableExpression varf) {
        this.e = varf;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, IOException {
        StringValue v = (StringValue) this.e.evaluate(state.getSymbolTable(), state.getHeap());
        if(!state.getFileTable().isDefined(v)) {
            throw new StatementException("File not open");
        }
        state.getFileTable().get(v).close();
        state.getFileTable().remove(v);
        return null;
    }

    //Type Check
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws TypeException {
        IType type = this.e.typeCheck(typeEnvironment);
        if(!type.equals(new StringType())) {
            return typeEnvironment;
        }
        else {
            throw new TypeException("CloseFileStatement: The variable is not a string.");
        }
    }

    @Override
    public String toString() {
        return "close read: " + this.e.toString();
    }
}
