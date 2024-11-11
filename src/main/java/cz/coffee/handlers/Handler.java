package cz.coffee.handlers;

@SuppressWarnings("all")
public interface Handler<T> {
    boolean handle(T b);
    void dispatchTo(T b);
    void mountTo(T b);
}
