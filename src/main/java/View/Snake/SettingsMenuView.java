package View.Snake;

import Controller.Controller;
import Model.ColorFunctions;
import Model.Snake.ColorScheme;
import Saves.Settings;
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

public class SettingsMenuView extends StackPane {
    TextField colorSchemeName;
    public SettingsMenuView(){
        repaint();
    }

    public void repaint() {
        Canvas background = new Canvas(windowWidth, windowHeight);
        GraphicsContext gc = background.getGraphicsContext2D();
        gc.setFill(Settings.getActiveColorScheme().getBackground());
        gc.fillRect(0, 0, windowWidth, windowHeight);

        //Settings text
        gc.setFill(Settings.getActiveColorScheme().getUI());
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(new Font("Roboto", 80 * factor));
        gc.fillText("Settings", background.getWidth() / 2, 90 * factor);

        //BackArrowButton
        ImageButton backButton = new ImageButton(ArrowCanvas.getArrowCanvas((int) (80 * factor), (int) (50 * factor)));
        backButton.setPrefWidth(windowWidth * 0.075);
        backButton.setPrefHeight(windowHeight * 0.05);
        backButton.setTranslateX(factor * -450);
        backButton.setTranslateY(factor * -300);
        backButton.setOnAction(e -> Controller.viewMainMenu());

        //startUpInGame
        gc.setFill(Settings.getActiveColorScheme().getUI());
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setFont(new Font("Roboto", 35 * factor));
        gc.fillText("Startup in-game", 300 * factor, 175 * factor);

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
        gc.setFill(Settings.getActiveColorScheme().getUI());
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setFont(new Font("Roboto", 35 * factor));
        gc.fillText("Color Scheme", 300 * factor, 225 * factor);

        ComboBox<ColorScheme> colorSchemesDropDown = new ComboBox<>();
        ObservableList<ColorScheme> colorSchemesList = colorSchemesDropDown.getItems();
        colorSchemesList.addAll(Settings.getColorSchemes());
        colorSchemesDropDown.getSelectionModel().select(Settings.getActiveColorScheme());
        colorSchemesDropDown.setStyle("-fx-font-size: " + 20 * factor + "px;");
        colorSchemesDropDown.setPrefWidth(factor * 300);
        colorSchemesDropDown.setTranslateY(factor * -145);
        colorSchemesDropDown.setTranslateX(factor * 175);
        colorSchemesDropDown.setOnAction(updateColorScheme -> {
            Settings.setActiveColorScheme(Settings.getColorScheme(colorSchemesDropDown.getSelectionModel().getSelectedIndex()));
            repaint();
        });

        //Create new colorscheme
        Button createNewColorSchemeButton = new Button("New color scheme");
        createNewColorSchemeButton.setTranslateY(factor * -85);
        createNewColorSchemeButton.setTranslateX(factor * 200);
        createNewColorSchemeButton.setPrefWidth(factor * 250);
        createNewColorSchemeButton.setPrefHeight(windowHeight * 0.05);
        createNewColorSchemeButton.setFont(new Font(factor * 30));
        createNewColorSchemeButton.setStyle(
                "-fx-font-size: " + factor * 25 + "px;" +
                        "-fx-text-fill: " + ColorScheme.toCssHexCode(Settings.getActiveColorScheme().getBackground()) + ";" +
                        "-fx-background-color:" + ColorScheme.toCssHexCode(Settings.getActiveColorScheme().getUI()) + ";"
        );
        createNewColorSchemeButton.setOnAction(createNewColoScheme -> {
            ColorScheme newColorScheme = new ColorScheme(Settings.getActiveColorScheme(), Settings.getNewColorSchemeName());
            Settings.addColorScheme(newColorScheme);
            Settings.setActiveColorScheme(newColorScheme);
            repaint();
        });

        //Delete colorscheme
        StackPane customizeColorscheme = new StackPane();
        if (Settings.getActiveColorScheme().isCustomizable()) {
            //DeleteButton
            Button deleteColorScheme = new Button("Delete color scheme");
            deleteColorScheme.setTranslateY(factor * -85);
            deleteColorScheme.setTranslateX(factor * -75);
            deleteColorScheme.setPrefWidth(factor * 250);
            deleteColorScheme.setPrefHeight(windowHeight * 0.05);
            deleteColorScheme.setFont(new Font(factor * 30));
            deleteColorScheme.setStyle(
                    "-fx-font-size: " + factor * 25 + "px;" +
                            "-fx-text-fill: " + ColorScheme.toCssHexCode(Settings.getActiveColorScheme().getBackground()) + ";" +
                            "-fx-background-color:" + ColorScheme.toCssHexCode(ColorFunctions.opacityOnBackground(Settings.getActiveColorScheme().getUI(), Settings.getActiveColorScheme().getBackground(), 0.5)) + ";"
            );
            deleteColorScheme.setOnAction(deleteActiveColorScheme -> {
                Settings.deleteColorScheme(colorSchemesDropDown.getSelectionModel().getSelectedIndex());
                Settings.setActiveColorScheme(Settings.getColorScheme(0));
                repaint();
            });
            //Color scheme name
            gc.setFill(Settings.getActiveColorScheme().getUI());
            gc.setTextAlign(TextAlignment.LEFT);
            gc.setFont(new Font("Roboto", 35 * factor));
            gc.fillText("Color scheme name", 300 * factor, 350 * factor);

            colorSchemeName = new TextField(Settings.getActiveColorScheme().getName());
            colorSchemeName.setStyle("-fx-font-size: " + factor * 20 + "px;");
            colorSchemeName.setMaxWidth(factor * 200);
            colorSchemeName.setPrefWidth(factor * 200);
            colorSchemeName.setPrefHeight(factor * 30);
            colorSchemeName.setTranslateX(factor * 225);
            colorSchemeName.setTranslateY(factor * -20);
            colorSchemeName.textProperty().addListener((ignored, oldValue, newValue) -> {
                String name = newValue;
                if (name.length() > 18) {
                    name = oldValue;
                    colorSchemeName.setText(name);
                }
                ColorScheme colorScheme = Settings.getActiveColorScheme();
                colorScheme.setName(name);//How and why the hell are you supposed to use getters and setter if private fields still can be changed outside the class
                Settings.setActiveColorScheme(Settings.readActiveColorScheme()); // This is needed because Settings.activeColorScheme also changes when colorScheme is changed
                Settings.updateActiveColorScheme(colorScheme, colorSchemesDropDown.getSelectionModel().getSelectedIndex());
                repaint();
                colorSchemeName.requestFocus();
                colorSchemeName.end();
            });
            colorSchemeName.setOnMouseClicked(caretAtEnd -> {
                colorSchemeName.end();
            });


            //UI
            gc.setFill(Settings.getActiveColorScheme().getUI());
            gc.setTextAlign(TextAlignment.LEFT);
            gc.setFont(new Font("Roboto", 35 * factor));
            gc.fillText("UI color", 300 * factor, 400 * factor);

            ColorPicker UIPicker = new ColorPicker(Settings.getActiveColorScheme().getUI());
            UIPicker.setTranslateX(factor * 230);
            UIPicker.setTranslateY(factor * 30);
            UIPicker.setStyle("-fx-font-size: " + factor * 20 + "px;");
            UIPicker.setOnAction(updateUIColor -> {
                ColorScheme colorScheme = Settings.getActiveColorScheme();
                colorScheme.setUI(UIPicker.getValue());
                Settings.updateActiveColorScheme(colorScheme, colorSchemesDropDown.getSelectionModel().getSelectedIndex());
                repaint();
            });

            //Background
            gc.setFill(Settings.getActiveColorScheme().getUI());
            gc.setTextAlign(TextAlignment.LEFT);
            gc.setFont(new Font("Roboto", 35 * factor));
            gc.fillText("Background color", 300 * factor, 450 * factor);

            ColorPicker backgroundPicker = new ColorPicker(Settings.getActiveColorScheme().getBackground());
            backgroundPicker.setTranslateX(factor * 230);
            backgroundPicker.setTranslateY(factor * 80);
            backgroundPicker.setStyle("-fx-font-size: " + factor * 20 + "px;");
            backgroundPicker.setOnAction(updateBackgroundColor -> {
                ColorScheme colorScheme = Settings.getActiveColorScheme();
                colorScheme.setBackground(backgroundPicker.getValue());
                Settings.updateActiveColorScheme(colorScheme, colorSchemesDropDown.getSelectionModel().getSelectedIndex());
                repaint();
            });

            //Head
            gc.setFill(Settings.getActiveColorScheme().getUI());
            gc.setTextAlign(TextAlignment.LEFT);
            gc.setFont(new Font("Roboto", 35 * factor));
            gc.fillText("Snake head color", 300 * factor, 500 * factor);

            ColorPicker headPicker = new ColorPicker(Settings.getActiveColorScheme().getHead());
            headPicker.setTranslateX(factor * 230);
            headPicker.setTranslateY(factor * 130);
            headPicker.setStyle("-fx-font-size: " + factor * 20 + "px;");
            headPicker.setOnAction(updateHeadColor -> {
                ColorScheme colorScheme = Settings.getActiveColorScheme();
                colorScheme.setHead(headPicker.getValue());
                Settings.updateActiveColorScheme(colorScheme, colorSchemesDropDown.getSelectionModel().getSelectedIndex());
                repaint();
            });

            //Tail
            gc.setFill(Settings.getActiveColorScheme().getUI());
            gc.setTextAlign(TextAlignment.LEFT);
            gc.setFont(new Font("Roboto", 35 * factor));
            gc.fillText("Snake tail color", 300 * factor, 550 * factor);

            ColorPicker tailPicker = new ColorPicker(Settings.getActiveColorScheme().getTail());
            tailPicker.setTranslateX(factor * 230);
            tailPicker.setTranslateY(factor * 180);
            tailPicker.setStyle("-fx-font-size: " + factor * 20 + "px;");
            tailPicker.setOnAction(updateTailColor -> {
                ColorScheme colorScheme = Settings.getActiveColorScheme();
                colorScheme.setTail(tailPicker.getValue());
                Settings.updateActiveColorScheme(colorScheme, colorSchemesDropDown.getSelectionModel().getSelectedIndex());
                repaint();
            });

            //Bits
            gc.setFill(Settings.getActiveColorScheme().getUI());
            gc.setTextAlign(TextAlignment.LEFT);
            gc.setFont(new Font("Roboto", 35 * factor));
            gc.fillText("Food color", 300 * factor, 600 * factor);

            ColorPicker bitsPicker = new ColorPicker(Settings.getActiveColorScheme().getBits());
            bitsPicker.setTranslateX(factor * 230);
            bitsPicker.setTranslateY(factor * 230);
            bitsPicker.setStyle("-fx-font-size: " + factor * 20 + "px;");
            bitsPicker.setOnAction(updateBitsColor -> {
                ColorScheme colorScheme = Settings.getActiveColorScheme();
                colorScheme.setBits(bitsPicker.getValue());
                Settings.updateActiveColorScheme(colorScheme, colorSchemesDropDown.getSelectionModel().getSelectedIndex());
                repaint();
            });

            //Info
            gc.setFill(Settings.getActiveColorScheme().getUI());
            gc.setTextAlign(TextAlignment.LEFT);
            gc.setFont(new Font("Roboto", 35 * factor));
            gc.fillText("Info text color", 300 * factor, 650 * factor);

            ColorPicker infoPicker = new ColorPicker(Settings.getActiveColorScheme().getInfo());
            infoPicker.setTranslateX(factor * 230);
            infoPicker.setTranslateY(factor * 280);
            infoPicker.setStyle("-fx-font-size: " + factor * 20 + "px;");
            infoPicker.setOnAction(updateInfoColor -> {
                ColorScheme colorScheme = Settings.getActiveColorScheme();
                colorScheme.setInfo(infoPicker.getValue());
                Settings.updateActiveColorScheme(colorScheme, colorSchemesDropDown.getSelectionModel().getSelectedIndex());
                repaint();
            });

            customizeColorscheme.getChildren().add(deleteColorScheme);
            customizeColorscheme.getChildren().add(colorSchemeName);
            customizeColorscheme.getChildren().add(UIPicker);
            customizeColorscheme.getChildren().add(backgroundPicker);
            customizeColorscheme.getChildren().add(headPicker);
            customizeColorscheme.getChildren().add(tailPicker);
            customizeColorscheme.getChildren().add(bitsPicker);
            customizeColorscheme.getChildren().add(infoPicker);
        }

        //Insert all
        gc.restore();
        getChildren().clear();
        getChildren().add(background);
        if (Settings.getActiveColorScheme().isCustomizable()) getChildren().add(customizeColorscheme);
        getChildren().add(backButton);
        getChildren().add(startUpInGameBox);
        getChildren().add(colorSchemesDropDown);
        getChildren().add(createNewColorSchemeButton);
    }
}
