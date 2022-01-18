package View;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class View extends Scene {
    public Grid grid;
    public View(StackPane pane){
        super(new Pane());
        getStylesheets().add("styling/buttons.css");
        viewPane(pane);
    }

    public void viewPane(StackPane pane){setRoot(pane);}
}
