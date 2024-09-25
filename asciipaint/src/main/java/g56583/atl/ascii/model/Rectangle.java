package g56583.atl.ascii.model;

import g56583.atl.ascii.DesignPattern.Composite.Component;

/**
 * Represents a rectangle shape with an upper-left corner, width, and height.
 */
public class Rectangle extends Component {
    private final Point upperLeft;
    private final double width;
    private final double height;

    /**
     * Constructs a rectangle with the given information.
     *
     * @param upperLeft The upper-left corner of the rectangle.
     * @param width     The width of the rectangle.
     * @param height    The height of the rectangle.
     * @param color     The color of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height, char color) {
        super(color);
        if (upperLeft == null) {
            throw new IllegalArgumentException("Given point is null");
        }
        this.upperLeft = new Point(upperLeft);
        if (width <= 0 && height <= 0) {
            throw new IllegalArgumentException("The weidth and the heigth should be more than 0.");
        }
        this.width = width;
        this.height = height;

    }

    @Override
    public boolean isInside(Point p) {
        if (p == null) {
            throw new IllegalArgumentException("Given point is null");
        }
        return (p.getX() <= upperLeft.getX() + width && p.getX() >= upperLeft.getX()) &&
                (p.getY() <= upperLeft.getY() + height && p.getY() >= upperLeft.getY());
    }
    @Override
    public void move(double dx, double dy) {
        upperLeft.move(dx, dy);
    }

    @Override
    public String toString() {
        return "Rectangle";
    }

    /**
     * Method for test return the upper left corener of the rectangle
     *
     * @return the upper left corener of the rectangle
     */
    protected Point getUpperLeft() {
        return new Point(upperLeft);
    }

    /**
     * Method for test return the width  of the rectangle
     *
     * @return the width  of the rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * Method for test return  the height of the rectangle
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return height;
    }

    @Override
    public Rectangle copy() {
        return new Rectangle(new Point(upperLeft), width, height, getColor());
    }

}
