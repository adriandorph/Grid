package Controller.Snake;

import Controller.Controller;
import javafx.scene.input.KeyCode;

public class EscapeMenuInput {
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
        if(isActive()){
            if (key == KeyCode.ESCAPE) {
                Controller.viewMainMenu();
            } else if (key == KeyCode.ENTER) {
                Controller.viewExistingSnakeGame();
                SnakeInput.unpause();
            }
        }
    }
}
