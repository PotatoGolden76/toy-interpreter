package domain.structures;

import domain.statements.IStatement;

public interface IStack<E> {
    void push(E o);
    E pop();
    boolean isEmpty();

    IStatement[] content();
}
