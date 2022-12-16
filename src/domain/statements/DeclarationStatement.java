package domain.statements;

import domain.ProgramState;
import domain.exceptions.StatementException;
import domain.types.IType;
import domain.values.IValue;
import domain.structures.IDictionary;

public class DeclarationStatement implements IStatement{
    final String id;
    final IType t;

    public DeclarationStatement(String id, IType t) {
        this.id = id;
        this.t = t;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException {
        IDictionary<String, IValue> symbols = state.getSymbolTable();

        if(symbols.isDefined(id)) {
            throw new StatementException("Variable " + id + " already defined.");
        }

        symbols.put(id, t.defaultValue());
        return null;
    }

    @Override
    public String toString() {
        return t.toString() + " " + id;
    }
}
