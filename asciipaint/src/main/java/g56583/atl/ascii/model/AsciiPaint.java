package g56583.atl.ascii.model;

import g56583.atl.ascii.DesignPattern.Composite.Component;
import g56583.atl.ascii.DesignPattern.Composite.Composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents an ASCII art drawing application.
 */
public class AsciiPaint {
    private Drawing drawing;

    /**
     * Constructs an empty AsciiPaint object with no initial drawing.
     */
    public AsciiPaint() {
        drawing = new Drawing();
    }

    /**
     * Constructs an AsciiPaint object with an initial drawing of the specified width and height.
     *
     * @param width  The width of the initial drawing.
     * @param height The height of the initial drawing.
     */
    public AsciiPaint(int width, int height) {
        drawing = new Drawing(width, height);
    }

    /**
     * Gets the current drawing in the AsciiPaint application.
     *
     * @return The current drawing.
     */
    public Drawing getDrawing() {
        return drawing;
    }

    /**
     * Creates a new circle shape and adds it to the drawing.
     *
     * @param x      The x-coordinate of the center of the circle.
     * @param y      The y-coordinate of the center of the circle.
     * @param radius The radius of the circle.
     * @param color  The color of the circle.
     */
    public void newCircle(int x, int y, double radius, char color) {
        drawing.addShape(new Circle(new Point(x, y), radius, color));
    }

