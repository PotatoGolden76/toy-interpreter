package domain.statements.lock;

import domain.ProgramState;
import domain.exceptions.InterpreterException;
import domain.exceptions.TypeException;
import domain.statements.IStatement;
import domain.structures.IDictionary;
import domain.types.IType;
import domain.types.IntType;
import domain.values.IValue;
import domain.values.IntValue;

import java.util.concurrent.locks.Lock;

public class LockStatement implements IStatement {
    private String variable;
    private static Lock lock = new java.util.concurrent.locks.ReentrantLock();

    public LockStatement(String variable) {
        this.variable = variable;
    }

    public ProgramState execute(ProgramState state) throws InterpreterException {
        lock.lock();
        IValue foundIndex = state.getSymbolTable().get(this.variable);

        if(foundIndex == null)
            throw new InterpreterException("No such variable in symbolTable");
        if(!(foundIndex.getType() instanceof IntType))
            throw new InterpreterException("Variable is not an integer");

        Integer lockValue = state.getLockTable().get(((IntValue)foundIndex).getValue());
        if (lockValue == null)
            throw new InterpreterException("No such index in LockTable");
        if (lockValue == -1) {
            state.getLockTable().put(((IntValue)foundIndex).getValue(), state.getID());
        }
        else{
           state.getStack().push(this);
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
            throw new TypeException("Lock: variable is not int ");
        }
    }


    @Override
    public String toString() {
        return "lock(" + variable + ")";
    }
}
