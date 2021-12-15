package Controller;

public interface Updatable<T> {
    void update(double seconds);
    T getRenderObject();
}
