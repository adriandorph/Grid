package View.Snake.Settings;

import Controller.Controller;
import Model.Snake.ColorScheme;
import Model.Snake.KeyBinding;
import Saves.Settings.ColorSettings;
import Saves.Settings.KeyBindingSettings;
import Saves.Settings.StartUpSettings;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import static Controller.Controller.factor;
import static Controller.Controller.windowHeight;

public class SettingsMenuView extends SettingsPageView {
    public SettingsMenuView(){
        super("Settings");
        repaint();
    }

    public void repaint() {
        super.repaint(backButtonAction -> Controller.viewMainMenu());

        //startUpInGame
        super.gc.setFill(ColorSettings.getActiveColorScheme().getUI());
        super.gc.setTextAlign(TextAlignment.LEFT);
        super.gc.setFont(new Font("Roboto", 35 * factor));
        super.gc.fillText("Startup in-game", 300 * factor, 175 * factor);

        CheckBox startUpInGameBox = new CheckBox();
        boolean tooDark = ColorSettings.getActiveColorScheme().getUI().getBrightness() <= 0.2;
        startUpInGameBox.setStyle(
                "-fx-font-size: " + factor * 30 + "px;" +
                        "-fx-background-color:" + (tooDark ? "#505050" : ColorScheme.toCssHexCode(ColorSettings.getActiveColorScheme().getUI())) + ";" +
                        "-fx-border-color: " + ColorScheme.toCssHexCode(ColorSettings.getActiveColorScheme().getUI()) + ";"
        ); //The font sets the size of the checkbox, because setPrefSize does not work
        startUpInGameBox.setOnAction(e -> StartUpSettings.saveStartUpInGame(startUpInGameBox.isSelected()));
        startUpInGameBox.setSelected(StartUpSettings.getStartUpInGame());
        startUpInGameBox.setTranslateY(factor * -195);
        startUpInGameBox.setTranslateX(factor * 300);

        //Color scheme picker
        super.gc.setFill(ColorSettings.getActiveColorScheme().getUI());
        super.gc.setTextAlign(TextAlignment.LEFT);
        super.gc.setFont(new Font("Roboto", 35 * factor));
        super.gc.fillText("Color Scheme", 300 * factor, 235 * factor);

        ComboBox<ColorScheme> colorSchemesDropDown = new ComboBox<>();
        ObservableList<ColorScheme> colorSchemesList = colorSchemesDropDown.getItems();
        colorSchemesList.addAll(ColorSettings.getColorSchemes());
        colorSchemesDropDown.getSelectionModel().select(0);
        for(int i = 0; i<colorSchemesList.size(); i++){
            if (colorSchemesList.get(i).equals(ColorSettings.getActiveColorScheme())){
                colorSchemesDropDown.getSelectionModel().select(i);
                break;
            }
        }
        colorSchemesDropDown.setStyle("-fx-font-size: " + 20 * factor + "px;");
        colorSchemesDropDown.setPrefWidth(factor * 300);
        colorSchemesDropDown.setTranslateY(factor * -135);
        colorSchemesDropDown.setTranslateX(factor * 175);
        colorSchemesDropDown.setOnAction(updateColorScheme -> {
            ColorSettings.setActiveColorScheme(ColorSettings.getColorScheme(colorSchemesDropDown.getSelectionModel().getSelectedIndex()));
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
                        "-fx-text-fill: " + ColorScheme.toCssHexCode(ColorSettings.getActiveColorScheme().getBackground()) + ";" +
                        "-fx-background-color:" + ColorScheme.toCssHexCode(ColorSettings.getActiveColorScheme().getUI()) + ";"
        );
        editColorSchemesButton.setOnAction(createNewColoScheme -> Controller.viewEditColorSchemesMenu());

        //Key bindings
        super.gc.setFill(ColorSettings.getActiveColorScheme().getUI());
        super.gc.setTextAlign(TextAlignment.LEFT);
        super.gc.setFont(new Font("Roboto", 35 * factor));
        super.gc.fillText("Key Bindings", 300 * factor, 345 * factor);

        ComboBox<KeyBinding> keyBindingsDropDown = new ComboBox<>();
        ObservableList<KeyBinding> keyBindingsList = keyBindingsDropDown.getItems();
        keyBindingsList.addAll(KeyBindingSettings.getKeyBindings());
        keyBindingsDropDown.getSelectionModel().select(0);
        for(int i = 0; i<keyBindingsList.size(); i++){
            if (keyBindingsList.get(i).equals(KeyBindingSettings.getActiveKeyBinding())){
                keyBindingsDropDown.getSelectionModel().select(i);
                break;
            }
        }
        keyBindingsDropDown.setStyle("-fx-font-size: " + 20 * factor + "px;");
        keyBindingsDropDown.setPrefWidth(factor * 300);
        keyBindingsDropDown.setTranslateY(factor * -25);
        keyBindingsDropDown.setTranslateX(factor * 175);
        keyBindingsDropDown.setOnAction(updateKeyBinding -> {
            KeyBindingSettings.setActiveKeyBinding(KeyBindingSettings.getKeyBinding(keyBindingsDropDown.getSelectionModel().getSelectedIndex()));
            repaint();
        });

        //Edit key bindings
        Button editKeyBindingsButton = new Button("Edit key bindings");
        editKeyBindingsButton.setTranslateY(factor * 25);
        editKeyBindingsButton.setTranslateX(factor * 200);
        editKeyBindingsButton.setPrefWidth(factor * 250);
        editKeyBindingsButton.setPrefHeight(windowHeight * 0.05);
        editKeyBindingsButton.setFont(new Font(factor * 30));
        editKeyBindingsButton.setStyle(
                "-fx-font-size: " + factor * 20 + "px;" +
                        "-fx-text-fill: " + ColorScheme.toCssHexCode(ColorSettings.getActiveColorScheme().getBackground()) + ";" +
                        "-fx-background-color:" + ColorScheme.toCssHexCode(ColorSettings.getActiveColorScheme().getUI()) + ";"
        );
        editKeyBindingsButton.setOnAction(createNewColoScheme -> Controller.viewEditKeyBindingsMenu());

        //Insert all
        super.gc.restore();
        getChildren().clear();
        getChildren().add(super.background);
        getChildren().add(super.backButton);
        getChildren().add(startUpInGameBox);
        getChildren().add(colorSchemesDropDown);
        getChildren().add(editColorSchemesButton);
        getChildren().add(keyBindingsDropDown);
        getChildren().add(editKeyBindingsButton);
    }
}
