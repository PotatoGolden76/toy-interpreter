package domain.statements;

import domain.ProgramState;
import domain.structures.IDictionary;
import domain.types.IType;

public class NoOpStatement implements IStatement{
    @Override
    public ProgramState execute(ProgramState state) {
        return null;
    }

    //Type Check
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) {
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "[NoOp]";
    }
}
