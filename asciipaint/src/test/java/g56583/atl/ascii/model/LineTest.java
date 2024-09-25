package g56583.atl.ascii.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineTest {
    @Test
    public void testLineConstructor() {
        Point startPoint = new Point(0, 0);
        Point endPoint = new Point(5, 5);
        Line line = new Line(startPoint, endPoint, 'R');

        assertEquals(startPoint.getX(), line.getStartPoint().getX());
        assertEquals(startPoint.getY(), line.getStartPoint().getY());
        assertEquals(endPoint.getX(), line.getEndPoint().getX());
        assertEquals(endPoint.getY(), line.getEndPoint().getY());
        assertEquals('R', line.getColor());
    }

    @Test
    public void testMove() {
        Point startPoint = new Point(0, 0);
        Point endPoint = new Point(5, 5);
        Line line = new Line(startPoint, endPoint, 'R');

        line.move(3, 3);

        assertEquals(3, line.getStartPoint().getX());
        assertEquals(3, line.getStartPoint().getY());
        assertEquals(8, line.getEndPoint().getX());
        assertEquals(8, line.getEndPoint().getY());
    }

    @Test
    public void testIsInsideTrue() {
        Point startPoint = new Point(0, 0);
        Point endPoint = new Point(4, 4);
        Line line = new Line(startPoint, endPoint, 'R');
        Point testPoint = new Point(2, 2);

        assertTrue(line.isInside(testPoint));
    }

    @Test
    public void testIsInsideFalse() {
        Point startPoint = new Point(0, 0);
        Point endPoint = new Point(4, 4);
        Line line = new Line(startPoint, endPoint, 'R');
        Point testPoint = new Point(3, 1);

        assertFalse(line.isInside(testPoint));
    }

    @Test
    public void testToString() {
        Point startPoint = new Point(0, 0);
        Point endPoint = new Point(5, 5);
        Line line = new Line(startPoint, endPoint, 'R');

        String expected = "Line";
        assertEquals(expected, line.toString());
    }

    @Test
    public void testLineConstructorWithNullPoints() {
        assertThrows(IllegalArgumentException.class, () -> new Line(null, new Point(5, 5), 'R'));

        assertThrows(IllegalArgumentException.class, () -> new Line(new Point(0, 0), null, 'R'));
    }

    @Test
    public void testIsInsideWithNullPoint() {
        Line line = new Line(new Point(0, 0), new Point(5, 5), 'R');
        assertThrows(IllegalArgumentException.class, () -> line.isInside(null));
    }

    @Test
    public void testLineCopy() {
        Line originalLine = new Line(new Point(1.0, 2.0), new Point(3.0, 4.0), 'L');
        Line copiedLine = originalLine.copy();

        assertNotSame(originalLine, copiedLine);
        assertEquals(originalLine.getStartPoint(), copiedLine.getStartPoint());
        assertEquals(originalLine.getEndPoint(), copiedLine.getEndPoint());
        assertEquals(originalLine.getColor(), copiedLine.getColor());
    }

    @Test
    public void testIsInsideHorizontalLine() {
        Line horizontalLine = new Line(new Point(1.0, 2.0), new Point(5.0, 2.0), 'L');
        Point onLinePoint = new Point(3.0, 2.0);
        Point offLinePoint = new Point(3.0, 3.0);

        assertTrue(horizontalLine.isInside(onLinePoint));
        assertFalse(horizontalLine.isInside(offLinePoint));
    }

    @Test
    public void testIsInsideVerticalLine() {
        Line verticalLine = new Line(new Point(2.0, 1.0), new Point(2.0, 5.0), 'L');
        Point onLinePoint = new Point(2.0, 3.0);
        Point offLinePoint = new Point(3.0, 3.0);

        assertTrue(verticalLine.isInside(onLinePoint));
        assertFalse(verticalLine.isInside(offLinePoint));
    }

    @Test
    public void testIsInsideDiagonalLine() {
        Line diagonalLine = new Line(new Point(1.0, 1.0), new Point(4.0, 4.0), 'L');
        Point onLinePoint = new Point(3.0, 3.0);
        Point closePoint = new Point(3.0, 4.0);
        assertTrue(diagonalLine.isInside(onLinePoint));
        assertFalse(diagonalLine.isInside(closePoint));
    }

}