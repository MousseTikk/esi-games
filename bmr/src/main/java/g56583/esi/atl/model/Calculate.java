package g56583.esi.atl.model;

/**
 * The `Calculate` class provides methods for calculating BMR and
 * daily calorie requirements based on user information and lifestyle choices.
 */
public class Calculate {
    /**
     * Calculates the BMR based on user information.
     *
     * @param height The user's height in centimeters.
     * @param weight The user's weight in kilograms.
     * @param age    The user's age in years.
     * @param isMale `true` if the user is male, `false` if the user is female.
     * @return The calculated BMR value.
     */
    public static double calculateBMR(double height, double weight, double age, boolean isMale) {
        if (height <= 0 || weight <= 0 || age <= 0) {
            return 0;
        }

        if (isMale) {
            return (13.7 * weight + 5 * height - 6.8 * age + 66);
        } else {
            return (9.6 * weight + 1.8 * height - 4.7 * age + 655);
        }
    }

    /**
     * Calculates daily calorie requirements based on BMR and lifestyle choice.
     *
     * @param bmr       The Basal Metabolic Rate (BMR) calculated using `calculateBMR`.
     * @param lifeStyle The user's chosen lifestyle from the `LifeStyle` enum.
     * @return The calculated daily calorie requirement.
     */
    public static double calculateCalories(double bmr, LifeStyle lifeStyle) {
        return (bmr * lifeStyle.getValue());
    }

}