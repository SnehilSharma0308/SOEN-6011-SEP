import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class PowerCalculatorUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Exponent Calculator");

        // Create the UI components
        Label titleLabel = new Label("Exponent Calculator");
        titleLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        titleLabel.setStyle("-fx-background-color: #d3d3d3; -fx-text-fill: #333333; -fx-padding: 20;");
        titleLabel.setAlignment(Pos.CENTER);

        Label equationLabel = new Label("xʸ = ?");
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

        Label resultLabel = new Label("Answer:");
        resultLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        resultLabel.setStyle("-fx-text-fill: #555555; -fx-padding: 5;");

        Label resultValueLabel = new Label();
        resultValueLabel.setFont(new Font("Arial", 16));
        resultValueLabel.setStyle("-fx-border-color: black; -fx-padding: 10px; -fx-min-width: 120px; -fx-min-height: 40px;");

        // Add functionality to the buttons
        calculateButton.setOnAction(event -> {
            try {
                double x = Double.parseDouble(textFieldX.getText());
                double y = Double.parseDouble(textFieldY.getText());
                double result = PowerCalculator.calculatePower(x, y);
                resultValueLabel.setText(String.valueOf(result));
            } catch (NumberFormatException e) {
                resultValueLabel.setText("Invalid input!");
            } catch (Exception e) {
                resultValueLabel.setText("Error");
            }
        });

        clearButton.setOnAction(event -> {
            textFieldX.clear();
            textFieldY.clear();
            resultValueLabel.setText("");
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

        VBox calculatorLayout = new VBox(15, titleLabel, equationLabel, inputGrid, buttonsBox, resultBox);
        calculatorLayout.setAlignment(Pos.CENTER);
        calculatorLayout.setPadding(new Insets(20));
        calculatorLayout.getStyleClass().add("calculator-layout");

        // Set up the main layout with orange background
        StackPane mainLayout = new StackPane();
        mainLayout.getChildren().add(calculatorLayout);
        mainLayout.getStyleClass().add("main-layout");

        Scene scene = new Scene(mainLayout, 500, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
