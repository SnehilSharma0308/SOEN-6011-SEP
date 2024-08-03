package com.sep.f7;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import java.io.InputStream;

import static com.sep.f7.UIConstants.DEFAULT_FONT;
import static com.sep.f7.UIConstants.GRID_PADDING;
import static com.sep.f7.UIConstants.TEXT_FIELD_INVALID;
import static com.sep.f7.UIConstants.TITLE_FONT_SIZE;
import static com.sep.f7.UIConstants.EQUATION_FONT_SIZE;
import static com.sep.f7.UIConstants.LABEL_FONT_SIZE;
import static com.sep.f7.UIConstants.RESULT_FONT_SIZE;
import static com.sep.f7.UIConstants.RESULT_MIN_HEIGHT;
import static com.sep.f7.UIConstants.RESULT_MIN_WIDTH;
import static com.sep.f7.UIConstants.ERROR_FONT_SIZE;
import static com.sep.f7.UIConstants.GRID_HGAP;
import static com.sep.f7.UIConstants.GRID_VGAP;
import static com.sep.f7.UIConstants.BUTTON_SPACING;
import static com.sep.f7.UIConstants.CALCULATOR_PADDING;
import static com.sep.f7.UIConstants.SCENE_HEIGHT;
import static com.sep.f7.UIConstants.SCENE_WIDTH;

/**
 * PowerCalculatorUI is a JavaFX application for computing the power function x^y.
 * It provides a user-friendly graphical interface for users to input the base (x) and exponent (y),
 * and computes the result, handling special cases and displaying errors as needed.
 */
public class PowerCalculatorUI extends Application {

    private Scene scene;
    private static final String VOICE_NAME = "kevin16";

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("SOEN-6011 (Eternity)");

        // Create the UI components
        Label titleLabel = new Label("Exponent Calculator");
        titleLabel.setFont(Font.font(DEFAULT_FONT, FontWeight.NORMAL, TITLE_FONT_SIZE));
        titleLabel.getStyleClass().add("title-label");
        titleLabel.setAlignment(Pos.CENTER);

        Label equationLabel = new Label("F7 (xʸ) = ?");
        equationLabel.setFont(Font.font(DEFAULT_FONT, FontWeight.NORMAL, EQUATION_FONT_SIZE));
        equationLabel.setStyle("-fx-text-fill: #333333; -fx-padding: 10;");
        equationLabel.setAlignment(Pos.CENTER);

        Label labelX = new Label("x =");
        labelX.setFont(Font.font(DEFAULT_FONT, FontWeight.BOLD, LABEL_FONT_SIZE));
        labelX.setStyle("-fx-text-fill: #555555;");
        TextField textFieldX = new TextField();
        textFieldX.setPromptText("base");

        Label labelY = new Label("y =");
        labelY.setFont(Font.font(DEFAULT_FONT, FontWeight.BOLD, LABEL_FONT_SIZE));
        labelY.setStyle("-fx-text-fill: #555555;");
        TextField textFieldY = new TextField();
        textFieldY.setPromptText("exponent");

        Button clearButton = new Button("Clear");
        clearButton.getStyleClass().add("button");
        clearButton.getStyleClass().add("button-clear");

        Button calculateButton = new Button("Calculate");
        calculateButton.getStyleClass().add("button");
        calculateButton.getStyleClass().add("button-calculate");

        Button toggleContrastButton = new Button("Toggle High Contrast");
        toggleContrastButton.getStyleClass().add("toggle-button");
        toggleContrastButton.setOnAction(event -> {
            if (scene.getStylesheets().contains(getClass().getResource("/com/sep/f7/high-contrast.css").toExternalForm())) {
                scene.getStylesheets().remove(getClass().getResource("/com/sep/f7/high-contrast.css").toExternalForm());
                scene.getStylesheets().add(getClass().getResource("/com/sep/f7/style.css").toExternalForm());
            } else {
                scene.getStylesheets().remove(getClass().getResource("/com/sep/f7/style.css").toExternalForm());
                scene.getStylesheets().add(getClass().getResource("/com/sep/f7/high-contrast.css").toExternalForm());
            }
        });

        Label resultLabel = new Label("Result:");
        resultLabel.setFont(Font.font(DEFAULT_FONT, FontWeight.BOLD, RESULT_FONT_SIZE));
        resultLabel.setStyle("-fx-text-fill: #555555; -fx-padding: 5;");

        Label resultValueLabel = new Label();
        resultValueLabel.setFont(new Font(DEFAULT_FONT, RESULT_FONT_SIZE));
        resultValueLabel.setStyle("-fx-border-color: black; -fx-padding: 10px; -fx-min-width: " + RESULT_MIN_WIDTH + "px; -fx-min-height: " + RESULT_MIN_HEIGHT + "px;");

