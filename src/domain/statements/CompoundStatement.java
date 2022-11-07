package domain.statements;

import domain.structures.IStack;
import domain.InterpreterException;
import domain.ProgramState;

public class CompoundStatement implements IStatement{
    IStatement first;
    IStatement second;

    public CompoundStatement(IStatement first, IStatement second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        IStack<IStatement> stack = state.getStack();
        stack.push(this.second);
        stack.push(this.first);
        return state;
    }

    @Override
    public String toString() {
        return "> " + this.first.toString() + " | " + this.second.toString();
    }
}
