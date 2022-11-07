import controller.Controller;
import domain.InterpreterException;
import domain.expressions.ArithmeticExpression;
import domain.expressions.ValueExpression;
import domain.expressions.VariableExpression;
import domain.statements.*;
import domain.structures.Stack;
import domain.types.BooleanType;
import domain.types.BooleanValue;
import domain.types.IntType;
import domain.types.IntValue;

import java.util.Scanner;

public class Main {
    static Controller c;


    public static void main(String[] args) {
        Stack program = new Stack();

        Scanner scn = new Scanner(System.in);
        int option, steps;


        System.out.println("Select program to run: ");
        System.out.println("1. int v; v=2; Print(v)");
        System.out.println("2. int a; int b; a=2+3*5; b=a+1; Print(b)");
        System.out.println("3. bool a; int v; a=true; (If a Then v=2 Else v=3); Print(v)");
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
            default -> {
                System.out.println("Invalid options");
                return;
            }
        }

        program.push(s);
        c = new Controller(program, steps == 1);
        System.out.println("Initial State:\n"+c.getRepository().toString()+ "\n>>> EXECUTIN <<<\n");
        try {
            c.run();
        } catch (InterpreterException e) {
            System.out.println(e.getMessage().toString());
        }
    }
}
