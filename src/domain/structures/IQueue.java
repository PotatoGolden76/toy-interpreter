package domain.structures;

public interface IQueue<E> {
    void push(E o);
    E pop();
}
