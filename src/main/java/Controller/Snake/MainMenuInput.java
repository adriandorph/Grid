package Controller.Snake;

import javafx.scene.input.KeyCode;

import Controller.Controller;

public class MainMenuInput {
    private static boolean isActive = false;

    public static boolean isActive() {
        return isActive;
    }

    public static void activate() {
        isActive = true;
    }

    public static void deactivate() {
        isActive = false;
    }

    public static void keyInput(KeyCode key){
        if(isActive()) {
            if (key == KeyCode.ESCAPE) {
                Controller.exit();
            } else if (key == KeyCode.ENTER){
                Controller.viewNewSnakeGame();
            }
        }
    }
}
