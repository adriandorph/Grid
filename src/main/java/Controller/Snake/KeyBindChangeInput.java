package Controller.Snake;

import Controller.Controller;
import Model.Snake.IllegalKeyException;
import Model.Snake.KeyBindOption;
import Model.Snake.KeyBinding;
import Saves.Settings.KeyBindingSettings;
import View.Snake.Settings.KeyChangePromptPage;
import javafx.scene.input.KeyCode;

public class KeyBindChangeInput {
    private static boolean isActive = false;
    private static KeyBindOption keyBind;
    private static int keyBindingIndex;

    private static KeyChangePromptPage kap;
    public static boolean isActive() {
        return isActive;
    }

    public static void activate(KeyBindOption keyBind, int keyBindingIndex, KeyChangePromptPage kap) {
        isActive = true;
        KeyBindChangeInput.keyBind = keyBind;
        KeyBindChangeInput.keyBindingIndex = keyBindingIndex;
        KeyBindChangeInput.kap = kap;
    }

    public static void deactivate() {
        isActive = false;
    }

    public static void keyInput(KeyCode key){
        if(isActive() && keyBind != null) {
            KeyBinding updatedKeyBinding = KeyBindingSettings.readActiveKeyBinding();
            try{
                switch (keyBind) {
                    case NORTH -> updatedKeyBinding.setNorth(key);
                    case SOUTH -> updatedKeyBinding.setSouth(key);
                    case EAST -> updatedKeyBinding.setEast(key);
                    case WEST -> updatedKeyBinding.setWest(key);
                    case LEFT -> updatedKeyBinding.setLeft(key);
                    case RIGHT -> updatedKeyBinding.setRight(key);
                    case PAUSE -> updatedKeyBinding.setPause(key);
                    case RESTART -> updatedKeyBinding.setRestart(key);
                }
                KeyBindingSettings.updateActiveKeyBinding(updatedKeyBinding, keyBindingIndex);
                Controller.viewEditKeyBindingsMenu();
            } catch (IllegalKeyException e){
                kap.drawIllegalChoice();
            }
        }
    }
}
