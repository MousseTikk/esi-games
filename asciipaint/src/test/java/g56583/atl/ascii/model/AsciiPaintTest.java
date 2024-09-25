package g56583.atl.ascii.model;

import static org.junit.jupiter.api.Assertions.*;

import g56583.atl.ascii.DesignPattern.Composite.Component;
import g56583.atl.ascii.DesignPattern.Composite.Composite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

class AsciiPaintTest {

    private AsciiPaint asciiPaint;

    @BeforeEach
    public void setUp() {
        asciiPaint = new AsciiPaint();
    }

    @Test
    public void testNewCircle() {
        System.out.println("testNewCircle");
        asciiPaint.newCircle(5, 5, 3.0, 'A');
        System.out.println(asciiPaint.getDrawing().getShapes().size());
        assertEquals(1, asciiPaint.getDrawing().getShapes().size());
    }

    @Test
    public void testNewRectangle() {
        System.out.println("testNewRectangle");
        asciiPaint.newRectangle(2, 2, 4.0, 3.0, 'B');
        System.out.println(asciiPaint.getDrawing().getShapes().size());
        assertEquals(1, asciiPaint.getDrawing().getShapes().size());
    }

    @Test
    public void testNewSquare() {
        System.out.println("testNewSquare");
        asciiPaint.newSquare(3, 3, 2.0, 'C');
        System.out.println(asciiPaint.getDrawing().getShapes().size());
        assertEquals(1, asciiPaint.getDrawing().getShapes().size());
    }

    @Test
    public void testAsAscii() {
        System.out.println("testAsAscii");
        asciiPaint.newCircle(5, 5, 3.0, 'A');
        String asciiArt = asciiPaint.asAscii();
        assertTrue(asciiArt.contains("A"));
    }

    @Test
    public void testGetDrawing() {
        System.out.println("testGetDrawing");
        Drawing drawing = asciiPaint.getDrawing();
        assertNotNull(drawing);
    }

    @Test
    public void testChangeShapeColor() {
        System.out.println("testChangeShapeColor");
        asciiPaint.newCircle(5, 5, 3.0, 'A');
        asciiPaint.changeShapeColor(0, 'B');
        char newColor = asciiPaint.getDrawing().getShapes().get(0).getColor();
        assertEquals('B', newColor);
    }

    @Test
    public void testMoveShape() {
        System.out.println("testMoveShape");
        asciiPaint.newCircle(5, 5, 3.0, 'A');
        asciiPaint.moveShape(0, 2.0, 2.0);
        Point newCenter = ((Circle) asciiPaint.getDrawing().getShapes().get(0)).getCenter();
        assertEquals(7.0, newCenter.getX(), 0.01);
        assertEquals(7.0, newCenter.getY(), 0.01);
    }

