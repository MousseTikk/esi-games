package g56583.atl.ascii.model;

import g56583.atl.ascii.DesignPattern.Composite.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an illustration composed of various shapes within a defined width and height.
 */
public class Drawing {

    private final List<Shape> shapes;
    private final int height;
    private final int width;
    private final List<Component> components;

    /**
     * Constructs an empty Drawing object with a default width or height that I could
     * change with a setter.
     */
    public Drawing() {
        this.width = 25;
        this.height = 25;
        shapes = new ArrayList<>();
        components = new ArrayList<>();
    }

    /**
     * Constructs a Drawing object with a specified width and height.
     *
     * @param width  The width of the drawing.
     * @param height The height of the drawing.
     * @throws IllegalArgumentException If width or height is less than or equal to zero.
     */
    public Drawing(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width or height of drawing too "
                    + "small.");
        }
        this.width = width;
        this.height = height;
        shapes = new ArrayList<>();
        components = new ArrayList<>();
    }

    /**
     * Adds a shape to the drawing.
     *
     * @param shape The shape to add.
     * @throws IllegalArgumentException If the given shape is null.
     */
    public void addShape(Shape shape) {
        if (shape == null) {
            throw new IllegalArgumentException("Given shape is null");
        }
        shapes.add(shape);
    }

    /**
     * Retrieves the shape located at a specified point within the drawing.
     *
     * @param p The point at which to check for a shape.
     * @return The shape at the specified point or null if no shape is found.
     * @throws IllegalArgumentException If the given point is null.
     */
    public Shape getShapeAt(Point p) {
        if (p == null) {
            throw new IllegalArgumentException("Given point is null");
        }
        for (int i = shapes.size() - 1; i >= 0; i--) {
            if (shapes.get(i).isInside(p)) {
                return shapes.get(i);
            }
        }
        return null;
    }
    /**
     * Gets the height of the drawing.
     *
     * @return The height of the drawing.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the width of the drawing.
     *
     * @return The width of the drawing.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the list of shapes present in the drawing.
     *
     * @return The list of shapes in the drawing.
     */
    public List<Shape> getShapes() {
        return shapes;
    }

    /**
     * Removes a shape from this drawing at the specified index.
     *
     * @param index The index of the shape to be removed.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void removeShape(int index) {
        if (index < 0 || index >= shapes.size()) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        shapes.remove(index);
    }

    /**
     * Inserts a shape into this drawing at the specified index.
     *
     * @param index The index where the shape will be inserted.
     * @param shape The shape to insert.
     * @throws IndexOutOfBoundsException if the index is out of range.
     * @throws IllegalArgumentException  if the shape is null.
     */
    public void addShapeAt(int index, Shape shape) {
        if (shape == null) {
            throw new IllegalArgumentException("Shape cannot be null");
        }
        if (index < 0 || index > shapes.size()) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        shapes.add(index, shape);
    }

    /**
     * Adds a composite component (group) to the drawing.
     *
     * @param component The composite component to add.
     */
    public void addComponent(Component component) {
        components.add(component);
    }

    /**
     * Retrieves a list of all composite components (groups) in the drawing.
     *
     * @return A list of composite components.
     */
    public List<Component> getComponents() {
        return components;
    }

    /**
     * Removes a shape from this drawing.
     * @param shape The shape to remove.
     */
    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }


}



