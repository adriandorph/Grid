package View;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class View extends Scene {
    public View(){
        super(new Pane());
        getStylesheets().add("styling/menuButtons.css");

        //setRoot(mainMenu);
    }
}
