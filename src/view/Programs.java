package view;

import domain.expressions.*;
import domain.statements.*;
import domain.types.BooleanType;
import domain.types.IntType;
import domain.types.ReferenceType;
import domain.types.StringType;
import domain.values.BooleanValue;
import domain.values.IntValue;
import domain.values.StringValue;

public class Programs {
    public static IStatement s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, exam;
    public static IStatement[] statements;

    static {
//        s1 = new CompoundStatement(
//                new DeclarationStatement("v", new IntType()),
//                new CompoundStatement(
//                        new AssignStatement("v", new ValueExpression(new IntValue(2))),
//                        new PrintStatement(new VariableExpression("v"))));
//
//        s2 = new CompoundStatement(
//                new DeclarationStatement("a", new IntType()),
//                new CompoundStatement(
//                        new DeclarationStatement("b", new IntType()),
//                        new CompoundStatement(
//                                new AssignStatement("a", new ArithmeticExpression(new ValueExpression(new IntValue(2)), new ArithmeticExpression(new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5)), '*'), '+')),
//                                new CompoundStatement(
//                                        new AssignStatement("b", new ArithmeticExpression(new VariableExpression("a"), new ValueExpression(new IntValue(1)), '+')),
//                                        new PrintStatement(new VariableExpression("b"))))));
//
//        s3 = new CompoundStatement(
//                new DeclarationStatement("a", new BooleanType()),
//                new CompoundStatement(
//                        new DeclarationStatement("v", new IntType()),
//                        new CompoundStatement(
//                                new AssignStatement("a", new ValueExpression(new BooleanValue(true))),
//                                new CompoundStatement(
//                                        new ConditionalStatement(
//                                                new VariableExpression("a"),
//                                                new AssignStatement("v", new ValueExpression(new IntValue(2))), new AssignStatement("v", new ValueExpression(new IntValue(3)))),
//                                        new PrintStatement(new VariableExpression("v"))))));
//
//        s4 = new CompoundStatement(
//                new DeclarationStatement("varf", new StringType()),
//                new CompoundStatement(
//                        new AssignStatement("varf", new ValueExpression(new StringValue("test.in"))),
//                        new CompoundStatement(
//                                new OpenReadFileStatement(new VariableExpression("varf")),
//                                new CompoundStatement(
//                                        new DeclarationStatement("varc", new IntType()),
//                                        new CompoundStatement(
//                                                new ReadFileStatement(new VariableExpression("varf"), "varc"),
//                                                new CompoundStatement(
//                                                        new PrintStatement(new VariableExpression("varc")),
//                                                        new CompoundStatement(
//                                                                new ReadFileStatement(new VariableExpression("varf"), "varc"),
//                                                                new CompoundStatement(
//                                                                        new PrintStatement(new VariableExpression("varc")),
//                                                                        new CloseFileStatement(new VariableExpression("varf"))))))))));
//
//        //Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)
//        s5 = new CompoundStatement(
//                new DeclarationStatement("v", new ReferenceType(new IntType())),
//                new CompoundStatement(
//                        new NewStatement("v", new ValueExpression(new IntValue(20))),
//                        new CompoundStatement(
//                                new DeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
//                                new CompoundStatement(
//                                        new NewStatement("a", new VariableExpression("v")),
//                                        new CompoundStatement(
//                                                new PrintStatement(new VariableExpression("v")),
//                                                new PrintStatement(new VariableExpression("a")))))));
//
//        //Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a)))
//        s6 = new CompoundStatement(
//                new DeclarationStatement("v", new ReferenceType(new IntType())),
//                new CompoundStatement(
//                        new NewStatement("v", new ValueExpression(new IntValue(20))),
//                        new CompoundStatement(
//                                new DeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
//                                new CompoundStatement(
//                                        new NewStatement("a", new VariableExpression("v")),
//                                        new CompoundStatement(
//                                                new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
//                                                new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a")))))))));
//
//        //Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
//        s7 = new CompoundStatement(
//                new DeclarationStatement("v", new ReferenceType(new IntType())),
//                new CompoundStatement(
//                        new NewStatement("v", new ValueExpression(new IntValue(20))),
//                        new CompoundStatement(
//                                new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
//                                new CompoundStatement(
//                                        new WriteHeapStatement("v", new ValueExpression(new IntValue(30))),
//                                        new PrintStatement(new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v")), new ValueExpression(new IntValue(5)), '+'))))));
//
//        //Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
//        s8 = new CompoundStatement(
//                new DeclarationStatement("v", new ReferenceType(new IntType())),
//                new CompoundStatement(
//                        new NewStatement("v", new ValueExpression(new IntValue(20))),
//                        new CompoundStatement(
//                                new DeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
//                                new CompoundStatement(
//                                        new NewStatement("a", new VariableExpression("v")),
//                                        new CompoundStatement(
//                                                new NewStatement("v", new ValueExpression(new IntValue(30))),
//                                                new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a")))))))));
//        //int v; v=4; (while (v>0) print(v);v=v-1);print(v)
//        s9 = new CompoundStatement(
//                new DeclarationStatement("v", new IntType()),
//                new CompoundStatement(
//                        new AssignStatement("v", new ValueExpression(new IntValue(4))),
//                        new CompoundStatement(
//                                new WhileStatement(
//                                        new RelationalExpression(new VariableExpression("v"), new ValueExpression(new IntValue(0)), ">"),
//                                        new CompoundStatement(
//                                                new PrintStatement(new VariableExpression("v")),
//                                                new AssignStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(1)), '-')))),
//                                new PrintStatement(new VariableExpression("v")))));
//
//        //int v; Ref int a; v=10; new(a,22); fork(wH(a,30);v=32;print(v);print(rH(a)));print(v);print(rH(a))
//        s10 = new CompoundStatement(
//                new DeclarationStatement("v", new IntType()),
//                new CompoundStatement(
//                        new DeclarationStatement("a", new ReferenceType(new IntType())),
//                        new CompoundStatement(
//                                new AssignStatement("v", new ValueExpression(new IntValue(10))),
//                                new CompoundStatement(
//                                        new NewStatement("a", new ValueExpression(new IntValue(22))),
//                                        new CompoundStatement(
//                                                new ForkStatement(
//                                                        new CompoundStatement(
//                                                                new WriteHeapStatement("a", new ValueExpression(new IntValue(30))),
//                                                                new CompoundStatement(
//                                                                        new AssignStatement("v", new ValueExpression(new IntValue(32))),
//                                                                        new CompoundStatement(
//                                                                                new PrintStatement(new VariableExpression("v")),
//                                                                                new PrintStatement(new ReadHeapExpression(new VariableExpression("a"))))))),
//                                                new CompoundStatement(
//                                                        new PrintStatement(new VariableExpression("v")),
//                                                        new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))))))));

        //Ref int a; new(a, 20); (for(v=0;v<3;v=v+1) fork(print(v);v=v*rH(a))); print(rH(a))
        exam = new CompoundStatement(
                new CompoundStatement(
                        new DeclarationStatement("a", new ReferenceType(new IntType())),
                        new CompoundStatement(
                                new NewStatement("a", new ValueExpression(new IntValue(20))),
                                new CompoundStatement(
                                        new ForStatement(
                                                new ValueExpression(new IntValue(0)), //v=0
                                                new ValueExpression(new IntValue(3)), //v<3
                                                new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(1)), '+'), //v+1
                                                new ForkStatement(
                                                        new CompoundStatement(
                                                                new PrintStatement(new VariableExpression("v")),
                                                                new AssignStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ReadHeapExpression(new VariableExpression("a")), '*'))))),
                                        new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))))),
                new NoOpStatement());


        statements = new IStatement[]{exam};
    }
}
