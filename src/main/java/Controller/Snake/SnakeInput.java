package Controller.Snake;

import Controller.InputController;
import Controller.Snake.DirectionController;
import Model.Snake.Direction;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class SnakeInput implements InputController {
    private Direction direction;

    @Override
    public void setKeyInput(Scene scene){
        setKeyInputPressed(scene);
    }

    private void setKeyInputPressed(Scene scene){
        scene.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
            switch (key){
                case LEFT, A -> direction = DirectionController.getLeftDirection(direction);
                case RIGHT, D -> direction = DirectionController.getRightDirection(direction);
            }
        });
    }

    public Direction getDirection(){
        return direction;
    }

}
