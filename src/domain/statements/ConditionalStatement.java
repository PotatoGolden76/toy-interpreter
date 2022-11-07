package domain.statements;

import domain.expressions.IExpression;
import domain.InterpreterException;
import domain.ProgramState;
import domain.types.BooleanType;
import domain.types.BooleanValue;
import domain.types.IValue;

public class ConditionalStatement implements IStatement{
    IExpression e;
    IStatement thenS, elseS;

    public ConditionalStatement(IExpression e, IStatement thenS, IStatement elseS) {
        this.e = e;
        this.thenS = thenS;
        this.elseS = elseS;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        IValue value = e.evaluate(state.getSymbolTable());

        if (!value.getType().equals(new BooleanType())) {
            throw new InterpreterException("Expression " + e.toString() + " can not be resolved to boolean");
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
