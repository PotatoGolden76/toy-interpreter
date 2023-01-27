package domain.statements;

import domain.ProgramState;
import domain.exceptions.InterpreterException;
import domain.exceptions.TypeException;
import domain.expressions.*;
import domain.structures.IDictionary;
import domain.structures.TypeTable;
import domain.types.BooleanType;
import domain.types.IType;
import domain.types.IntType;

public class ForStatement implements IStatement {
    private IExpression init;
    private IExpression step;
    private IStatement body;
    private
    IExpression condition;

    public ForStatement(IExpression init, IExpression condition, IExpression step, IStatement body) {
        this.init = init;
        this.condition = condition;
        this.step = step;
        this.body = body;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        state.getStack().push(new CompoundStatement(
                new DeclarationStatement("v", new IntType()),
                new CompoundStatement(
                        new AssignStatement("v", init),
                        new WhileStatement(
                                new RelationalExpression(
                                        new VariableExpression("v"),
                                        condition,
                                        "<"
                                ),
                                new CompoundStatement(
                                        body,
                                        new AssignStatement("v", step)
                                )
                        )
                )
        ));
        return null;
    }

    // Type Check
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws TypeException {
        typeEnvironment.put("v", new IntType());
        IType e1 = this.init.typeCheck(typeEnvironment); //init
        IType e2 = this.condition.typeCheck(typeEnvironment); //cond
        IType e3 = this.step.typeCheck(typeEnvironment); //step

        if (!e1.equals(new IntType())) {
            throw new TypeException("The initialisation of the FOR is not an int");
        }

        if (!e2.equals(new IntType())) {
            throw new TypeException("The right operand of the condition of the FOR loop is not an int");
        }

        if (!e3.equals(new IntType())) {
            throw new TypeException("The step of the FOR loop is not an int");
        }



        return this.body.typeCheck(typeEnvironment);
    }


    @Override
    public String toString() {
        return "for (" + init + "; " + condition + "; " + step + ") " + body;
    }
}
