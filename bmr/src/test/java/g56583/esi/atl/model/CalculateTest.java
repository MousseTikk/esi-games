package g56583.esi.atl.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculateTest {
    //13.7 ∗ 80.0 + 5 ∗ 180.0 − 6.8 ∗ 30.0 + 66
    @Test
    public void testCalculateBMR_Male() {
        double bmr = Calculate.calculateBMR(180.0, 80.0, 30.0, true);
        assertEquals(1858.0, bmr);
    }

    //9.6 ∗ 60.0 + 1.8 ∗ 160.0 − 4.7 ∗ 25.0 + 655
    @Test
    public void testCalculateBMR_Female() {
        double bmr = Calculate.calculateBMR(160.0, 60.0, 25.0, false);
        assertEquals(1401.5, bmr);
    }

    @Test
    public void testCalculateBMR_InvalidInput() {
        double bmr = Calculate.calculateBMR(0, 0, 0, true);
        assertEquals(0.0, bmr);
    }


    @Test
    public void testCalculateCalories_ZeroBMR() {
        double bmr = 0.0;
        double dailyCalories = Calculate.calculateCalories(bmr, LifeStyle.actif);
        assertEquals(0.0, dailyCalories);
    }


    @Test
    public void testCalculateCalories() {
        double bmr = 1700.0;
        double calories = Calculate.calculateCalories(bmr, LifeStyle.actif);
        assertEquals(2635.0, calories);
    }
    // This case isn't possible with filter you can't enter a negative value for the height, width, etc
    /**@Test public void testCalculateCalories_NegativeBMR() {
    double bmr = -1000.0;
    double dailyCalories = Calculate.calculateCalories(bmr, 1.55);
    assertEquals(0.0, dailyCalories); // Expected 0 daily calories for negative BMR
    }**/
}