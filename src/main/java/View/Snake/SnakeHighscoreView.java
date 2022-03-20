package View.Snake;

import Controller.Controller;
import Model.Snake.ColorScheme;
import Saves.Settings;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SnakeHighscoreView extends StackPane {

    public SnakeHighscoreView(SnakeHighscoreCanvas hc){

        //Background
        Canvas background = new Canvas(Controller.windowWidth, Controller.windowHeight);
        GraphicsContext gc = background.getGraphicsContext2D();
        gc.setFill(Settings.getActiveColorScheme().getBackground());
        gc.fillRect(0,0, Controller.windowWidth, Controller.windowHeight);
        gc.restore();

        //OK button
        Button okbutton = new Button("OK");
        okbutton.setTranslateY(Controller.windowHeight/3.75);
        okbutton.setPrefWidth(Controller.windowWidth * 0.2);
        okbutton.setPrefHeight(Controller.windowHeight * 0.05);
        okbutton.setFont(new Font(Controller.factor * 30));
        okbutton.setStyle(
                "-fx-font-size: "+Controller.factor * 30+"px;" +
                        "-fx-text-fill: "+ ColorScheme.toCssHexCode(Settings.getActiveColorScheme().getBackground()) + ";"+
                        "-fx-background-color:" + ColorScheme.toCssHexCode(Settings.getActiveColorScheme().getUI()) +";"
        );
        okbutton.setOnAction(e -> Controller.viewMainMenu());

        getChildren().add(background);
        getChildren().add(hc);
        getChildren().add(okbutton);
    }
}
