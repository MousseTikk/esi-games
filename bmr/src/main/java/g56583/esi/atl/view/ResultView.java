package g56583.esi.atl.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * The {@code ResultView} class extends {@link GridPane} and is used to display
 * the results of the BMR and daily calorie calculations. It contains text fields
 * to show the BMR and calorie results, which are not editable by the user.
 */
public class ResultView extends GridPane {

    private final TextField tfdBMR;
    private final TextField tfdCalories;

    /**
     * Constructs a new object ResultView with a predefined layout and components.
     * It sets up the grid, labels, and text fields for displaying the BMR and calorie
     * results.
     */
    public ResultView() {
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(20, 10, 20, 10));

        Label resultsTitle = new Label("Résultats");
        resultsTitle.setUnderline(true);
        add(resultsTitle, 0, 1);

        add(new Label("BMR:"), 0, 2);
        tfdBMR = new TextField();
        tfdBMR.setPromptText("Résultats du BMR");
        tfdBMR.setEditable(false);
        add(tfdBMR, 1, 2);

        add(new Label("Calories:"), 0, 3);
        tfdCalories = new TextField();
        tfdCalories.setEditable(false);
        tfdCalories.setPromptText("Dépense en calories");
        add(tfdCalories, 1, 3);
    }

    /**
     * Sets the BMR result in the BMR text field.
     *
     * @param bmr The calculated BMR to display.
     */
    public void setBMR(String bmr) {
        tfdBMR.setText(bmr);
    }

    /**
     * Sets the daily calorie requirement result in the calorie text field.
     *
     * @param calories The calculated daily calorie requirement to display.
     */
    public void setCalories(String calories) {
        tfdCalories.setText(calories);
    }

    /**
     * Clears the BMR and calorie result text fields.
     */
    public void clearFields() {
        tfdBMR.clear();
        tfdCalories.clear();
    }
}