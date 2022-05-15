package Controller;

import Controller.Snake.*;
import Model.Snake.KeyBindOption;
import View.Snake.Settings.KeyChangePromptPage;

public class InputController {
    public static void deactivateAll(){
        SnakeInput.deactivate();
        MainMenuInput.deactivate();
        EscapeMenuInput.deactivate();
        SettingsInput.deactivate();
        KeyBindChangeInput.deactivate();
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

    public static void SettingsInput(){
        deactivateAll();
        SettingsInput.activate();
    }

    public static void keyBindChangeInput(KeyBindOption keyBindChange, int keyBindingIndex, KeyChangePromptPage kap){
        deactivateAll();
        KeyBindChangeInput.activate(keyBindChange, keyBindingIndex, kap);
    }
}
