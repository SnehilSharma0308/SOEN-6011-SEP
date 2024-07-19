import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PowerCalculatorUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Exponent Calculator");

        // Create the UI components
        Label titleLabel = new Label("Exponent Calculator");
        titleLabel.setFont(new Font("Arial", 20));
        titleLabel.setStyle("-fx-background-color: #d3d3d3; -fx-text-fill: #333333; -fx-padding: 10;");
        titleLabel.setAlignment(Pos.CENTER);

        Label equationLabel = new Label("xⁿ = ?");
        equationLabel.setFont(new Font("Arial", 24));
        equationLabel.setStyle("-fx-text-fill: #333333;");
        equationLabel.setAlignment(Pos.CENTER);

        Label labelX = new Label("x =");
        labelX.setFont(new Font("Arial", 16));
        labelX.setStyle("-fx-text-fill: #555555;");
        TextField textFieldX = new TextField();
        textFieldX.setPromptText("base");

        Label labelY = new Label("n =");
        labelY.setFont(new Font("Arial", 16));
        labelY.setStyle("-fx-text-fill: #555555;");
        TextField textFieldY = new TextField();
        textFieldY.setPromptText("exponent");

        Button clearButton = new Button("Clear");
        clearButton.setStyle("-fx-background-color: #f0ad4e; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px;");
        Button calculateButton = new Button("Calculate");
        calculateButton.setStyle("-fx-background-color: #5cb85c; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px;");

        Label resultLabel = new Label("Answer:");
        resultLabel.setFont(new Font("Arial", 16));
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

        // Set up the layout
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

        VBox mainLayout = new VBox(15, titleLabel, equationLabel, inputGrid, buttonsBox, resultBox);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(10));
        mainLayout.setStyle("-fx-background-color: #FFFFFF;"); // Light grey background

        Scene scene = new Scene(mainLayout, 350, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
