package View.Snake.Settings;

import Controller.Controller;
import Model.ColorFunctions;
import Model.Snake.ColorScheme;
import Saves.Settings;
import View.Snake.ArrowCanvas;
import View.Snake.ImageButton;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import static Controller.Controller.factor;
import static Controller.Controller.windowHeight;
import static Controller.Controller.windowWidth;

public class SettingsMenuView extends SettingsTabView {
    public SettingsMenuView(){
        super("Settings");
        repaint();
    }

    @Override
    public void repaint() {
        super.repaint();
        super.backButton.setOnAction(e -> Controller.viewMainMenu());

        //startUpInGame
        super.gc.setFill(Settings.getActiveColorScheme().getUI());
        super.gc.setTextAlign(TextAlignment.LEFT);
        super.gc.setFont(new Font("Roboto", 35 * factor));
        super.gc.fillText("Startup in-game", 300 * factor, 175 * factor);

        CheckBox startUpInGameBox = new CheckBox();
        boolean tooDark = Settings.getActiveColorScheme().getUI().getBrightness() <= 0.2;
        startUpInGameBox.setStyle(
                "-fx-font-size: " + factor * 30 + "px;" +
                        "-fx-background-color:" + (tooDark ? "#505050" : ColorScheme.toCssHexCode(Settings.getActiveColorScheme().getUI())) + ";" +
                        "-fx-border-color: " + ColorScheme.toCssHexCode(Settings.getActiveColorScheme().getUI()) + ";"
        ); //The font sets the size of the checkbox, because setPrefSize does not work
        startUpInGameBox.setOnAction(e -> Settings.saveStartUpInGame(startUpInGameBox.isSelected()));
        startUpInGameBox.setSelected(Settings.getStartUpInGame());
        startUpInGameBox.setTranslateY(factor * -195);
        startUpInGameBox.setTranslateX(factor * 300);

        //Color scheme picker
        super.gc.setFill(Settings.getActiveColorScheme().getUI());
        super.gc.setTextAlign(TextAlignment.LEFT);
        super.gc.setFont(new Font("Roboto", 35 * factor));
        super.gc.fillText("Color Scheme", 300 * factor, 225 * factor);

        ComboBox<ColorScheme> colorSchemesDropDown = new ComboBox<>();
        ObservableList<ColorScheme> colorSchemesList = colorSchemesDropDown.getItems();
        colorSchemesList.addAll(Settings.getColorSchemes());
        colorSchemesDropDown.getSelectionModel().select(0);
        for(int i = 0; i<colorSchemesList.size(); i++){
            if (colorSchemesList.get(i).equals(Settings.getActiveColorScheme())){
                colorSchemesDropDown.getSelectionModel().select(i);
                break;
            }
        }
        colorSchemesDropDown.setStyle("-fx-font-size: " + 20 * factor + "px;");
        colorSchemesDropDown.setPrefWidth(factor * 300);
        colorSchemesDropDown.setTranslateY(factor * -145);
        colorSchemesDropDown.setTranslateX(factor * 175);
        colorSchemesDropDown.setOnAction(updateColorScheme -> {
            Settings.setActiveColorScheme(Settings.getColorScheme(colorSchemesDropDown.getSelectionModel().getSelectedIndex()));
            repaint();
        });

        //Edit color schemes
        Button editColorSchemesButton = new Button("Edit color schemes");
        editColorSchemesButton.setTranslateY(factor * -85);
        editColorSchemesButton.setTranslateX(factor * 200);
        editColorSchemesButton.setPrefWidth(factor * 250);
        editColorSchemesButton.setPrefHeight(windowHeight * 0.05);
        editColorSchemesButton.setFont(new Font(factor * 30));
        editColorSchemesButton.setStyle(
                "-fx-font-size: " + factor * 20 + "px;" +
                        "-fx-text-fill: " + ColorScheme.toCssHexCode(Settings.getActiveColorScheme().getBackground()) + ";" +
                        "-fx-background-color:" + ColorScheme.toCssHexCode(Settings.getActiveColorScheme().getUI()) + ";"
        );
        editColorSchemesButton.setOnAction(createNewColoScheme -> Controller.viewEditColorSchemesMenu());

        //Insert all
        super.gc.restore();
        getChildren().clear();
        getChildren().add(super.background);
        getChildren().add(super.backButton);
        getChildren().add(startUpInGameBox);
        getChildren().add(colorSchemesDropDown);
        getChildren().add(editColorSchemesButton);
    }
}
