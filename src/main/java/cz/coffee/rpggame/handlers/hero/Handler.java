package cz.coffee.rpggame.handlers.hero;

@SuppressWarnings("all")
public interface Handler<T> {
    void handle(T b);
    void dispatchTo(T b);
    void mountTo(T b);
}
