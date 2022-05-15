package View.Snake.Settings;

import Model.ColorFunctions;
import Model.Snake.ColorScheme;
import Model.Snake.KeyBinding;
import Saves.Settings.ColorSettings;
import Saves.Settings.KeyBindingSettings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import Controller.Controller;

import static Controller.Controller.factor;
import static Controller.Controller.windowHeight;

public class EditKeyBindingsView extends SettingsPageView{

    private TextField keyBindingName;

    public EditKeyBindingsView() {
        super("Key Bindings");
        repaint();
    }

    public void repaint() {
        super.repaint(backButtonAction -> Controller.viewSettingsMenu());

        //Key binding picker
        super.gc.setFill(ColorSettings.getActiveColorScheme().getUI());
        super.gc.setTextAlign(TextAlignment.LEFT);
        super.gc.setFont(new Font("Roboto", 35 * factor));
        super.gc.fillText("Key Binding", 300 * factor, 175 * factor);

        ComboBox<KeyBinding> keyBindingsDropDown = new ComboBox<>();
        ObservableList<KeyBinding> keyBindingsList = keyBindingsDropDown.getItems();
        keyBindingsList.addAll(KeyBindingSettings.getKeyBindings());
        keyBindingsDropDown.getSelectionModel().select(0);
        for (int i = 0; i < keyBindingsList.size(); i++) {
            if (keyBindingsList.get(i).equals(KeyBindingSettings.getActiveKeyBinding())) {
                keyBindingsDropDown.getSelectionModel().select(i);
                break;
            }
        }
        keyBindingsDropDown.setStyle("-fx-font-size: " + 20 * factor + "px;");
        keyBindingsDropDown.setPrefWidth(factor * 300);
        keyBindingsDropDown.setTranslateY(factor * -195);
        keyBindingsDropDown.setTranslateX(factor * 175);
        keyBindingsDropDown.setOnAction(updateKeyBinding -> {
            KeyBindingSettings.setActiveKeyBinding(KeyBindingSettings.getKeyBinding(keyBindingsDropDown.getSelectionModel().getSelectedIndex()));
            repaint();
        });

        //Create new colorscheme
        Button createNewKeyBindingButton = new Button("New key binding");
        createNewKeyBindingButton.setTranslateY(factor * -135);
        createNewKeyBindingButton.setTranslateX(factor * 200);
        createNewKeyBindingButton.setPrefWidth(factor * 250);
        createNewKeyBindingButton.setPrefHeight(windowHeight * 0.05);
        createNewKeyBindingButton.setFont(new Font(factor * 30));
        createNewKeyBindingButton.setStyle(
                "-fx-font-size: " + factor * 20 + "px;" +
                        "-fx-text-fill: " + ColorScheme.toCssHexCode(ColorSettings.getActiveColorScheme().getBackground()) + ";" +
                        "-fx-background-color:" + ColorScheme.toCssHexCode(ColorSettings.getActiveColorScheme().getUI()) + ";"
        );
        createNewKeyBindingButton.setOnAction(createNewKeyBinding -> {
            KeyBinding newKeyBinding = new KeyBinding(KeyBindingSettings.getActiveKeyBinding(), KeyBindingSettings.getNewKeyBindingName());
            KeyBindingSettings.addKeyBinding(newKeyBinding);
            KeyBindingSettings.setActiveKeyBinding(newKeyBinding);
            repaint();
        });

        //Delete KeyBinding
        StackPane customizeKeyBinding = new StackPane();
        if (KeyBindingSettings.getActiveKeyBinding().isCustomizable()) {
            //DeleteButton
            Button deleteKeyBinding = new Button("Delete key binding");
            deleteKeyBinding.setTranslateY(factor * -135);
            deleteKeyBinding.setTranslateX(factor * -75);
            deleteKeyBinding.setPrefWidth(factor * 250);
            deleteKeyBinding.setPrefHeight(windowHeight * 0.05);
            deleteKeyBinding.setFont(new Font(factor * 30));
            deleteKeyBinding.setStyle(
                    "-fx-font-size: " + factor * 20 + "px;" +
                            "-fx-text-fill: " + ColorScheme.toCssHexCode(ColorSettings.getActiveColorScheme().getBackground()) + ";" +
                            "-fx-background-color:" + ColorScheme.toCssHexCode(ColorFunctions.opacityOnBackground(ColorSettings.getActiveColorScheme().getUI(), ColorSettings.getActiveColorScheme().getBackground(), 0.5)) + ";"
            );
            deleteKeyBinding.setOnAction(deleteActiveKeyBinding -> {
                KeyBindingSettings.deleteKeyBinding(keyBindingsDropDown.getSelectionModel().getSelectedIndex());
                KeyBindingSettings.setActiveKeyBinding(KeyBindingSettings.getKeyBinding(0));
                repaint();
            });

            //KeyBinding name
            super.gc.setFill(ColorSettings.getActiveColorScheme().getUI());
            super.gc.setTextAlign(TextAlignment.LEFT);
            super.gc.setFont(new Font("Roboto", 35 * factor));
            super.gc.fillText("Key Binding Name", 300 * factor, 300 * factor);

            keyBindingName = new TextField(KeyBindingSettings.getActiveKeyBinding().getName());
            keyBindingName.setStyle("-fx-font-size: " + factor * 20 + "px;");
            keyBindingName.setMaxWidth(factor * 200);
            keyBindingName.setPrefWidth(factor * 200);
            keyBindingName.setPrefHeight(factor * 30);
            keyBindingName.setTranslateX(factor * 225);
            keyBindingName.setTranslateY(factor * -70);
            keyBindingName.textProperty().addListener((ignored, oldValue, newValue) -> {
                String name = newValue;
                if (name.length() > 18) {
                    name = oldValue;
                    keyBindingName.setText(name);
                }
                KeyBinding keyBinding = KeyBindingSettings.readActiveKeyBinding();
                keyBinding.setName(name);
                KeyBindingSettings.updateActiveKeyBinding(keyBinding, keyBindingsDropDown.getSelectionModel().getSelectedIndex());
                repaint();
                keyBindingName.requestFocus();
                keyBindingName.end();
            });
            keyBindingName.setOnMouseClicked(caretAtEnd -> {
                keyBindingName.end();
            });

            customizeKeyBinding.getChildren().add(deleteKeyBinding);
            customizeKeyBinding.getChildren().add(keyBindingName);
        }

        //Assignments
        KeyBindSettingComponent north = new KeyBindSettingComponent(
                "North",
                KeyBindingSettings.getActiveKeyBinding().getNorth(),
                e -> {},
                KeyBindingSettings.getActiveKeyBinding().isCustomizable()
        );
        KeyBindSettingComponent south = new KeyBindSettingComponent(
                "South",
                KeyBindingSettings.getActiveKeyBinding().getSouth(),
                e -> {},
                KeyBindingSettings.getActiveKeyBinding().isCustomizable()
        );
        KeyBindSettingComponent east = new KeyBindSettingComponent(
                "East",
                KeyBindingSettings.getActiveKeyBinding().getEast(),
                e -> {},
                KeyBindingSettings.getActiveKeyBinding().isCustomizable()
        );
        KeyBindSettingComponent west = new KeyBindSettingComponent(
                "West",
                KeyBindingSettings.getActiveKeyBinding().getWest(),
                e -> {},
                KeyBindingSettings.getActiveKeyBinding().isCustomizable()
        );
        KeyBindSettingComponent left = new KeyBindSettingComponent(
                "Turn left",
                KeyBindingSettings.getActiveKeyBinding().getLeft(),
                e -> {},
                KeyBindingSettings.getActiveKeyBinding().isCustomizable()
        );
        KeyBindSettingComponent right = new KeyBindSettingComponent(
                "Turn right",
                KeyBindingSettings.getActiveKeyBinding().getRight(),
                e -> {},
                KeyBindingSettings.getActiveKeyBinding().isCustomizable()
        );
        KeyBindSettingComponent pause = new KeyBindSettingComponent(
                "Pause",
                KeyBindingSettings.getActiveKeyBinding().getPause(),
                e -> {},
                KeyBindingSettings.getActiveKeyBinding().isCustomizable()
        );
        KeyBindSettingComponent restart = new KeyBindSettingComponent(
                "Restart game",
                KeyBindingSettings.getActiveKeyBinding().getRestart(),
                e -> {},
                KeyBindingSettings.getActiveKeyBinding().isCustomizable()
        );

        VBox assignmentsVBox = new VBox();
        assignmentsVBox.getChildren().addAll(north, south, east, west, left, right, pause, restart);
        assignmentsVBox.setTranslateY(factor * 350);

        //Insert all
        super.gc.restore();
        getChildren().clear();
        getChildren().add(super.background);
        if (KeyBindingSettings.getActiveKeyBinding().isCustomizable()) getChildren().add(customizeKeyBinding);
        getChildren().add(super.backButton);
        getChildren().add(keyBindingsDropDown);
        getChildren().add(createNewKeyBindingButton);
        getChildren().add(assignmentsVBox);
    }
}
