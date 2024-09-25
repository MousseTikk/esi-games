package g56583.esi.atl.model;

/**
 * The `LifeStyle` enum provides pre-defined lifestyle choices with associated activity
 * level values for use in calorie calculations.
 */
public enum LifeStyle {
    sédentaire(1.2),
    peu_actif(1.375),
    actif(1.55),
    fort_actif(1.725),
    extrêmement_actif(1.9);

    private final double value;

    /**
     * Constructor
     *
     * @param value
     */
    LifeStyle(double value) {
        this.value = value;
    }

    /**
     * Gets the activity level value associated with the lifestyle choice.
     *
     * @return The activity level value.
     */
    public double getValue() {
        return value;
    }
    @Override
    public String toString() {
        switch (this) {
            case sédentaire: return "Sédentaire";
            case peu_actif: return "Peu Actif";
            case actif: return "Actif";
            case fort_actif: return "Fort Actif";
            case extrêmement_actif: return "Extrêmement Actif";
            default: throw new IllegalArgumentException();
        }
    }
}