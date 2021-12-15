package Controller;
import javafx.scene.input.KeyCode;

public interface InputController {
    boolean isActive();
    void activate();
    void deactivate();
    void keyInput(KeyCode key);
}
