package View.Snake;

import Controller.Controller;
import Model.ColorFunctions;
import Model.Snake.ColorScheme;
import Saves.Settings;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import static Controller.Controller.factor;
import static Controller.Controller.windowHeight;
import static Controller.Controller.windowWidth;

public class SettingsMenuView extends StackPane {
    public SettingsMenuView(){
        repaint();
        //Background
    }

    public void repaint(){
        Canvas background = new Canvas(windowWidth, windowHeight);
        GraphicsContext gc = background.getGraphicsContext2D();
        gc.setFill(Settings.getActiveColorScheme().getBackground());
        gc.fillRect(0,0, windowWidth, windowHeight);

        //Settings text
        gc.setFill(Settings.getActiveColorScheme().getUI());
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(new Font("Roboto", 80 * factor));
        gc.fillText("Settings", background.getWidth() / 2,90 * factor);

        //BackArrowButton
        ImageButton backButton = new ImageButton(ArrowCanvas.getArrowCanvas((int)(80 * factor), (int)(50 * factor)));
        backButton.setPrefWidth(windowWidth * 0.075);
        backButton.setPrefHeight(windowHeight * 0.05);
        backButton.setTranslateX(factor * - 450);
        backButton.setTranslateY(factor * - 300);
        backButton.setOnAction(e -> Controller.viewMainMenu());

        //startUpInGame
        gc.setFill(Settings.getActiveColorScheme().getUI());
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setFont(new Font("Roboto", 35 * factor));
        gc.fillText("Startup in-game", 300 * factor,200 * factor);

        CheckBox startUpInGameBox = new CheckBox();
        boolean tooDark = Settings.getActiveColorScheme().getUI().getBrightness() <= 0.2;
        startUpInGameBox.setStyle(
                "-fx-font-size: "+factor * 30+"px;" +
                        "-fx-background-color:" + (tooDark? "#505050": ColorScheme.toCssHexCode(Settings.getActiveColorScheme().getUI())) +";"+
                        "-fx-border-color: "+ ColorScheme.toCssHexCode(Settings.getActiveColorScheme().getUI())+ ";"
        ); //The font sets the size of the checkbox, because setPrefSize does not work
        startUpInGameBox.setOnAction(e -> Settings.saveStartUpInGame(startUpInGameBox.isSelected()));
        startUpInGameBox.setSelected(Settings.getStartUpInGame());
        startUpInGameBox.setTranslateY(factor * -170);
        startUpInGameBox.setTranslateX(factor * 300);

        //Color scheme picker
        gc.setFill(Settings.getActiveColorScheme().getUI());
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setFont(new Font("Roboto", 35 * factor));
        gc.fillText("Color Scheme", 300 * factor,275 * factor);

        ComboBox<String> colorSchemesDropDown = new ComboBox<>();
        ObservableList<String> colorSchemesList = colorSchemesDropDown.getItems();
        for (ColorScheme cs: Settings.getColorSchemes()){
            colorSchemesList.add(cs.getName());
        }
        colorSchemesDropDown.setValue(Settings.getActiveColorScheme().getName());
        colorSchemesDropDown.setStyle("-fx-font-size: "+ 25 * factor+"px;");
        colorSchemesDropDown.setPrefWidth(factor * 250);
        colorSchemesDropDown.setTranslateY(factor * -95);
        colorSchemesDropDown.setTranslateX(factor * 200);
        colorSchemesDropDown.setOnAction(updateColorScheme -> {
            Settings.setActiveColorScheme(Settings.getColorScheme(colorSchemesDropDown.getValue()));
            repaint();
        });

        //Create new colorscheme
        Button createNewColorSchemeButton = new Button("new colorscheme");
        createNewColorSchemeButton.setTranslateY(factor * - 20);
        createNewColorSchemeButton.setTranslateX(factor * 212.5);
        createNewColorSchemeButton.setPrefWidth(factor * 225);
        createNewColorSchemeButton.setPrefHeight(windowHeight * 0.05);
        createNewColorSchemeButton.setFont(new Font(factor * 30));
        createNewColorSchemeButton.setStyle(
                "-fx-font-size: "+ factor * 25+"px;" +
                        "-fx-text-fill: "+ ColorScheme.toCssHexCode(Settings.getActiveColorScheme().getBackground()) + ";"+
                        "-fx-background-color:" + ColorScheme.toCssHexCode(Settings.getActiveColorScheme().getUI()) +";"
        );
        createNewColorSchemeButton.setOnAction(createNewColoScheme -> {
            ColorScheme newColorScheme = new ColorScheme(Settings.getActiveColorScheme(), "new colorscheme");
            Settings.addColorScheme(newColorScheme);
            Settings.setActiveColorScheme(newColorScheme);
            repaint();
        });

        //Delete colorscheme
        Button deleteColorScheme = new Button("delete colorscheme");
        if (Settings.getActiveColorScheme().isCustomizable()){
            deleteColorScheme.setTranslateY(factor * - 20);
            deleteColorScheme.setTranslateX(factor * - 50);
            deleteColorScheme.setPrefWidth(factor * 250);
            deleteColorScheme.setPrefHeight(windowHeight * 0.05);
            deleteColorScheme.setFont(new Font(factor * 30));
            deleteColorScheme.setStyle(
                    "-fx-font-size: "+ factor * 25+"px;" +
                            "-fx-text-fill: "+ ColorScheme.toCssHexCode(Settings.getActiveColorScheme().getBackground()) + ";"+
                            "-fx-background-color:" + ColorScheme.toCssHexCode(ColorFunctions.opacityOnBackground(Settings.getActiveColorScheme().getUI(), Settings.getActiveColorScheme().getBackground(), 0.5)) +";"
            );
            deleteColorScheme.setOnAction( deleteActiveColorScheme -> {
                Settings.deleteColorScheme(Settings.getActiveColorScheme().getName());
                Settings.setActiveColorScheme(Settings.getColorScheme(0));
                repaint();
            });
        }

        //Insert all
        gc.restore();
        getChildren().clear();
        getChildren().add(background);
        getChildren().add(backButton);
        getChildren().add(startUpInGameBox);
        getChildren().add(colorSchemesDropDown);
        getChildren().add(createNewColorSchemeButton);
        if (Settings.getActiveColorScheme().isCustomizable()) getChildren().add(deleteColorScheme);
    }
}
