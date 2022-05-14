package View.Snake;

import Controller.Controller;
import Model.Snake.ColorScheme;
import Saves.Settings.ColorSettings;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class SnakeMainMenuView extends StackPane {

    public SnakeMainMenuView(){
        //Background
        Canvas background = new Canvas(Controller.windowWidth, Controller.windowHeight);
        GraphicsContext gc = background.getGraphicsContext2D();
        gc.setFill(ColorSettings.getActiveColorScheme().getBackground());
        gc.fillRect(0,0, Controller.windowWidth, Controller.windowHeight);

        //Snake text
        gc.setFill(ColorSettings.getActiveColorScheme().getUI());
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(new Font("Roboto", 200 * Controller.factor));
        gc.fillText("Snake", background.getWidth() / 2,background.getHeight() / 2 - 100 * Controller.factor);
        gc.restore();

        //Play button
        Button play = new Button("Play");
        play.setTranslateY(Controller.windowHeight * 0.07);
        play.setPrefWidth(Controller.windowWidth * 0.2);
        play.setPrefHeight(Controller.windowHeight * 0.05);
        play.setFont(new Font(Controller.factor * 30));
        play.setStyle(
                "-fx-font-size: "+Controller.factor * 30+"px;" +
                "-fx-text-fill: "+ ColorScheme.toCssHexCode(ColorSettings.getActiveColorScheme().getBackground()) + ";"+
                "-fx-background-color:" + ColorScheme.toCssHexCode(ColorSettings.getActiveColorScheme().getUI()) +";"
        );
        play.setOnAction(e -> Controller.viewNewSnakeGame());

        //Settings button
        Button settings = new Button("Settings");
        settings.setTranslateY(Controller.windowHeight * 0.18);
        settings.setPrefWidth(Controller.windowWidth * 0.2);
        settings.setPrefHeight(Controller.windowHeight * 0.05);
        settings.setFont(new Font(Controller.factor * 30));
        settings.setStyle(
                "-fx-font-size: "+Controller.factor * 30+"px;" +
                        "-fx-text-fill: "+ ColorScheme.toCssHexCode(ColorSettings.getActiveColorScheme().getBackground()) + ";"+
                        "-fx-background-color:" + ColorScheme.toCssHexCode(ColorSettings.getActiveColorScheme().getUI()) +";"
        );
        settings.setOnAction(e -> Controller.viewSettingsMenu());

        //Exit button
        Button exit = new Button("Exit");
        exit.setTranslateY(Controller.windowHeight * 0.29);
        exit.setPrefWidth(Controller.windowWidth * 0.2);
        exit.setPrefHeight(Controller.windowHeight * 0.05);
        exit.setFont(new Font(Controller.factor * 30));
        exit.setStyle(
                "-fx-font-size: "+Controller.factor * 30+"px;" +
                        "-fx-text-fill: "+ ColorScheme.toCssHexCode(ColorSettings.getActiveColorScheme().getBackground()) + ";"+
                        "-fx-background-color:" + ColorScheme.toCssHexCode(ColorSettings.getActiveColorScheme().getUI()) +";"
        );
        exit.setOnAction(e -> Controller.exit());

        //Insert all
        getChildren().add(background);
        getChildren().add(play);
        getChildren().add(settings);
        getChildren().add(exit);
    }
}
