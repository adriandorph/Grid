package View.Snake;

import Controller.Controller;
import Controller.Snake.SnakeInput;
import Model.Snake.ColorScheme;
import Saves.Settings;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class EscapeMenuView extends StackPane {
    public EscapeMenuView(){
        //Background
        Canvas background = new Canvas(Controller.windowWidth, Controller.windowHeight);
        GraphicsContext gc = background.getGraphicsContext2D();
        gc.setFill(Settings.getActiveColorScheme().getBackground());
        gc.fillRect(0,0, Controller.windowWidth, Controller.windowHeight);

        //Continue button
        Button continuebutton = new Button("Continue");
        continuebutton.setTranslateY(-Controller.windowHeight * 0.06);
        continuebutton.setPrefWidth(Controller.windowWidth * 0.2);
        continuebutton.setPrefHeight(Controller.windowHeight * 0.05);
        continuebutton.setFont(new Font(Controller.factor * 30));
        continuebutton.setStyle(
                "-fx-font-size: "+Controller.factor * 30+"px;" +
                        "-fx-text-fill: "+ ColorScheme.toCssHexCode(Settings.getActiveColorScheme().getBackground()) + ";"+
                        "-fx-background-color:" + ColorScheme.toCssHexCode(Settings.getActiveColorScheme().getUI()) +";"
        );
        continuebutton.setOnAction(e -> {
            Controller.viewExistingSnakeGame();
            SnakeInput.unpause();
        });

        //Exit button
        Button exit = new Button("Main menu");
        exit.setTranslateY(Controller.windowHeight * 0.06);
        exit.setPrefWidth(Controller.windowWidth * 0.2);
        exit.setPrefHeight(Controller.windowHeight * 0.05);
        exit.setFont(new Font(Controller.factor * 30));
        exit.setStyle(
                "-fx-font-size: "+Controller.factor * 30+"px;" +
                        "-fx-text-fill: "+ ColorScheme.toCssHexCode(Settings.getActiveColorScheme().getBackground()) + ";"+
                        "-fx-background-color:" + ColorScheme.toCssHexCode(Settings.getActiveColorScheme().getUI()) +";"
        );
        exit.setOnAction(e -> Controller.viewMainMenu());

        //Insert all
        getChildren().add(background);
        getChildren().add(continuebutton);
        getChildren().add(exit);
    }
}
