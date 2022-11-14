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
        Controller c1, c2, c3, c4;

        c1 = new Controller(new Stack(s1), false, "log1.txt");
        c2 = new Controller(new Stack(s2), false, "log2.txt");
        c3 = new Controller(new Stack(s3), false, "log3.txt");
        c4 = new Controller(new Stack(s4), false, "log4.txt");

        RunCommand r1, r2, r3, r4;

        r1 = new RunCommand("1", "int v; v=2; Print(v)", c1);
        r2 = new RunCommand("2", "int a; int b; a=2+3*5; b=a+1; Print(b)", c2);
        r3 = new RunCommand("3", "bool a; int v; a=true; (If a Then v=2 Else v=3); Print(v)", c3);
        r4 = new RunCommand("4", "string varf; varf=\"test.in\"; openReadFile(varf); int varc; readFile(varf,varc); print(varc); readFile(varf,varc); print(varc); closeReadFile(varf)", c4);

        menu.addCommand(r1);
        menu.addCommand(r2);
        menu.addCommand(r3);
        menu.addCommand(r4);
        menu.addCommand(new ExitCommand("0", "exit"));

        menu.show();
    }

}
