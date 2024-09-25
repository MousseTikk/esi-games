package g56583.atl.ascii.model;

import g56583.atl.ascii.DesignPattern.Composite.Component;

/**
 * Represents a circle shape with a center point and a radius.
 */
public class Circle extends Component {
    private final Point center;
    private final double radius;

    /**
     * Constructs a circle with the given information.
     *
     * @param center The center point of the circle.
     * @param radius The radius of the circle.
     * @param color  The color of the circle.
     */
    public Circle(Point center, double radius, char color) {
        super(color);
        if (center == null) {
            throw new IllegalArgumentException("The given center is null");
        }
        if (radius <= 0) {
            throw new IllegalArgumentException("The given radius should be superior to 0");
        }
        this.center = new Point(center);
        this.radius = radius;
    }

    @Override
    public boolean isInside(Point p) {
        if (p == null) {
            throw new IllegalArgumentException("The given point is null");
        }
        double eqCircle = Math.pow(p.getX() - center.getX(), 2) + Math.pow(p.getY() - center.getY(), 2);
        return eqCircle <= Math.pow(radius, 2);
    }

    @Override
    public void move(double dx, double dy) {
        center.move(dx, dy);
    }

    @Override
    public String toString() {
        return "Circle";
    }

    /**
     * Method create for the test and return the center of the circle
     *
     * @return the center of the circle
     */
    protected Point getCenter() {
        return new Point(center);
    }

    /**
     * Method create for the test and return the radius of the circle
     *
     * @return the radius of the circle
     */
    protected double getRadius() {
        return radius;
    }

    @Override
    public Circle copy() {
        return new Circle(new Point(this.center), this.radius, this.getColor());
    }
}
