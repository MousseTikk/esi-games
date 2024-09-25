package g56583.esi.atl.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculateTest {
    //13.7 ∗ 80.0 + 5 ∗ 180.0 − 6.8 ∗ 30.0 + 66
    @Test
    public void testCalculateBMR_Male() {
        Calculate calculate = new Calculate();
        calculate.calculateAndUpdate(180.0, 80.0, 30.0,true,LifeStyle.peu_actif);
        assertEquals(1858, calculate.getBmr());
        assertEquals(2554.75,calculate.getCalories());
    }

    //9.6 ∗ 60.0 + 1.8 ∗ 160.0 − 4.7 ∗ 25.0 + 655
    @Test
    public void testCalculateBMR_Female() {
        Calculate calculate = new Calculate();
        calculate.calculateAndUpdate(160.0, 60.0, 25.00,false,LifeStyle.peu_actif);
        assertEquals(1401.50, calculate.getBmr());
        assertEquals(1927.06,calculate.getCalories(),0.1);
    }

    @Test
    public void testCalculateBMR_InvalidInput() {
        Calculate calculate = new Calculate();
        calculate.calculateAndUpdate(0, 0, 0,false,LifeStyle.peu_actif);
        assertEquals(0.0, calculate.getBmr());
        assertEquals(0.0,calculate.getCalories());
    }






    // This case isn't possible with filter you can't enter a negative value for the height, width, etc
  /*  @Test public void testCalculateCalories_NegativeBMR() {
    double bmr = -1000.0;
    double dailyCalories = Calculate.calculateCalories(bmr, LifeStyle.actif);
    assertEquals(0.0, dailyCalories); // Expected 0 daily calories for negative BMR
    }*/
}