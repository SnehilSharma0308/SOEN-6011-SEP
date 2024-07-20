import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * PowerCalculatorUI is a JavaFX application for computing the power function x^y.
 * It provides a user-friendly graphical interface for users to input the base (x) and exponent (y)
 * and computes the result, handling special cases and displaying errors as needed.
 */
public class PowerCalculatorUI extends Application {

    /**
     * The main entry point for all JavaFX applications. The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * @param primaryStage the primary stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("SOEN-6011 (Eternity)");

        // Create the UI components
        Label titleLabel = new Label("Exponent Calculator");
        titleLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        titleLabel.getStyleClass().add("title-label"); // Apply the new CSS class
        titleLabel.setAlignment(Pos.CENTER);

        Label equationLabel = new Label("F7 (xʸ) = ?");
        equationLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 24));
        equationLabel.setStyle("-fx-text-fill: #333333; -fx-padding: 10;");
        equationLabel.setAlignment(Pos.CENTER);

        Label labelX = new Label("x =");
        labelX.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        labelX.setStyle("-fx-text-fill: #555555;");
        TextField textFieldX = new TextField();
        textFieldX.setPromptText("base");

        Label labelY = new Label("y =");
        labelY.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        labelY.setStyle("-fx-text-fill: #555555;");
        TextField textFieldY = new TextField();
        textFieldY.setPromptText("exponent");

        Button clearButton = new Button("Clear");
        clearButton.getStyleClass().add("button");
        clearButton.getStyleClass().add("button-clear");

        Button calculateButton = new Button("Calculate");
        calculateButton.getStyleClass().add("button");
        calculateButton.getStyleClass().add("button-calculate");

        Label resultLabel = new Label("Result:");
        resultLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        resultLabel.setStyle("-fx-text-fill: #555555; -fx-padding: 5;");

        Label resultValueLabel = new Label();
        resultValueLabel.setFont(new Font("Arial", 16));
        resultValueLabel.setStyle("-fx-border-color: black; -fx-padding: 10px; -fx-min-width: 120px; -fx-min-height: 40px;");

        Label errorLabel = new Label();
        errorLabel.setFont(new Font("Arial", 16));
        errorLabel.setStyle("-fx-text-fill: red;"); // Apply style to error label

        // Add listeners to remove the invalid style when user starts typing
        textFieldX.textProperty().addListener((observable, oldValue, newValue) -> {
            textFieldX.getStyleClass().remove("text-field-invalid");
            errorLabel.setText(""); // Clear error message when typing
        });

        textFieldY.textProperty().addListener((observable, oldValue, newValue) -> {
            textFieldY.getStyleClass().remove("text-field-invalid");
            errorLabel.setText(""); // Clear error message when typing
        });

        // Add functionality to the buttons
        calculateButton.setOnAction(event -> {
            boolean validInput = true;
            resultValueLabel.setText(""); // Clear the result box

            try {
                double x = Double.parseDouble(textFieldX.getText());
                textFieldX.getStyleClass().remove("text-field-invalid"); // Remove invalid style if present
            } catch (NumberFormatException e) {
                textFieldX.getStyleClass().add("text-field-invalid"); // Add invalid style
                validInput = false;
            }

            try {
                double y = Double.parseDouble(textFieldY.getText());
                textFieldY.getStyleClass().remove("text-field-invalid"); // Remove invalid style if present
            } catch (NumberFormatException e) {
                textFieldY.getStyleClass().add("text-field-invalid"); // Add invalid style
                validInput = false;
            }

            if (validInput) {
                try {
                    double x = Double.parseDouble(textFieldX.getText());
                    double y = Double.parseDouble(textFieldY.getText());
                    double result = PowerCalculator.calculatePower(x, y);
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
            textFieldX.getStyleClass().remove("text-field-invalid");
            textFieldY.getStyleClass().remove("text-field-invalid");
        });

        // Set up the layout for the calculator
        GridPane inputGrid = new GridPane();
        inputGrid.setPadding(new Insets(20, 20, 20, 20));
        inputGrid.setVgap(15);
        inputGrid.setHgap(10);
        inputGrid.setAlignment(Pos.CENTER);

        inputGrid.add(labelX, 0, 0);
        inputGrid.add(textFieldX, 1, 0);
        inputGrid.add(labelY, 0, 1);
        inputGrid.add(textFieldY, 1, 1);

        HBox buttonsBox = new HBox(20, clearButton, calculateButton);
        buttonsBox.setAlignment(Pos.CENTER);

        HBox resultBox = new HBox(resultLabel, resultValueLabel);
        resultBox.setAlignment(Pos.CENTER);

        VBox calculatorLayout = new VBox(15, titleLabel, equationLabel, inputGrid, buttonsBox, resultBox, errorLabel);
        calculatorLayout.setAlignment(Pos.CENTER);
        calculatorLayout.setPadding(new Insets(20));
        calculatorLayout.getStyleClass().add("calculator-layout");

        // Set up the main layout with light orange background
        StackPane mainLayout = new StackPane();
        mainLayout.getChildren().add(calculatorLayout);
        mainLayout.getStyleClass().add("main-layout");

        Scene scene = new Scene(mainLayout, 500, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main method is ignored in correctly deployed JavaFX applications.
     * main() serves only as fallback in case the application cannot be launched through deployment artifacts, e.g., in IDEs with limited FX support.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