    @Test
    public void testChangeShapeColorInvalidIndex() {
        System.out.println("testChangeShapeColorInvalidIndex");
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.changeShapeColor(-1, 'B'));
    }

    @Test
    public void testMoveShapeInvalidIndex() {
        System.out.println("testMoveShapeInvalidIndex");
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.moveShape(-1, 2.0, 2.0));
    }

    @Test
    public void testGetShapesListEmpty() {
        System.out.println("testGetShapesListEmpty");
        String shapesList = asciiPaint.getShapesList();
        assertTrue(shapesList.contains("List of Shapes:\n"));
    }

    @Test
    public void testGetShapesListWithShapes() {
        System.out.println("testGetShapesListWithShapes");
        asciiPaint.newCircle(5, 5, 3.0, 'A');
        asciiPaint.newRectangle(2, 2, 4.0, 3.0, 'B');
        String shapesList = asciiPaint.getShapesList();
        assertTrue(shapesList.contains("0: Circle (A)\n1: Rectangle (B)\n"));
    }

    @Test
    public void testAsciiPaintDefaultConstructor() {
        AsciiPaint asciiPaint = new AsciiPaint();
        assertNotNull(asciiPaint);
    }

    @Test
    public void testAsciiPaintConstructorWithDimensions() {
        AsciiPaint asciiPaint = new AsciiPaint(10, 10);
        assertEquals("Dimension: 10x10", asciiPaint.getDrawingDimensions());
    }

    @Test
    public void testAsciiPaintConstructorNegativeDimensions() {
        assertThrows(IllegalArgumentException.class, () -> new AsciiPaint(-5, -5));
    }

    @Test
    public void testAddCircleValidParameters() {
        asciiPaint.newCircle(5, 5, 3.0, 'A');
        assertEquals(1, asciiPaint.getDrawing().getShapes().size());
    }

    @Test
    public void testAddCircleInvalidParameters() {
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.newCircle(5, 5, -3.0, 'A'));
    }

    @Test
    public void testChangeShapeColorValidIndex() {
        asciiPaint.newCircle(5, 5, 3.0, 'A');
        asciiPaint.changeShapeColor(0, 'B');
        assertEquals('B', asciiPaint.getDrawing().getShapes().get(0).getColor());
    }

    @Test
    public void testMoveShapeValidIndex() {
        asciiPaint.newCircle(5, 5, 3.0, 'A');
        asciiPaint.moveShape(0, 2.0, 2.0);
        Circle circle = (Circle) asciiPaint.getDrawing().getShapes().get(0);
        assertEquals(7.0, circle.getCenter().getX(), 0.01);
        assertEquals(7.0, circle.getCenter().getY(), 0.01);
    }

    @Test
    public void testCreateAndUngroupShapeGroupValidIndices() {
        asciiPaint.newCircle(5, 5, 3.0, 'A');
        asciiPaint.newSquare(2, 2, 2.0, 'B');
        Composite group = asciiPaint.createGroup(Arrays.asList(0, 1), 'C');
        assertNotNull(group);
        asciiPaint.ungroupShape(asciiPaint.getDrawing().getComponents().indexOf(group));
        assertEquals(2, asciiPaint.getDrawing().getShapes().size());
    }

    @Test
    public void testGroupingInvalidIndices() {
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.createGroup(Arrays.asList(100, 101), 'D'));
    }

    @Test
    public void testRemoveShapeValidIndex() {
        asciiPaint.newCircle(5, 5, 3.0, 'A');
        asciiPaint.removeShape(0);
        assertTrue(asciiPaint.getDrawing().getShapes().isEmpty());
    }

    @Test
    public void testRemoveShapeInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> asciiPaint.removeShape(100));
    }

    @Test
    public void testAddRectangleValidParameters() {
        asciiPaint.newRectangle(1, 1, 4.0, 3.0, 'R');
        assertEquals(1, asciiPaint.getDrawing().getShapes().size());
    }

    @Test
    public void testAddRectangleInvalidParameters() {
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.newRectangle(1, 1, -4.0, 3.0, 'R'));
    }

    @Test
    public void testAddSquareValidParameters() {
        asciiPaint.newSquare(2, 2, 3.0, 'S');
        assertEquals(1, asciiPaint.getDrawing().getShapes().size());
    }

    @Test
    public void testAddSquareInvalidParameters() {
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.newSquare(2, 2, -3.0, 'S'));
    }

    @Test
    public void testAddLineValidParameters() {
        asciiPaint.newLine(0, 0, 5, 5, 'L');
        assertEquals(1, asciiPaint.getDrawing().getShapes().size());
    }


    @Test
    public void testRetrieveInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            asciiPaint.getShapeAt(-1); // Using an invalid index
        });
    }

    @Test
    public void testReplaceShapeInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> asciiPaint.replaceShape(-1, new Square(new Point(2, 2), 2.0, 'B')));
    }

    @Test
    public void testReplaceShapeWithNull() {
        asciiPaint.newCircle(5, 5, 3.0, 'A');
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.replaceShape(0, null));
    }

    @Test
    public void testGetDrawingDimensions() {
        AsciiPaint asciiPaintWithDims = new AsciiPaint(20, 15);
        assertEquals("Dimension: 20x15", asciiPaintWithDims.getDrawingDimensions());
    }

    @Test
    public void testAddShapeAtBoundary() {
        asciiPaint.newCircle(0, 0, 1.0, 'C'); // Assuming (0,0) is a boundary point
        assertEquals(1, asciiPaint.getDrawing().getShapes().size());
    }

    @Test
    public void testMoveShapeOutsideDrawingArea() {
        asciiPaint.newCircle(5, 5, 3.0, 'A');
        asciiPaint.moveShape(0, 20, 20);
        Circle circle = (Circle) asciiPaint.getDrawing().getShapes().get(0);
        assertEquals(25.0, circle.getCenter().getX(), 0.01);
        assertEquals(25.0, circle.getCenter().getY(), 0.01);
    }

    @Test
    public void testAsAsciiWithGroupedComponents() {
        asciiPaint.newCircle(1, 1, 1.0, 'C');
        asciiPaint.newSquare(3, 3, 2.0, 'S');
        asciiPaint.createGroup(Arrays.asList(0, 1), 'G');
        String asciiArt = asciiPaint.asAscii();
        assertTrue(asciiArt.contains("G"));
    }

    @Test
    public void testChangeGroupColor() {
        asciiPaint.newCircle(1, 1, 1.0, 'C');
        asciiPaint.newSquare(3, 3, 2.0, 'S');
        Composite group = asciiPaint.createGroup(Arrays.asList(0, 1), 'G');

        int groupIndex = asciiPaint.getShapes().size() + asciiPaint.getDrawing().getComponents().indexOf(group);
        asciiPaint.changeShapeColor(groupIndex, 'H');

        char updatedColor = group.getColor();
        assertEquals('H', updatedColor);
    }

    /**
     * @Test public void testGetShapesListWithGroupAndShapes() {
     * asciiPaint.newCircle(1, 1, 1.0, 'A');
     * asciiPaint.newSquare(3, 3, 2.0, 'B');
     * asciiPaint.createGroup(Arrays.asList(0, 1), 'A');
     * String shapesList = asciiPaint.getShapesList();
     * assertTrue(shapesList.contains("Group (G)"));
     * }
     **/
    @Test
    public void testAddShapeAtSpecificIndex() {
        asciiPaint.newCircle(5, 5, 3.0, 'A');
        asciiPaint.addShapeAt(0, new Square(new Point(2, 2), 2.0, 'B'));
        Shape shape = asciiPaint.getShapes().get(0);
        assertTrue(shape instanceof Square);
    }

    @Test
    public void testCreateNewDrawing() {
        asciiPaint.createNewDrawing(20, 20);
        assertEquals("Dimension: 20x20", asciiPaint.getDrawingDimensions());
    }

    @Test
    public void testGetColorOfShapeForGroup() {
        asciiPaint.newCircle(1, 1, 1.0, 'C');
        asciiPaint.newSquare(3, 3, 2.0, 'S');
        asciiPaint.createGroup(Arrays.asList(0, 1), 'C');
        char groupColor = asciiPaint.getColorOfShape(0);
        assertEquals('C', groupColor);
    }

    @Test
    public void testUngroupShape() {
        asciiPaint.newCircle(1, 1, 1.0, 'C');
        asciiPaint.newSquare(3, 3, 2.0, 'S');
        asciiPaint.createGroup(Arrays.asList(0, 1), 'G');
        asciiPaint.ungroupShape(0); // Assuming group is at index 0
        assertEquals(2, asciiPaint.getShapes().size());
    }

    @Test
    public void testGetShapeAtValidIndex() {
        asciiPaint.newCircle(5, 5, 3.0, 'A');
        Shape shape = asciiPaint.getShapeAt(0);
        assertTrue(shape instanceof Circle);
    }

    @Test
    public void testGetShapeAtInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            asciiPaint.getShapeAt(100); // Using an out-of-bounds index
        });
    }

    @Test
    public void testReplaceShape() {
        asciiPaint.newCircle(5, 5, 3.0, 'A');
        asciiPaint.replaceShape(0, new Square(new Point(2, 2), 2.0, 'B'));
        Shape replacedShape = asciiPaint.getShapeAt(0);
        assertTrue(replacedShape instanceof Square);
    }

    @Test
    public void testReplaceShapeWithInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            asciiPaint.replaceShape(100, new Square(new Point(2, 2), 2.0, 'B')); // Invalid index
        });
    }

    @Test
    public void testRegroupWithValidGroup() {
        Composite group = new Composite('X');
        asciiPaint.regroup(group);
        assertTrue(asciiPaint.getComponents().contains(group));
    }

    @Test
    public void testRegroupWithNullGroup() {
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.regroup(null));
    }

    @Test
    public void testGetGroupWithValidIndex() {

    }

    @Test
    public void testGetGroupWithInvalidIndex() {
        assertThrows(IllegalArgumentException.class, () -> {
            asciiPaint.getGroup(100); // Assuming 100 is an invalid index
        });
    }

    @Test
    public void testUngroupSpecificGroupWithValidGroup() {
        Composite group = new Composite('X');
        asciiPaint.addShape(new Circle(new Point(1, 1), 2, 'C'));
        asciiPaint.createGroup(Collections.singletonList(0), 'G');
        asciiPaint.ungroupSpecificGroup(group);
        assertFalse(asciiPaint.getComponents().contains(group));
    }

    @Test
    public void testUngroupSpecificGroupWithNullGroup() {
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.ungroupSpecificGroup(null));
    }

    @Test
    public void testSetDrawingWithValidDrawing() {
        Drawing newDrawing = new Drawing();
        asciiPaint.setDrawing(newDrawing);
        assertEquals(newDrawing, asciiPaint.getDrawing());
    }

    @Test
    public void testSetDrawingWithNull() {
        assertThrows(IllegalArgumentException.class, () -> asciiPaint.setDrawing(null));
    }

    @Test
    public void testAddShape() {
        Shape shape = new Circle(new Point(1, 1), 2, 'C');
        asciiPaint.addShape(shape);
        assertTrue(asciiPaint.getShapes().contains(shape));
    }

    @Test
    public void testGetColorOfShapeWithValidIndex() {
        asciiPaint.addShape(new Circle(new Point(1, 1), 2, 'C'));
        char color = asciiPaint.getColorOfShape(0);
        assertEquals('C', color);
    }

    @Test
    public void testGetColorOfShapeWithInvalidIndex() {
        assertThrows(IllegalArgumentException.class, () -> {
            asciiPaint.getColorOfShape(100); // Assuming 100 is an invalid index
        });
    }

    @Test
    public void testUngroupShapeWithInvalidIndex() {
        AsciiPaint asciiPaint = new AsciiPaint();
        asciiPaint.newCircle(1, 1, 1.0, 'C');
        asciiPaint.newSquare(3, 3, 2.0, 'S');
        Composite group = asciiPaint.createGroup(Arrays.asList(0, 1), 'G');

        asciiPaint.getDrawing().addComponent(group);

        int invalidGroupIndex = asciiPaint.getDrawing().getComponents().size() + 1;

        assertThrows(IllegalArgumentException.class, () -> asciiPaint.ungroupShape(invalidGroupIndex));
    }

    @Test
    public void testRemoveShape() {
        AsciiPaint asciiPaint = new AsciiPaint();
        Shape circle = new Circle(new Point(1, 1), 1.0, 'C');
        Shape square = new Square(new Point(3, 3), 2.0, 'S');

        asciiPaint.addShape(circle);
        asciiPaint.addShape(square);

        assertTrue(asciiPaint.getShapes().contains(circle));
        assertTrue(asciiPaint.getShapes().contains(square));

        asciiPaint.removeShape(circle);

        assertFalse(asciiPaint.getShapes().contains(circle));
        assertTrue(asciiPaint.getShapes().contains(square));
    }

    @Test
    public void testUngroupSpecificGroup() {
        AsciiPaint asciiPaint = new AsciiPaint();

        Component circle = new Circle(new Point(1, 1), 1.0, 'C');
        Component square = new Square(new Point(3, 3), 2.0, 'S');

        asciiPaint.addShape(circle);
        asciiPaint.addShape(square);

        Composite group = new Composite('G');
        group.add( circle);
        group.add(square);
        asciiPaint.getDrawing().addComponent(group);

        assertTrue(asciiPaint.getDrawing().getComponents().contains(group));

        asciiPaint.ungroupSpecificGroup(group);

        assertFalse(asciiPaint.getDrawing().getComponents().contains(group));

        assertTrue(asciiPaint.getDrawing().getShapes().contains(circle));
        assertTrue(asciiPaint.getDrawing().getShapes().contains(square));
    }


}