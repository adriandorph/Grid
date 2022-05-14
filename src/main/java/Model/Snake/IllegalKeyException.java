package Model.Snake;

import javafx.scene.input.KeyCode;

public class IllegalKeyException extends Exception {
    public IllegalKeyException(KeyCode keyCode, String message){
        super("Illegal key: "+ keyCode + ". " + message);
    }

    public IllegalKeyException(KeyCode keyCode){
        super("Illegal key: "+ keyCode);
    }
}
