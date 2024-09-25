package g56583.atl.ascii.model;

/**
 * The Shape interface represents a geometric shape and defines the expected behaviors
 * for any shape in a drawing.
 */
public interface Shape {
    /**
     * Move the shape by the specified horizontal and vertical distances.
     *
     * @param dx The horizontal distance to move the shape.
     * @param dy The vertical distance to move the shape.
     */
    void move(double dx, double dy);

    /**
     * Check if a given point is inside the shape.
     *
     * @param p The point to check for inclusion.
     * @return True if the point is inside the shape, false otherwise.
     */
    boolean isInside(Point p);

    /**
     * Get the color representation of the shape.
     *
     * @return The character representing the color of the shape.
     */
    char getColor();

    /**
     * Copy the shape.
     *
     * @return A copy of the shape.
     */
    Shape copy();
}
