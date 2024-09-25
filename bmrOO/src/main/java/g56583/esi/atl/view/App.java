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
 * The App class is the main entry point for the BMR (Basal Metabolic Rate) calculation application.
 * It sets up the user interface and handles user interactions.
 */
public class App extends Application {
    private final DataView dataView;
    private final ResultView resultsView;
    private final Button calculateButton;
    private final Button clearButton;
    private final Label errorLabel;

    private final Calculate calculateModel = new Calculate();

    /**
     * Constructor for App class. Initializes the views and controls.
     */
    public App() {
        dataView = new DataView();
        resultsView = new ResultView(calculateModel);
        calculateButton = new Button("Calculer");
        clearButton = new Button("Effacer");
        errorLabel = new Label();
        calculateModel.addObserver(resultsView);
    }

    /**
     * Main method to launch the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the application and sets up the primary stage with its components.
     *
     * @param primaryStage The primary stage for this application.
     */
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
     * Calculates the BMR based on the user's input and selected lifestyle.
     */
    private void calculateBMR() {
        try {
            double height = Double.parseDouble(dataView.getUserHeight());
            double weight = Double.parseDouble(dataView.getWeight());
            double age = Double.parseDouble(dataView.getAge());
            boolean isMale = dataView.isMaleSelected();
            LifeStyle lifestyle = dataView.getSelectedLifestyle();

            if (height <= 0 || weight <= 0 || age <= 0) {
                showError("Les champs ne peuvent pas être égaux à zéro.");
            } else {
                calculateModel.calculateAndUpdate(height, weight, age, isMale, lifestyle);
            }
        } catch (NumberFormatException e) {
            showError("Veuillez entrer des valeurs numériques valides pour la taille, le poids et l'âge.");
        }
    }
    /**
     * Clears all input fields and result displays.
     */
    private void clearData() {
        dataView.clearFields();
        resultsView.clearFields();
        errorLabel.setText("");
    }

    /**
     * Displays an error message to the user in a dialog box.
     *
     * @param message The error message to be displayed.
     */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}