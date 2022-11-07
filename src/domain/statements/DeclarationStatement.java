package domain.statements;

import domain.InterpreterException;
import domain.ProgramState;
import domain.types.IType;
import domain.types.IValue;
import domain.structures.IDictionary;

public class DeclarationStatement implements IStatement{
    String id;
    IType t;

    public DeclarationStatement(String id, IType t) {
        this.id = id;
        this.t = t;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        IDictionary<String, IValue> symbols = state.getSymbolTable();

        if(symbols.isDefined(id)) {
            throw new InterpreterException("Variable " + id + " already defined.");
        }

        symbols.put(id, t.defaultValue());
        return state;
    }

    @Override
    public String toString() {
        return "> " + t.toString() + " " + id;
    }
}
