package g56583.atl.ascii.model;

/**
 * An abstract class representing a colored shape that implements the Shape interface.
 * This class provides functionality for setting and retrieving the color of a shape.
 */
public abstract class ColoredShape implements Shape {
    private char color;

    /**
     * Constructs a ColoredShape object with the specified color.
     *
     * @param color The character representing the color of the shape.
     */
    public ColoredShape(char color) {
        this.color = color;
    }

    /**
     * Gets the color of the shape.
     *
     * @return The character representing the color of the shape.
     */
    @Override
    public char getColor() {
        return color;
    }

    /**
     * Sets the color of the shape.
     *
     * @param color The character representing the new color of the shape.
     */
    public void setColor(char color) {
        this.color = color;
    }

    @Override
    public abstract ColoredShape copy();
}
