package View;

import Controller.Controller;
import Model.Matrix;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class View extends Scene {
    public Grid grid;
    public View(){
        super(new Pane());
        //getStylesheets().add("styling/menuButtons.css");
        StackPane sp = new StackPane();
        grid = new Grid(Controller.windowWidth, Controller.windowHeight);
        sp.getChildren().add(grid);
        setRoot(sp);
        Matrix squares = new Matrix(16, 9);
        grid.render(new RenderGrid(squares));
    }
}
