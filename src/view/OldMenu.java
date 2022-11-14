package view;

import controller.Controller;
import domain.exceptions.ExpressionException;
import domain.exceptions.InterpreterException;
import domain.expressions.ArithmeticExpression;
import domain.expressions.ValueExpression;
import domain.expressions.VariableExpression;
import domain.statements.*;
import domain.structures.Stack;
import domain.types.BooleanType;
import domain.types.StringType;
import domain.values.BooleanValue;
import domain.types.IntType;
import domain.values.IntValue;
import domain.values.StringValue;

import java.io.IOException;
import java.util.Scanner;

public class OldMenu {
    static Controller c;


    public static void run() {
        Stack program = new Stack();

        Scanner scn = new Scanner(System.in);
        int option, steps;


        System.out.println("Select program to run: ");
        System.out.println("1. int v; v=2; Print(v)");
        System.out.println("2. int a; int b; a=2+3*5; b=a+1; Print(b)");
        System.out.println("3. bool a; int v; a=true; (If a Then v=2 Else v=3); Print(v)");
        System.out.println("4. string varf; varf=\"test.in\"; openReadFile(varf); int varc; readFile(varf,varc); print(varc); readFile(varf,varc); print(varc); closeReadFile(varf)");
        option = scn.nextInt();

        System.out.println("Run step-by-step 1/0: ");
        steps = scn.nextInt();

        IStatement s;
        switch (option) {
            case 1 -> s = new CompoundStatement(
                    new DeclarationStatement("v", new IntType()),
                    new CompoundStatement(
                            new AssignStatement("v", new ValueExpression(new IntValue(2))),
                            new PrintStatement(new VariableExpression("v"))));
            case 2 -> s = new CompoundStatement(
                    new DeclarationStatement("a", new IntType()),
                    new CompoundStatement(
                            new DeclarationStatement("b", new IntType()),
                            new CompoundStatement(
                                    new AssignStatement("a", new ArithmeticExpression(new ValueExpression(new IntValue(2)), new ArithmeticExpression(new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5)), '*'), '+')),
                                    new CompoundStatement(
                                            new AssignStatement("b", new ArithmeticExpression(new VariableExpression("a"), new ValueExpression(new IntValue(1)), '+')),
                                            new PrintStatement(new VariableExpression("b"))))));
            case 3 -> s = new CompoundStatement(
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
            case 4 -> s = new CompoundStatement(
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
            default -> {
                System.out.println("Invalid options");
                return;
            }
        }

        program.push(s);
        scn.nextLine();
        System.out.println("path of log file: ");
        String log = scn.nextLine();
        try {
            c = new Controller(program, steps == 1, log);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Initial State:\n" + c.getRepository().toString() + "\n>>> EXECUTING <<<\n");
        try {
            c.run();
        } catch (InterpreterException | IOException | ExpressionException e) {
            System.out.println(e.getMessage());
        }
    }
}
