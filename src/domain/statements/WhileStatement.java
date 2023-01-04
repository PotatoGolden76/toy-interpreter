package domain.statements;

import domain.ProgramState;
import domain.exceptions.ExpressionException;
import domain.exceptions.StatementException;
import domain.exceptions.TypeException;
import domain.exceptions.ValueException;
import domain.expressions.IExpression;
import domain.structures.IDictionary;
import domain.structures.TypeTable;
import domain.types.BooleanType;
import domain.types.IType;
import domain.values.BooleanValue;
import domain.values.IValue;

public class WhileStatement implements IStatement {
    private IStatement statement;
    private IExpression expression;

    public WhileStatement(IExpression expression, IStatement statement) {
        this.expression = expression;
        this.statement = statement;

    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ValueException, ExpressionException {
        IValue value = expression.evaluate(state.getSymbolTable(), state.getHeap());
        if (value.getType() instanceof BooleanType) {
            if (((BooleanValue) value).getValue()) {
                state.getStack().push(this);
                state.getStack().push(statement);
            }
        } else {
            throw new StatementException("Expression is not a boolean");
        }
        return null;
    }

    //Type Check
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws TypeException {
        IType typeExp = this.expression.typeCheck(typeEnvironment);
        if (!typeExp.equals(new BooleanType())) {
            return this.statement.typeCheck(((TypeTable) typeEnvironment).clone());
        } else {
            throw new TypeException("The condition of WHILE does nto have the type bool");
        }
    }

    @Override
    public String toString() {
        return "while(" + expression.toString() + ") " + statement.toString();
    }
}
