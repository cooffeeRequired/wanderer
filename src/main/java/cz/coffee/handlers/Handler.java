package cz.coffee.handlers;

@SuppressWarnings("all")
public interface Handler<T, Location> {
    boolean handle(T b, Location l);
    void dispatchTo(T b);
    void mountTo(T b);
}
