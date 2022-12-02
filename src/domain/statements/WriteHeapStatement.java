package domain.statements;

import domain.ProgramState;
import domain.exceptions.ExpressionException;
import domain.exceptions.StatementException;
import domain.exceptions.ValueException;
import domain.expressions.IExpression;
import domain.values.IValue;
import domain.values.ReferenceValue;

public class WriteHeapStatement implements IStatement{
    final String var;
    final IExpression e;

    public WriteHeapStatement(String var, IExpression e) {
        this.var = var;
        this.e = e;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ValueException, ExpressionException {
        IValue value = state.getSymbolTable().get(var);
        if (value == null) {
            throw new StatementException("Variable " + var + " not defined");
        }
        if (!(value instanceof ReferenceValue)) {
            throw new StatementException("Variable " + var + " is not a reference");
        }
        ReferenceValue ref = (ReferenceValue) value;
        if (!state.getHeap().isDefined(ref.getAddress())) {
            throw new StatementException("Address " + ref.getAddress() + " not defined in heap");
        }
        IValue value2 = e.evaluate(state.getSymbolTable(), state.getHeap());
        if (!value2.getType().equals(ref.getType())) {
            throw new StatementException("Type of " + var + " and " + e + " do not match");
        }
        state.getHeap().update(ref.getAddress(), value2);
        return state;
    }

    @Override
    public String toString() {
        return "writeHeap(" + this.var + ", " + this.e.toString() + ")";
    }
}