    /**
     * Creates a new rectangle shape and adds it to the drawing.
     *
     * @param x      The x-coordinate of the upper-left corner of the rectangle.
     * @param y      The y-coordinate of the upper-left corner of the rectangle.
     * @param width  The width of the rectangle.
     * @param height The height of the rectangle.
     * @param color  The color of the rectangle.
     */
    public void newRectangle(int x, int y, double width, double height, char color) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("The width and the height should be more than 0.");
        }
        drawing.addShape(new Rectangle(new Point(x, y), width, height, color));
    }

    /**
     * Creates a new square shape and adds it to the drawing.
     *
     * @param x     The x-coordinate of the upper-left corner of the square.
     * @param y     The y-coordinate of the upper-left corner of the square.
     * @param side  The side length of the square.
     * @param color The color of the square.
     */
    public void newSquare(int x, int y, double side, char color) {
        drawing.addShape(new Square(new Point(x, y), side, color));
    }

    /**
     * Creates a new line shape and adds it to the drawing.
     * A line is defined by two points and a color.
     *
     * @param x1    The x-coordinate of the start point of the line.
     * @param y1    The y-coordinate of the start point of the line.
     * @param x2    The x-coordinate of the end point of the line.
     * @param y2    The y-coordinate of the end point of the line.
     * @param color The color of the line.
     */
    public void newLine(int x1, int y1, int x2, int y2, char color) {
        drawing.addShape(new Line(new Point(x1, y1), new Point(x2, y2), color));
    }

    /**
     * Converts the current drawing to an ASCII art representation.
     *
     * @return The ASCII art representation of the drawing.
     */
    public String asAscii() {
        StringBuilder sb = new StringBuilder();
        List<Shape> reversedShapes = new ArrayList<>(drawing.getShapes());
        Collections.reverse(reversedShapes);
        List<Component> reversedComponents = new ArrayList<>(drawing.getComponents());
        Collections.reverse(reversedComponents);

        for (int i = 0; i < drawing.getHeight(); i++) {
            for (int j = 0; j < drawing.getWidth(); j++) {
                Point p = new Point(j, i);
                char colorChar = ' ';

                for (Component component : reversedComponents) {
                    if (component.isInside(p)) {
                        colorChar = component.getColor();
                        break;
                    }
                }
                if (colorChar == ' ') {
                    for (Shape shape : reversedShapes) {
                        if (shape.isInside(p)) {
                            colorChar = shape.getColor();
                            break;
                        }
                    }
                }
                sb.append(colorChar);
            }
            sb.append('\n');
        }
        return sb.toString();
    }
    /**
     * Changes the color of a shape at the specified index in the drawing.
     *
     * @param shapeIndex The index of the shape to change the color of.
     * @param newColor   The new color to set for the shape (represented as a character).
     * @throws IllegalArgumentException If the shape at the given index does not support color,
     *                                  or if the shapeIndex is invalid (out of bounds).
     */
    public void changeShapeColor(int shapeIndex, char newColor) {
        if (shapeIndex < 0 || shapeIndex >= this.getShapes().size() + this.getComponents().size()) {
            throw new IllegalArgumentException("Invalid shape index: " + shapeIndex);
        }
        if (shapeIndex < this.getShapes().size()) {
            Shape shape = this.getShapes().get(shapeIndex);
            if (shape instanceof ColoredShape) {
                ((ColoredShape) shape).setColor(newColor);
            }
        } else {
            int groupIndex = shapeIndex - this.getShapes().size();
            Composite group = (Composite) this.getComponents().get(groupIndex);
            group.setColor(newColor);
            for (Component component : group.getChildren()) {
                if (component instanceof ColoredShape) {
                    component.setColor(newColor);
                }
            }
        }
    }


    /**
     * Returns a list of shapes and groups in the drawing,
     *
     * @return a list of shapes and groups in the drawing,
     */
    public String getShapesList() {
        StringBuilder sb = new StringBuilder("List of Shapes:\n");
        int index = 0;
        for (Shape shape : drawing.getShapes()) {
            String shapeType = shape.toString();
            char shapeColor = shape.getColor();
            sb.append(index++).append(": ").append(shapeType).append(" (").append(shapeColor).append(")\n");
        }
        for (Component component : drawing.getComponents()) {
            String componentType = component instanceof Composite ? "Group" : component.toString();
            char componentColor = component.getColor();
            sb.append(index++).append(": ").append(componentType).append(" (").append(componentColor).append(")\n");
        }


        return sb.toString();
    }

    /**
     * Returns the dimensions of the drawing.
     *
     * @return the dimensions of the drawing.
     */
    public String getDrawingDimensions() {
        return "Dimension: " + drawing.getWidth() + "x" + drawing.getHeight();
    }

    /**
     * Removes a shape from the drawing at the specified index.
     *
     * @param index The index of the shape to be removed.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void removeShape(int index) {
        drawing.removeShape(index);
    }

    /**
     * Inserts a shape into the drawing at the specified index.
     *
     * @param index The index where the shape will be inserted.
     * @param shape The shape to insert.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void addShapeAt(int index, Shape shape) {
        drawing.addShapeAt(index, shape);
    }

    /**
     * Creates a new drawing with the specified dimensions.
     *
     * @param width  the given width.
     * @param height the given height.
     */
    public void createNewDrawing(int width, int height) {
        this.drawing = new Drawing(width, height);
    }

    public List<Shape> getShapes() {
        return drawing.getShapes();
    }

    /**
     * Creates a new group of shapes with the specified color.
     *
     * @param indices    the indices of the shapes to be grouped.
     * @param groupColor the color of the group.
     * @return the created group.
     */
    public Composite createGroup(List<Integer> indices, char groupColor) {
        Composite group = new Composite(groupColor);
        indices.sort(Collections.reverseOrder());
        for (int index : indices) {
            if (index < this.getShapes().size()) {
                Component shape = (Component) this.getShapes().get(index);
                group.add(shape);
                this.getShapes().remove(index);
            } else {
                throw new IllegalArgumentException("Invalid index: " + index);
            }
        }
        this.getDrawing().addComponent(group);
        return group;
    }

    /**
     * Retrieves the color of a shape or group at a specified index.
     *
     * @param index The index of the shape or group.
     * @return The color of the shape or group at the specified index.
     * @throws IllegalArgumentException If the specified index is invalid.
     */
    public char getColorOfShape(int index) {
        if (index < this.getShapes().size()) {
            return this.getShapes().get(index).getColor();
        } else if (index < this.getShapes().size() + this.getComponents().size()) {
            int groupIndex = index - this.getShapes().size();
            Composite group = (Composite) this.getComponents().get(groupIndex);
            return group.getColor();
        } else {
            throw new IllegalArgumentException("Invalid index: " + index);
        }
    }

    /**
     * Moves a shape or group to a new position.
     *
     * @param shapeIndex The index of the shape or group to move.
     * @param dx         The horizontal distance to move.
     * @param dy         The vertical distance to move.
     * @throws IllegalArgumentException If the specified shape or group index is invalid.
     */
    public void moveShape(int shapeIndex, double dx, double dy) {
        if (shapeIndex < 0 || shapeIndex >= this.getShapes().size()) {
            throw new IllegalArgumentException("Invalid shape index: " + shapeIndex);
        }
        if (shapeIndex < this.getShapes().size()) {
            Shape shape = this.getShapes().get(shapeIndex);
            shape.move(dx, dy);
        } else {
            moveGroup(shapeIndex, dx, dy);
        }
    }

    /**
     * Moves a group to a new position.
     *
     * @param groupIndex The index of the group to move.
     * @param dx         The horizontal distance to move.
     * @param dy         The vertical distance to move.
     * @throws IllegalArgumentException If the specified group index is invalid.
     */
    private void moveGroup(int groupIndex, double dx, double dy) {
        int actualIndex = groupIndex - this.getShapes().size();
        if (actualIndex >= 0 && actualIndex < this.getComponents().size()) {
            Component group = this.getComponents().get(actualIndex);
            group.move(dx, dy);
        } else {
            throw new IllegalArgumentException("Invalid group index.");
        }
    }

    /**
     * Retrieves a list of all composite components (groups) in the drawing.
     *
     * @return A list of composite components.
     */
    public List<Component> getComponents() {
        return drawing.getComponents();
    }

    /**
     * Disassembles a grouped shape into its individual components.
     *
     * @param groupIndex The index of the group to ungroup.
     * @throws IllegalArgumentException If the specified group index is invalid.
     */
    public void ungroupShape(int groupIndex) {
        int actualIndex = groupIndex - getShapes().size();
        if (actualIndex < 0 || actualIndex >= getComponents().size()) {
            throw new IllegalArgumentException("Invalid group index.");
        }

        Composite group = (Composite) getComponents().get(actualIndex);
        for (Component component : group.getChildren()) {
            drawing.addShape(component);
        }

        getComponents().remove(actualIndex);
    }

    /**
     * Adds a shape or group to the drawing.
     *
     * @param addedShape The shape or group to add.
     */
    public void addShape(Shape addedShape) {
        drawing.addShape(addedShape);
    }

    /**
     * Removes a shape or group from the drawing.
     *
     * @param removedShape The shape or group to remove.
     */
    public void removeShape(Shape removedShape) {
        drawing.removeShape(removedShape);
    }

    /**
     * Sets the drawing.
     *
     * @param drawing The drawing to set.
     */
    public void setDrawing(Drawing drawing) {
        if (drawing == null) {
            throw new IllegalArgumentException("Drawing cannot be null.");
        }
        this.drawing = drawing;
    }

    /**
     * Removes a group from the drawing.
     *
     * @param group The group to remove.
     */
    public void ungroupSpecificGroup(Composite group) {
        if (group == null) {
            throw new IllegalArgumentException("Group cannot be null.");
        }

        for (Component component : group.getChildren()) {
            drawing.addShape(component);
        }

        drawing.getComponents().remove(group);
    }

    /**
     * Retrieves a composite group from the drawing.
     *
     * @param groupIndex The index of the group to retrieve.
     * @return The composite group at the specified index.
     */
    public Composite getGroup(int groupIndex) {
        int actualIndex = groupIndex - getShapes().size();
        if (actualIndex < 0 || actualIndex >= getComponents().size()) {
            throw new IllegalArgumentException("Invalid group index.");
        }
        Composite originalGroup = (Composite) getComponents().get(actualIndex);
        return new Composite(originalGroup);
    }

    /**
     * Adds a group to the drawing.
     *
     * @param group The group to add.
     */
    public void regroup(Composite group) {
        if (group == null) {
            throw new IllegalArgumentException("Group cannot be null.");
        }
        this.drawing.addComponent(group);
    }

    /**
     * Retrieves a shape or group from the drawing.
     *
     * @param index The index of the shape or group to retrieve.
     * @return The shape or group at the specified index.
     */
    public Shape getShapeAt(int index) {
        if (index < 0 || index >= getShapes().size()) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        return getShapes().get(index);
    }

    /**
     * Replaces a shape or group in the drawing with a new shape or group.
     *
     * @param index    The index of the shape or group to replace.
     * @param newShape The new shape or group to replace with.
     */
    public void replaceShape(int index, Shape newShape) {
        if (index < 0 || index >= getShapes().size()) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        if (newShape == null) {
            throw new IllegalArgumentException("New shape cannot be null");
        }
        getShapes().set(index, newShape);
    }
    public void recreateGroup(List<Shape> originalShapes, char color) {
        Composite group = new Composite(color);
        for (Shape shape : originalShapes) {
            group.add((Component) shape);
            removeShape(shape); // Supprime la forme de la liste des formes
        }

        drawing.addComponent(group); // Ajoute le groupe reconstitué
    }
}
