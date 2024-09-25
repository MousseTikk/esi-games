package g56583.atl.ascii.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CircleTest {

    @Test
    public void testCreateCircle() {
        System.out.println("testCreateCircle");
        Circle circle = new Circle(new Point(0, 0), 5.0, 'R');
        assertEquals(0, circle.getCenter().getX());
        assertEquals(0, circle.getCenter().getY());
        assertEquals(5.0, circle.getRadius());
        assertEquals('R', circle.getColor());
    }
    @Test
    public void testMoveCircle() {
        System.out.println("testMoveCircle");
        Circle circle = new Circle(new Point(0, 0), 5.0, 'R');
        circle.move(2.0, 3.0);
        assertEquals(2.0, circle.getCenter().getX());
        assertEquals(3.0, circle.getCenter().getY());
    }
    @Test
    public void testIsPointInsideCircle() {
        System.out.println("testIsPointInsideCircle");
        Circle circle = new Circle(new Point(3, 4), 5.0, 'R');
        assertTrue(circle.isInside(new Point(5, 4)));
        assertFalse(circle.isInside(new Point(10, 10)));
    }
    @Test
    public void testCreateCircleNullCenter() {
        System.out.println("testCreateCircleNullCenter");
        assertThrows(IllegalArgumentException.class, () -> new Circle(null, 5.0, 'R'));
    }
    @Test
    public void testCreateCircleNegativeRadius() {
        System.out.println("testCreateCircleNegativeRadius");
        assertThrows(IllegalArgumentException.class, () -> new Circle(new Point(0, 0), -5.0, 'R'));
    }
    @Test
    public void testIsInsideNull(){
        System.out.println("testIsInsideNull");
        Circle circle = new Circle(new Point(0, 0), 5.0, 'R');
        assertThrows(IllegalArgumentException.class, () -> circle.isInside(null));
    }
    @Test
    public void testCopy() {
        Circle originalCircle = new Circle(new Point(3, 4), 5.0, 'R');
        Circle copiedCircle = originalCircle.copy();
        assertEquals(originalCircle.getCenter(), copiedCircle.getCenter());
        assertEquals(originalCircle.getRadius(), copiedCircle.getRadius());
        assertEquals(originalCircle.getColor(), copiedCircle.getColor());

        assertNotSame(originalCircle, copiedCircle);
    }
}