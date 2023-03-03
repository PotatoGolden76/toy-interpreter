package domain.statements;

import domain.ProgramState;
import domain.exceptions.StatementException;
import domain.exceptions.TypeException;
import domain.structures.IDictionary;
import domain.structures.Stack;
import domain.types.IType;

public class ForkStatement implements IStatement {
    private IStatement statement;

    public ForkStatement(IStatement statement) {
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, TypeException {
        return new ProgramState(new Stack(), state.getSymbolTable().clone(), state.getOutput(), state.getFileTable(), state.getHeap(), statement, state.getLockTable());
    }

    //Type Check
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws TypeException {
        return this.statement.typeCheck(typeEnvironment);
    }

    @Override
    public String toString() {
        return "fork(" + statement.toString() + ")";
    }

}
