package g56583.esi.atl.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LifeStyleTest {
    @Test
    public void testGetValueSedentary() {
        double value = LifeStyle.sédentaire.getValue();
        assertEquals(1.2, value);
    }

    @Test
    public void testGetValueActive() {
        double value = LifeStyle.actif.getValue();
        assertEquals(1.55, value);
    }

    @Test
    public void testGetValueExtremelyActive() {
        double value = LifeStyle.extrêmement_actif.getValue();
        assertEquals(1.9, value);
    }

    @Test
    public void testGetValueStronglyActive() {
        double value = LifeStyle.fort_actif.getValue();
        assertEquals(1.725, value);
    }

    @Test
    public void testGetValueNotVeryActive() {
        double value = LifeStyle.peu_actif.getValue();
        assertEquals(1.375, value);
    }
}