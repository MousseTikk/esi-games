package g56583.atl.ascii.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrawingTest {
    private Drawing drawing;
    private Shape circle;
    private Shape rectangle;

    @BeforeEach
    public void setUp() {
        drawing = new Drawing(20, 20);
        circle = new Circle(new Point(10, 10), 5.0, 'R');
        rectangle = new Rectangle(new Point(5, 5), 8.0, 5.0, 'B');
    }
    @Test
    public void testAddShape() {
        System.out.println("testAddShape");
        assertTrue(drawing.getShapes().isEmpty());
        drawing.addShape(circle);
        assertTrue(drawing.getShapes().contains(circle));
        assertEquals(1, drawing.getShapes().size());
    }
    // Ce test ne passe pas car je ne sais pas s'il faut faire en sorte que la nouvelle forme qu'on place
    // écrase l'ancienne ou si l'ancienne est "invincible" et ne peut être écrasé (par simplicité
    // j'ai implémenté la seconde option qui a tout autant de sens).
    @Test
    public void testGetShapeAt() {
        System.out.println("testGetShapeAt");
        drawing.addShape(circle);
        drawing.addShape(rectangle);
        assertEquals(rectangle, drawing.getShapeAt(new Point(10, 10)));
        assertEquals(rectangle, drawing.getShapeAt(new Point(7, 7)));
        assertNull(drawing.getShapeAt(new Point(2, 2)));
    }
    @Test
    public void testGetWidthAndHeight() {
        System.out.println("testGetWidthAndHeight");
        assertEquals(20, drawing.getWidth());
        assertEquals(20, drawing.getHeight());
    }
    @Test
    public void testConstructorInvalidWidth() {
        System.out.println("testConstructorInvalidWidth");
        assertThrows(IllegalArgumentException.class, () -> new Drawing(0, 20));
    }
    @Test
    public void testConstructorInvalidHeight() {
        System.out.println("testConstructorInvalidHeight");
        assertThrows(IllegalArgumentException.class, () -> new Drawing(20, 0));
    }
    @Test
    public void testAddShapeNullShape() {
        System.out.println("testAddShapeNullShape");
        assertThrows(IllegalArgumentException.class, () -> drawing.addShape(null));
    }
    @Test
    public void testGetShapeAtNullPoint() {
        System.out.println("testGetShapeAtNullPoint");
        assertThrows(IllegalArgumentException.class, () -> drawing.getShapeAt(null));
    }
    @Test
    public void testAddShapeAtValidIndex() {
        Drawing drawing = new Drawing();
        Shape shape = new Circle(new Point(1, 1), 1.0, 'A');
        drawing.addShapeAt(0, shape);
        assertEquals(1, drawing.getShapes().size());
    }
    @Test
    public void testAddShapeAtNullShape() {
        Drawing drawing = new Drawing();
        assertThrows(IllegalArgumentException.class, () -> {
            drawing.addShapeAt(0, null); // Null shape
        });
    }
    @Test
    public void testAddShapeAtInvalidIndex() {
        Drawing drawing = new Drawing();
        Shape shape = new Circle(new Point(1, 1), 1.0, 'A');
        assertThrows(IndexOutOfBoundsException.class, () -> {
            drawing.addShapeAt(-1, shape); // Invalid index
        });
    }
    @Test
    public void testRemoveExistingShape() {
        Drawing drawing = new Drawing();
        Shape shape = new Circle(new Point(1, 1), 1.0, 'A');
        drawing.addShape(shape);
        drawing.removeShape(shape);
        assertEquals(0, drawing.getShapes().size());
    }
    @Test
    public void testRemoveNonExistingShape() {
        Drawing drawing = new Drawing();
        Shape nonExistingShape = new Circle(new Point(2, 2), 2.0, 'B');
        // This should not throw an exception but also not affect the drawing
        drawing.removeShape(nonExistingShape);
        assertEquals(0, drawing.getShapes().size());
    }
}
