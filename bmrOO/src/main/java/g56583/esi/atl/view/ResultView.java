package g56583.esi.atl.view;

import g56583.esi.atl.DesignPattern.Observer;
import g56583.esi.atl.model.Calculate;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * The ResultView class extends GridPane and implements Observer to display
 * the BMR and daily calorie results. It observes changes in the Calculate model.
 */
public class ResultView extends GridPane implements Observer {
    private final TextField tfdBMR;
    private final TextField tfdCalories;
    private final Calculate calculateModel;

    /**
     * Constructor for ResultView. Sets up the UI components and layout.
     *
     * @param calculateModel The Calculate model to observe for changes.
     */
    public ResultView(Calculate calculateModel) {
        this.calculateModel = calculateModel;
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
     * Sets the BMR result in the BMR TextField.
     *
     * @param bmr The BMR result to display.
     */
    public void setBMR(String bmr) {
        tfdBMR.setText(bmr);
    }

    /**
     * Sets the daily calorie requirement result in the calorie TextField.
     *
     * @param calories The daily calorie requirement to display.
     */
    public void setCalories(String calories) {
        tfdCalories.setText(calories);
    }

    /**
     * Clears the BMR and calorie result TextFields.
     */
    public void clearFields() {
        tfdBMR.clear();
        tfdCalories.clear();
    }

    /**
     * Updates the display with the latest BMR and calorie results from the Calculate model.
     */
    @Override
    public void update() {
        tfdBMR.setText(String.format("%.2f", calculateModel.getBmr()));
        tfdCalories.setText(String.format("%.2f", calculateModel.getCalories()));
    }
}