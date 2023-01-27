package domain.statements.lock;

import domain.ProgramState;
import domain.exceptions.*;
import domain.statements.IStatement;
import domain.structures.IDictionary;
import domain.types.IType;
import domain.types.IntType;
import domain.values.IValue;
import domain.values.IntValue;

import java.io.IOException;
import java.util.concurrent.locks.Lock;

public class UnlockStatement implements IStatement {
    private String variable;
    private static Lock lock = new java.util.concurrent.locks.ReentrantLock();

    public UnlockStatement(String variable) {
        this.variable = variable;
    }



    @Override
    public String toString() {
        return "unlock(" + variable + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, IOException, ValueException, ExpressionException, TypeException, InterpreterException {
        lock.lock();
        IValue foundIndex = state.getSymbolTable().get(this.variable);

        if(foundIndex == null)
            throw new InterpreterException("No such variable in symbolTable");
        if(!(foundIndex.getType() instanceof IntValue))
            throw new InterpreterException("Variable is not an integer");

        Integer lockValue = state.getLockTable().get(((IntValue)foundIndex).getValue());
        if (lockValue == null)
            throw new InterpreterException("No such index in LockTable");
        if (lockValue == state.getID()) {

            state.getLockTable().put(((IntValue)foundIndex).getValue(), -1);
        }

        lock.unlock();
        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws TypeException {
        IType typeVar = typeEnvironment.get(this.variable);
        if (typeVar.equals(new IntType())) {
            return typeEnvironment;
        } else {
            throw new TypeException("Unlock: variable is not int ");
        }
    }
}
