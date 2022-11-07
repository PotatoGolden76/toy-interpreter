package domain.structures;

import domain.statements.IStatement;
import domain.structures.IStack;


public class Stack implements IStack<IStatement> {

    final java.util.Stack<IStatement> internal = new java.util.Stack<>();
    @Override
    public void push(IStatement o) {
        internal.push(o);
    }

    @Override
    public IStatement pop() {
        return internal.pop();
    }
}
