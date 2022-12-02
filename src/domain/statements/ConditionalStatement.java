package domain.statements;

import domain.exceptions.ExpressionException;
import domain.exceptions.StatementException;
import domain.exceptions.ValueException;
import domain.expressions.IExpression;
import domain.ProgramState;
import domain.types.BooleanType;
import domain.values.BooleanValue;
import domain.values.IValue;

public class ConditionalStatement implements IStatement{
    final IExpression e;
    final IStatement thenS;
    final IStatement elseS;

    public ConditionalStatement(IExpression e, IStatement thenS, IStatement elseS) {
        this.e = e;
        this.thenS = thenS;
        this.elseS = elseS;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ValueException, ExpressionException {
        IValue value = e.evaluate(state.getSymbolTable(), state.getHeap());

        if (value.getType().equals(new BooleanType())) {
            throw new StatementException("Expression " + e + " can not be resolved to boolean");
        }

        BooleanValue b = (BooleanValue) value;
        if (b.getValue()) {
            state.getStack().push(thenS);
        } else {
            state.getStack().push(elseS);
        }
        return state;
    }

    @Override
    public String toString() {
        return "if (" + this.e.toString() + ") then [ " + this.thenS.toString() + " ] else [ " + this.elseS.toString() + "]";
    }
}
