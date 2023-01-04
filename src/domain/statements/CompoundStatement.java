package domain.statements;

import domain.exceptions.StatementException;
import domain.exceptions.TypeException;
import domain.structures.IDictionary;
import domain.structures.IStack;
import domain.ProgramState;
import domain.types.IType;

public class CompoundStatement implements IStatement{
    final IStatement first;
    final IStatement second;

    public CompoundStatement(IStatement first, IStatement second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IStack<IStatement> stack = state.getStack();
        stack.push(this.second);
        stack.push(this.first);
        return null;
    }

    //Type Check
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws TypeException {
        return this.second.typeCheck(this.first.typeCheck(typeEnvironment));
    }

    @Override
    public String toString() {
        return this.first.toString() + " | " + this.second.toString();
    }
}
