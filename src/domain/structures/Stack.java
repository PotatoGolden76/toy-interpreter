package domain.structures;

import domain.statements.IStatement;
import domain.structures.IStack;


public class Stack implements IStack<IStatement> {

    final java.util.Stack<IStatement> internal;

    public Stack() {
        this.internal = new java.util.Stack<>();
    }
    public Stack(IStatement start) {
        this.internal = new java.util.Stack<>();
        this.push(start);
    }

    public Stack(Stack stack) {
        this.internal = new java.util.Stack<>();
        this.internal.addAll(stack.internal);
    }

    @Override
    public void push(IStatement o) {
        internal.push(o);
    }

    @Override
    public IStatement pop() {
        return internal.pop();
    }

    @Override
    public boolean isEmpty() {
        return this.internal.isEmpty();
    }

    @Override
    public String toString() {
        String s = "Execution Stack: ";
        for(IStatement o : this.internal.stream().toList()) {
            s = s.concat("\n> " + o.toString());
        }
        return s;
    }
}
