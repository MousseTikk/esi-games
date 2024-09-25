package g56583.esi.atl.view;

import g56583.esi.atl.model.Calculate;
import g56583.esi.atl.model.LifeStyle;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The `App` class represents the main application for calculating Basal Metabolic Rate (BMR)
 * and daily calorie requirements based on user input and lifestyle choices.
 */
public class App extends Application {
    private final DataView dataView;
    private final ResultView resultsView;
    private final Button calculateButton;
    private final Button clearButton;
    private final Label errorLabel;

    /**
     * Constructor for the `App` class.
     */
    public App() {
        dataView = new DataView();
        resultsView = new ResultView();
        calculateButton = new Button("Calculer");
        clearButton = new Button("Effacer");
        errorLabel = new Label();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calcul du BMR");
        primaryStage.setResizable(false);

        MenuBar menuBar = new MenuBar();
        Menu fichierMenu = new Menu("Fichier");
        MenuItem quitterItem = new MenuItem("Quitter");
        fichierMenu.getItems().add(quitterItem);
        menuBar.getMenus().add(fichierMenu);
        quitterItem.setOnAction(e -> {
            primaryStage.close();
        });

        VBox mainContainer = new VBox(10);
        mainContainer.setPadding(new Insets(0, 20, 20, 20));

        HBox sidesBox = new HBox(20);


        calculateButton.setMaxWidth(Double.MAX_VALUE);
        clearButton.setMaxWidth(Double.MAX_VALUE);

        VBox buttonBox = new VBox(10, calculateButton, clearButton);

        sidesBox.getChildren().addAll(dataView, resultsView);
        mainContainer.getChildren().addAll(menuBar, sidesBox, buttonBox);

        clearButton.setOnAction(event -> {
            clearData();
        });

        calculateButton.setOnAction(event -> {
            calculateBMR();
        });

        mainContainer.getChildren().add(errorLabel);

        Scene scene = new Scene(mainContainer, 600, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Calculate the BMR based on user input and lifestyle choice.
     */
    private void calculateBMR() {
        try {
            double height = Double.parseDouble(dataView.getUserHeight());
            double weight = Double.parseDouble(dataView.getWeight());
            double age = Double.parseDouble(dataView.getAge());
            boolean isMale = dataView.isMaleSelected();
            String selectedLifestyle = dataView.getSelectedLifestyle();

            LifeStyle lifestyle = null;
            for (LifeStyle ls : LifeStyle.values()) {
                if (ls.name().equalsIgnoreCase(selectedLifestyle.replace(" ", "_"))) {
                    lifestyle = ls;
                    break;
                }
            }

            if (lifestyle != null) {
                if (height <= 0 || weight <= 0 || age <= 0) {
                    showError("Les champs ne peuvent pas être égaux à zéro.");
                } else {
                    double bmr = Calculate.calculateBMR(height, weight, age, isMale);
                    double calories = Calculate.calculateCalories(bmr, lifestyle);
                    resultsView.setBMR(String.format("%.2f", bmr));
                    resultsView.setCalories(String.format("%.2f", calories));
                }
            } else {
                showError("Style de vie non valide.");
            }
        } catch (NumberFormatException e) {
            showError("Veuillez entrer des valeurs numériques valides pour la taille, le poids et l'âge.");
        }
    }

    /**
     * Clear the different field of my application
     */
    private void clearData() {
        dataView.clearFields();
        resultsView.clearFields();
        errorLabel.setText("");
    }

    /**
     * Show the given message with a pop up
     *
     * @param message the given message
     */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}