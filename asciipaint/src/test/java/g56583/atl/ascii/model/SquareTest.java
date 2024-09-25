package g56583.atl.ascii.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {

    @Test
    public void testConstructorAndGetters() {
        Point upperLeft = new Point(2.0, 3.0);
        Square square = new Square(upperLeft, 4.0, 'S');

        assertEquals(upperLeft, square.getUpperLeft());
        assertEquals(4.0, square.getWidth(), 0.01);
        assertEquals(4.0, square.getHeight(), 0.01);
        assertEquals('S', square.getColor());
    }

    @Test
    public void testIsInside() {
        Point upperLeft = new Point(2.0, 3.0);
        Square square = new Square(upperLeft, 4.0, 'S');

        Point insidePoint = new Point(3.0, 4.0);
        Point outsidePoint = new Point(1.0, 2.0);

        assertTrue(square.isInside(insidePoint));
        assertFalse(square.isInside(outsidePoint));
    }

    @Test
    public void testMove() {
        Point upperLeft = new Point(2.0, 3.0);
        Square square = new Square(upperLeft, 4.0, 'S');

        square.move(1.0, 2.0);

        assertEquals(3.0, square.getUpperLeft().getX(), 0.01);
        assertEquals(5.0, square.getUpperLeft().getY(), 0.01);
    }

    @Test
    public void testCopy() {
        Square originalSquare = new Square(new Point(2.0, 3.0), 4.0, 'S');
        Square copiedSquare = originalSquare.copy();

        assertNotSame(originalSquare, copiedSquare);
        assertEquals(originalSquare.getUpperLeft(), copiedSquare.getUpperLeft());
        assertEquals(originalSquare.getWidth(), copiedSquare.getWidth());
        assertEquals(originalSquare.getColor(), copiedSquare.getColor());
    }

    @Test
    public void testToString() {
        Square square = new Square(new Point(2.0, 3.0), 4.0, 'S');
        String expectedString = "Square";
        assertEquals(expectedString, square.toString());
    }

    @Test
    public void testIsInsideBoundary() {
        Square square = new Square(new Point(2.0, 3.0), 4.0, 'S');
        Point boundaryPoint = new Point(2.0, 3.0);
        assertTrue(square.isInside(boundaryPoint));
    }

    @Test
    public void testIsInsideCorner() {
        Square square = new Square(new Point(2.0, 3.0), 4.0, 'S');
        Point cornerPoint = new Point(6.0, 7.0);
        assertTrue(square.isInside(cornerPoint));
    }

}