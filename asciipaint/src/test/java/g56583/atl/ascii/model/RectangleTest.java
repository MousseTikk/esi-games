package g56583.atl.ascii.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {
    @Test
    public void testConstructorAndGetters() {
        System.out.println("testConstructorAndGetters");
        Point upperLeft = new Point(2.0, 3.0);
        Rectangle rectangle = new Rectangle(upperLeft, 4.0, 5.0, 'R');

        assertEquals(upperLeft, rectangle.getUpperLeft());
        assertEquals(4.0, rectangle.getWidth(), 0.01);
        assertEquals(5.0, rectangle.getHeight(), 0.01);
        assertEquals('R', rectangle.getColor());
    }

    @Test
    public void testIsInside() {
        System.out.println("testIsInside");
        Point upperLeft = new Point(2.0, 3.0);
        Rectangle rectangle = new Rectangle(upperLeft, 4.0, 5.0, 'R');

        Point insidePoint = new Point(3.0, 4.0);
        Point outsidePoint = new Point(1.0, 2.0);

        assertTrue(rectangle.isInside(insidePoint));
        assertFalse(rectangle.isInside(outsidePoint));
    }

    @Test
    public void testMove() {
        System.out.println("testMove");
        Point upperLeft = new Point(2.0, 3.0);
        Rectangle rectangle = new Rectangle(upperLeft, 4.0, 5.0, 'R');

        rectangle.move(1.0, 2.0);

        assertEquals(3.0, rectangle.getUpperLeft().getX(), 0.01);
        assertEquals(5.0, rectangle.getUpperLeft().getY(), 0.01);
    }

    @Test
    public void testConstructorNullUpperLeft() {
        System.out.println("testConstructorNullUpperLeft");
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(null, 4.0, 5.0, 'R'));
    }

    @Test
    public void testConstructorInvalidDimensions() {
        System.out.println("testConstructorInvalidDimensions");
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(new Point(2.0, 3.0), -4.0, -5.0, 'R'));
    }

    @Test
    public void testIsInsideNullArgument() {
        System.out.println("testIsInsideNullArgument");
        Rectangle rectangle = new Rectangle(new Point(2.0, 3.0), 4.0, 5.0, 'R');
        assertThrows(IllegalArgumentException.class, () -> rectangle.isInside(null));
    }

    @Test
    public void testRectangleCopy() {
        Point upperLeft = new Point(1.0, 2.0);
        double width = 5.0;
        double height = 10.0;
        char color = 'R';
        Rectangle originalRectangle = new Rectangle(upperLeft, width, height, color);

        Rectangle copiedRectangle = originalRectangle.copy();

        assertNotSame(originalRectangle, copiedRectangle, "Copied rectangle should not be the same reference as the original");
        assertEquals(originalRectangle.getUpperLeft(), copiedRectangle.getUpperLeft(), "Upper left corner should be equal");
        assertEquals(originalRectangle.getWidth(), copiedRectangle.getWidth(), "Widths should be equal");
        assertEquals(originalRectangle.getHeight(), copiedRectangle.getHeight(), "Heights should be equal");
        assertEquals(originalRectangle.getColor(), copiedRectangle.getColor(), "Colors should be equal");
    }

}
