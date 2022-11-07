package domain.statements;

import domain.IExpression;
import domain.InterpreterException;
import domain.ProgramState;

public class PrintStatement implements IStatement{
    IExpression e;

    public PrintStatement(IExpression e) {
        this.e = e;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        //TODO: implement
        return state;
    }

    @Override
    public String toString() {
        return "> print: " + this.e.toString();
    }
}