        Button speakButton = new Button("");
        InputStream iconStream = getClass().getResourceAsStream("/com/sep/f7/speaker1.png");
        Image iconImage = new Image(iconStream);
        ImageView iconView = new ImageView(iconImage);
        speakButton.setGraphic(iconView);
        iconView.setFitWidth(24);
        iconView.setFitHeight(24);
        speakButton.getStyleClass().add("speaker-button");
        speakButton.setOnAction(event -> speakResult(resultValueLabel.getText()));

        Label errorLabel = new Label();
        errorLabel.setFont(new Font(DEFAULT_FONT, ERROR_FONT_SIZE));
        errorLabel.setStyle("-fx-text-fill: red;");

        // Add listeners to remove the invalid style when user starts typing
        textFieldX.textProperty().addListener((observable, oldValue, newValue) -> {
            textFieldX.getStyleClass().remove(TEXT_FIELD_INVALID);
            errorLabel.setText(""); // Clear error message when typing
        });

        textFieldY.textProperty().addListener((observable, oldValue, newValue) -> {
            textFieldY.getStyleClass().remove(TEXT_FIELD_INVALID);
            errorLabel.setText(""); // Clear error message when typing
        });

        // Add functionality to the buttons
        calculateButton.setOnAction(event -> {
            boolean validInput = true;
            resultValueLabel.setText(""); // Clear the result box

            try {
                Double.parseDouble(textFieldX.getText());
                textFieldX.getStyleClass().remove(TEXT_FIELD_INVALID);
            } catch (NumberFormatException e) {
                textFieldX.getStyleClass().add(TEXT_FIELD_INVALID);
                validInput = false;
            }

            try {
                Double.parseDouble(textFieldY.getText());
                textFieldY.getStyleClass().remove(TEXT_FIELD_INVALID);
            } catch (NumberFormatException e) {
                textFieldY.getStyleClass().add(TEXT_FIELD_INVALID);
                validInput = false;
            }

            if (validInput) {
                try {
                    double x = Double.parseDouble(textFieldX.getText());
                    double y = Double.parseDouble(textFieldY.getText());
                    PowerCalculator powerCalculator = new PowerCalculator();
                    double result = powerCalculator.calculatePower(x, y);
                    resultValueLabel.setText(String.valueOf(result));
                    errorLabel.setText(""); // Clear any previous error message
                } catch (Exception e) {
                    errorLabel.setText("Error: " + e.getMessage());
                }
            } else {
                errorLabel.setText("Invalid input! Please enter valid real numbers.");
            }
        });

        clearButton.setOnAction(event -> {
            textFieldX.clear();
            textFieldY.clear();
            resultValueLabel.setText("");
            errorLabel.setText(""); // Clear any previous error message
            textFieldX.getStyleClass().remove(TEXT_FIELD_INVALID);
            textFieldY.getStyleClass().remove(TEXT_FIELD_INVALID);
        });

        // Set up the layout for the calculator
        GridPane inputGrid = new GridPane();
        inputGrid.setPadding(new Insets(GRID_PADDING, GRID_PADDING, GRID_PADDING, GRID_PADDING));
        inputGrid.setVgap(GRID_VGAP);
        inputGrid.setHgap(GRID_HGAP);
        inputGrid.setAlignment(Pos.CENTER);

        inputGrid.add(labelX, 0, 0);
        inputGrid.add(textFieldX, 1, 0);
        inputGrid.add(labelY, 0, 1);
        inputGrid.add(textFieldY, 1, 1);

        HBox buttonsBox = new HBox(BUTTON_SPACING, clearButton, calculateButton);
        buttonsBox.setAlignment(Pos.CENTER);

        HBox resultBox = new HBox(10, resultLabel, resultValueLabel, speakButton); // Added spacing of 10 between elements
        resultBox.setAlignment(Pos.CENTER);

        VBox calculatorLayout = new VBox(GRID_PADDING, titleLabel, equationLabel, inputGrid, buttonsBox, resultBox, errorLabel, toggleContrastButton);
        calculatorLayout.setAlignment(Pos.CENTER);
        calculatorLayout.setPadding(new Insets(CALCULATOR_PADDING));
        calculatorLayout.getStyleClass().add("calculator-layout");

        // Set up the main layout with light orange background
        StackPane mainLayout = new StackPane();
        mainLayout.getChildren().add(calculatorLayout);
        mainLayout.getStyleClass().add("main-layout");

        scene = new Scene(mainLayout, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("/com/sep/f7/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void speakResult(String text) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice voice = voiceManager.getVoice(VOICE_NAME);
        if (voice != null) {
            voice.allocate();
            voice.speak(text);
            voice.deallocate();
        } else {
            System.err.println("Voice not found: " + VOICE_NAME);
        }
    }

    public static void main(final String[] args) {
        launch(args);
    }
}
