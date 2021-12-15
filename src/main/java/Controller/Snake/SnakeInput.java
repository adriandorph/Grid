package Controller.Snake;
import Controller.InputController;
import Model.Snake.Direction;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class SnakeInput implements InputController {
    private static Direction direction;
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

    public Direction getDirection(){
        return direction;
    }

}
