package domain.statements;

import domain.*;
import domain.expressions.IExpression;
import domain.structures.IDictionary;
import domain.structures.IStack;
import domain.types.IType;
import domain.values.IValue;

public class AssignStatement implements IStatement {

    String id;
    IExpression e;

    public AssignStatement(String v, IExpression e) {
        this.id = v;
        this.e = e;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        IStack<IStatement> stack = state.getStack();
        IDictionary<String, IValue> symbols = state.getSymbolTable();

        IValue eValue = e.evaluate(symbols);
        IType eType = symbols.get(id).getType();


        if (!eValue.getType().equals(eType)) {
            throw new InterpreterException("Declared type " + id +" and type " + eType + " do not match.");
        }
        if (!symbols.isDefined(id)) {
            throw new InterpreterException("Variable " + id + " was not declared before.");
        }

        symbols.put(id, eValue);

        return state;
    }

    @Override
    public String toString() {
        return this.id + " = " + this.e.toString();
    }
}
