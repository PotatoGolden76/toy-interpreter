package view;

import domain.expressions.ArithmeticExpression;
import domain.expressions.ValueExpression;
import domain.expressions.VariableExpression;
import domain.statements.*;
import domain.types.BooleanType;
import domain.types.IntType;
import domain.types.StringType;
import domain.values.BooleanValue;
import domain.values.IntValue;
import domain.values.StringValue;

public class Programs {
    public static IStatement s1, s2, s3, s4;

    static {
        s1 = new CompoundStatement(
                new DeclarationStatement("v", new IntType()),
                new CompoundStatement(
                        new AssignStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));

        s2 = new CompoundStatement(
                new DeclarationStatement("a", new IntType()),
                new CompoundStatement(
                        new DeclarationStatement("b", new IntType()),
                        new CompoundStatement(
                                new AssignStatement("a", new ArithmeticExpression(new ValueExpression(new IntValue(2)), new ArithmeticExpression(new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5)), '*'), '+')),
                                new CompoundStatement(
                                        new AssignStatement("b", new ArithmeticExpression(new VariableExpression("a"), new ValueExpression(new IntValue(1)), '+')),
                                        new PrintStatement(new VariableExpression("b"))))));

        s3 = new CompoundStatement(
                new DeclarationStatement("a", new BooleanType()),
                new CompoundStatement(
                        new DeclarationStatement("v", new IntType()),
                        new CompoundStatement(
                                new AssignStatement("a", new ValueExpression(new BooleanValue(true))),
                                new CompoundStatement(
                                        new ConditionalStatement(
                                                new VariableExpression("a"),
                                                new AssignStatement("v", new ValueExpression(new IntValue(2))), new AssignStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))))));

        s4 = new CompoundStatement(
                new DeclarationStatement("varf", new StringType()),
                new CompoundStatement(
                        new AssignStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(
                                new OpenReadFileStatement(new VariableExpression("varf")),
                                new CompoundStatement(
                                        new DeclarationStatement("varc", new IntType()),
                                        new CompoundStatement(
                                                new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                new CompoundStatement(
                                                        new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(
                                                                new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                                new CompoundStatement(
                                                                        new PrintStatement(new VariableExpression("varc")),
                                                                        new CloseFileStatement(new VariableExpression("varf"))))))))));
    }
}
