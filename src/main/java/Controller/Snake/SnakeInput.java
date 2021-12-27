package Controller.Snake;
import Controller.DirectionController;
import Controller.InputController;
import Model.Direction;
import javafx.scene.input.KeyCode;

public class SnakeInput implements InputController {
    public static Direction direction;
    private static boolean isActive = false;

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void activate() {
        isActive = true;
    }

    @Override
    public void deactivate() {
        isActive = false;
    }

    @Override
    public void keyInput(KeyCode key){
        switch (key){
            case LEFT, A -> direction = DirectionController.getLeftDirection(direction);
            case RIGHT, D -> direction = DirectionController.getRightDirection(direction);
        }
    }
}
