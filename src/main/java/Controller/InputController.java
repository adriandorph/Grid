package Controller;

import Controller.Snake.EscapeMenuInput;
import Controller.Snake.MainMenuInput;
import Controller.Snake.SnakeInput;

public class InputController {
    public static void deactivateAll(){
        SnakeInput.deactivate();
        MainMenuInput.deactivate();
        EscapeMenuInput.deactivate();
    }

    public static void SnakeInput(){
        deactivateAll();
        SnakeInput.activate();
    }

    public static void MainMenuInput(){
        deactivateAll();
        MainMenuInput.activate();
    }

    public static void EscapeMenuInput(){
        deactivateAll();
        EscapeMenuInput.activate();
    }
}
