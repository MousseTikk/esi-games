package g56583.atl.ascii.model;

/**
 * Represents a square shape with an upper-left corner and side length.
 */
public class Square extends Rectangle {
    /**
     * Creates a new square.
     *
     * @param upperLeft upper-left corner of the square
     * @param side      length of the square
     * @param color     color of the square
     */
    public Square(Point upperLeft, double side, char color) {
        super(new Point(upperLeft), side, side, color);
    }

    @Override
    public String toString() {
        return "Square";
    }

    @Override
    public Square copy() {
        Point copiedUpperLeft = new Point(this.getUpperLeft());
        return new Square(copiedUpperLeft, this.getWidth(), this.getColor());
    }
}
