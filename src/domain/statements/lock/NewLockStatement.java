package domain.statements.lock;

import domain.ProgramState;
import domain.exceptions.TypeException;
import domain.expressions.ValueExpression;
import domain.statements.IStatement;
import domain.structures.IDictionary;
import domain.types.IType;
import domain.types.IntType;
import domain.types.StringType;
import domain.values.IntValue;

import java.util.concurrent.locks.Lock;

public class NewLockStatement implements IStatement {
    private static int nextAddress = 1;

    private String variable;
    private static Lock lock = new java.util.concurrent.locks.ReentrantLock();

    public NewLockStatement(String variable) {
        this.variable = variable;
    }

    public ProgramState execute(ProgramState state) {
        lock.lock();

        state.getLockTable().put(nextAddress, -1);
        state.getSymbolTable().put(variable, new IntValue(nextAddress));
        nextAddress++;

        lock.unlock();
        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws TypeException {
        IType typeVar = typeEnvironment.get(this.variable);
        if (typeVar.equals(new IntType())) {
            return typeEnvironment;
        } else {
            throw new TypeException("NewLock: variable is not int ");
        }
    }

    @Override
    public String toString() {
        return "newLock(" + variable + ")";
    }
}
