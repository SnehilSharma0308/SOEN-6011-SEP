import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PowerCalculatorUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Exponent Calculator");

        // Create the UI components
        Label titleLabel = new Label("Exponent Calculator");
        titleLabel.setFont(new Font("Arial", 20));
        titleLabel.setStyle("-fx-background-color: #CD5C5C; -fx-text-fill: white; -fx-padding: 10;");

        Label equationLabel = new Label("xⁿ = ?");
        equationLabel.setFont(new Font("Arial", 24));
        equationLabel.setAlignment(Pos.CENTER);

        Label labelX = new Label("x =");
        TextField textFieldX = new TextField();
        textFieldX.setPromptText("base");

        Label labelY = new Label("n =");
        TextField textFieldY = new TextField();
        textFieldY.setPromptText("exponent");

        Button clearButton = new Button("Clear");
        Button calculateButton = new Button("Calculate");
        Label resultLabel = new Label("Answer:");
        resultLabel.setFont(new Font("Arial", 16));
        resultLabel.setPadding(new Insets(10));

        // Add functionality to the buttons
        calculateButton.setOnAction(event -> {
            try {
                double x = Double.parseDouble(textFieldX.getText());
                double y = Double.parseDouble(textFieldY.getText());
                double result = PowerCalculator.calculatePower(x, y);
                resultLabel.setText("Answer: " + result);
            } catch (NumberFormatException e) {
                resultLabel.setText("Invalid input! Please enter valid numeric values.");
            } catch (Exception e) {
                resultLabel.setText("Error: " + e.getMessage());
            }
        });

        clearButton.setOnAction(event -> {
            textFieldX.clear();
            textFieldY.clear();
            resultLabel.setText("Answer:");
        });

        // Set up the layout
        GridPane grid = new GridPane();

        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(15);
        grid.setHgap(10);

        grid.add(titleLabel, 0, 0, 5, 1);
        grid.add(equationLabel, 1, 1, 2, 1);
        GridPane.setHalignment(equationLabel, HPos.CENTER);
        grid.add(labelX, 0, 2);
        grid.add(textFieldX, 1, 2);
        grid.add(labelY, 0, 3);
        grid.add(textFieldY, 1, 3);

        HBox buttonsBox = new HBox(35, clearButton, calculateButton);
        buttonsBox.setAlignment(Pos.CENTER);
        grid.add(buttonsBox, 0, 4, 2, 1);
        GridPane.setHalignment(buttonsBox, HPos.CENTER);

        grid.add(resultLabel, 0, 5, 2, 1);

        Scene scene = new Scene(grid, 250, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
