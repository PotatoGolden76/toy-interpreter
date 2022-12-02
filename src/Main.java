import controller.Controller;
import domain.exceptions.InterpreterException;
import domain.structures.Stack;
import view.TextMenu;
import view.commands.ExitCommand;
import view.commands.RunCommand;

import java.io.IOException;

import static view.Programs.*;

public class Main {

    public static void main(String[] args) throws IOException, InterpreterException {
        TextMenu menu = new TextMenu();
        Controller c1, c2, c3, c4, c5, c6, c7, c8, c9;

        c1 = new Controller(new Stack(s1), false, "log1.txt");
        c2 = new Controller(new Stack(s2), false, "log2.txt");
        c3 = new Controller(new Stack(s3), false, "log3.txt");
        c4 = new Controller(new Stack(s4), false, "log4.txt");
        c5 = new Controller(new Stack(s5), false, "log5.txt");
        c6 = new Controller(new Stack(s6), false, "log6.txt");
        c7 = new Controller(new Stack(s7), false, "log7.txt");
        c8 = new Controller(new Stack(s8), false, "log8.txt");
        c9 = new Controller(new Stack(s9), false, "log9.txt");

        RunCommand r1, r2, r3, r4, r5, r6 ,r7, r8, r9;

        r1 = new RunCommand("1", "int v; v=2; Print(v)", c1);
        r2 = new RunCommand("2", "int a; int b; a=2+3*5; b=a+1; Print(b)", c2);
        r3 = new RunCommand("3", "bool a; int v; a=true; (If a Then v=2 Else v=3); Print(v)", c3);
        r4 = new RunCommand("4", "string varf; varf=\"test.in\"; openReadFile(varf); int varc; readFile(varf,varc); print(varc); readFile(varf,varc); print(varc); closeReadFile(varf)", c4);
        r5 = new RunCommand("5", "Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)", c5);
        r6 = new RunCommand("6", "Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a)))", c6);
        r7 = new RunCommand("7", "Ref int v;new(v,20);print(rH(v));wH(v,30);print(rH(v)+5)", c7);
        r8 = new RunCommand("8", "Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))", c8);
        r9 = new RunCommand("9", "int v; v=4; (while (v>0) print(v);v=v-1);print(v)", c9);

        menu.addCommand(r1);
        menu.addCommand(r2);
        menu.addCommand(r3);
        menu.addCommand(r4);
        menu.addCommand(r5);
        menu.addCommand(r6);
        menu.addCommand(r7);
        menu.addCommand(r8);
        menu.addCommand(r9);

        menu.addCommand(new ExitCommand("0", "exit"));

        menu.show();
    }

}
