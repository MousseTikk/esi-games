package g56583.esi.atl.view;

import g56583.esi.atl.model.LifeStyle;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class DataView extends GridPane {
    private final TextField tfdTaille;
    private final TextField tfdPoids;
    private final TextField tfdAge;
    private final RadioButton rdSexeF;
    private final RadioButton rdSexeH;
    private final ChoiceBox<LifeStyle> cbStyle;

    /**
     * Constructor of DataView
     */
    public DataView() {
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(20, 10, 20, 10));

        Label datasTitle = new Label("Données");
        datasTitle.setUnderline(true);
        add(datasTitle, 0, 1);

        add(new Label("Taille (cm):"), 0, 2);
        tfdTaille = new TextField();
        tfdTaille.setPromptText("Taille en cm");
        add(tfdTaille, 1, 2);

        add(new Label("Poids (kg):"), 0, 3);
        tfdPoids = new TextField();
        tfdPoids.setPromptText("Poids en kg");
        add(tfdPoids, 1, 3);

        add(new Label("Âge (années):"), 0, 4);
        tfdAge = new TextField();
        tfdAge.setPromptText("Âge en années");
        add(tfdAge, 1, 4);

        add(new Label("Sexe:"), 0, 5);
        ToggleGroup sexGroup = new ToggleGroup();
        rdSexeF = new RadioButton("Femme");
        rdSexeF.setToggleGroup(sexGroup);
        rdSexeF.setSelected(true);
        rdSexeH = new RadioButton("Homme");
        rdSexeH.setToggleGroup(sexGroup);
        HBox sexBox = new HBox(10, rdSexeF, rdSexeH);
        add(sexBox, 1, 5);

        add(new Label("Style de vie:"), 0, 6);
        cbStyle = new ChoiceBox<>();
        cbStyle.getItems().addAll(LifeStyle.values());
        cbStyle.setValue(LifeStyle.sédentaire);
        add(cbStyle, 1, 6);

        Pattern decimalPattern = Pattern.compile("\\d*|\\d+\\.\\d*");
        UnaryOperator<TextFormatter.Change> filter = change -> {
            if (decimalPattern.matcher(change.getControlNewText()).matches()) {
                return change;
            }
            return null;
        };
        tfdTaille.setTextFormatter(new TextFormatter<>(filter));
        tfdPoids.setTextFormatter(new TextFormatter<>(filter));
        tfdAge.setTextFormatter(new TextFormatter<>(filter));
    }

    /**
     * Get the value of the height input field.
     *
     * @return The height input field's value.
     */
    public String getUserHeight() {
        return tfdTaille.getText();
    }

    /**
     * Get the value of the weight input field.
     *
     * @return The weight input field's value.
     */
    public String getWeight() {
        return tfdPoids.getText();
    }

    /**
     * Get the value of the age input field.
     *
     * @return The age input field's value.
     */
    public String getAge() {
        return tfdAge.getText();
    }

    /**
     * Get the female gender selection radio button.
     *
     * @return The female gender radio button.
     */
    public boolean isMaleSelected() {
        return rdSexeH.isSelected();
    }

    /**
     * Get the selected lifestyle choice from the dropdown.
     *
     * @return The selected lifestyle choice.
     */
    public LifeStyle getSelectedLifestyle() {
        return cbStyle.getValue();
    }
    /**
     * Clears all input fields and resets the form to its default state.
     * This method clears the text fields for height, weight, and age,
     * sets the female gender radio button to selected, and resets the
     * lifestyle choice to "Sédentaire".
     */
    public void clearFields() {
        tfdTaille.clear();
        tfdPoids.clear();
        tfdAge.clear();
        rdSexeF.setSelected(true);
        cbStyle.setValue(LifeStyle.sédentaire);
    }

}