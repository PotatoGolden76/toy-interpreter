package domain.statements;

import domain.ProgramState;
import domain.exceptions.StatementException;
import domain.structures.Stack;

public class ForkStatement implements IStatement {
    private IStatement statement;

    public ForkStatement(IStatement statement) {
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException {
        return new ProgramState(new Stack(), state.getSymbolTable().clone(), state.getOutput(), state.getFileTable(), state.getHeap(), statement);
    }

    @Override
    public String toString() {
        return "fork(" + statement.toString() + ")";
    }

}
