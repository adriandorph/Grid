package View.Snake;

import Controller.Controller;
import Model.Snake.ColorScheme;
import Saves.Settings;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.image.ImageView;

public class SettingsMenuView extends StackPane {
    public SettingsMenuView(){
        //Background
        Canvas background = new Canvas(Controller.windowWidth, Controller.windowHeight);
        GraphicsContext gc = background.getGraphicsContext2D();
        gc.setFill(Settings.getActiveColorScheme().getBackground());
        gc.fillRect(0,0, Controller.windowWidth, Controller.windowHeight);

        //Settings text
        gc.setFill(Settings.getActiveColorScheme().getUI());
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(new Font("Roboto", 80 * Controller.factor));
        gc.fillText("Settings", background.getWidth() / 2,90 * Controller.factor);

        //startUpInGame
        gc.setFill(Settings.getActiveColorScheme().getUI());
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setFont(new Font("Roboto", 35 * Controller.factor));
        gc.fillText("Startup in-game", 300 * Controller.factor,200 * Controller.factor);

        CheckBox startUpInGameBox = new CheckBox();
        boolean tooDark = Settings.getActiveColorScheme().getUI().getBrightness() <= 0.2;
        startUpInGameBox.setStyle(
                "-fx-font-size: "+Controller.factor * 30+"px;" +
                        "-fx-background-color:" + (tooDark? "#505050": ColorScheme.toCssHexCode(Settings.getActiveColorScheme().getUI())) +";"+
                        "-fx-border-color: "+ ColorScheme.toCssHexCode(Settings.getActiveColorScheme().getUI())+ ";"
        ); //The font s ets the size of the checkbox, because setPrefSize does not work
        startUpInGameBox.setOnAction(e -> Settings.saveStartUpInGame(startUpInGameBox.isSelected()));
        startUpInGameBox.setSelected(Settings.getStartUpInGame());
        startUpInGameBox.setTranslateY(Controller.factor * -170);
        startUpInGameBox.setTranslateX(Controller.factor * 300);

        //BackArrowButton
        ImageButton backButton = new ImageButton(ArrowCanvas.getArrowCanvas((int)(80 * Controller.factor), (int)(50 * Controller.factor)));
        backButton.setPrefWidth(Controller.windowWidth * 0.075);
        backButton.setPrefHeight(Controller.windowHeight * 0.05);
        backButton.setTranslateX(Controller.factor * - 450);
        backButton.setTranslateY(Controller.factor * - 300);
        backButton.setOnAction(e -> Controller.viewMainMenu());

        //Insert all
        gc.restore();
        getChildren().add(background);
        getChildren().add(backButton);
        getChildren().add(startUpInGameBox);
    }
}
