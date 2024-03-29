package domain.statements;

import domain.*;
import domain.exceptions.ExpressionException;
import domain.exceptions.StatementException;
import domain.exceptions.TypeException;
import domain.exceptions.ValueException;
import domain.expressions.IExpression;
import domain.structures.IDictionary;
import domain.structures.IStack;
import domain.types.IType;
import domain.values.IValue;

public class AssignStatement implements IStatement {

    final String id;
    final IExpression e;

    public AssignStatement(String v, IExpression e) {
        this.id = v;
        this.e = e;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ValueException, ExpressionException {
        IStack<IStatement> stack = state.getStack();
        IDictionary<String, IValue> symbols = state.getSymbolTable();

        IValue eValue = e.evaluate(symbols, state.getHeap());
        IType eType = symbols.get(id).getType();


        if (!eValue.getType().equals(eType)) {
            throw new StatementException("Declared type " + id +" and type " + eType + " do not match.");
        }
        if (!symbols.isDefined(id)) {
            throw new StatementException("Variable " + id + " was not declared before.");
        }

        symbols.put(id, eValue);

        return null;
    }

    //Type check
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws TypeException {
        IType typeVar = typeEnvironment.get(id);
        IType typeExp = e.typeCheck(typeEnvironment);
        if (typeVar.equals(typeExp)) { //equals is inverted???
            return typeEnvironment;
        } else {
            throw new TypeException("Assignment: right hand side and left hand side have different types ");
        }
    }

    @Override
    public String toString() {
        return this.id + " = " + this.e.toString();
    }
}
