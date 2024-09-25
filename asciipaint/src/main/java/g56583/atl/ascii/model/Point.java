package g56583.atl.ascii.model;

import java.util.Objects;

/**
 * Represents a 2D point with x and y coordinates.
 */
public class Point {
    private double x;

    private double y;

    /**
     * Constructs a Point object with the given x and y coordinates.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a Point object as a copy of another Point object.
     *
     * @param p The Point object to copy.
     */
    public Point(Point p) {
        this(p.getX(), p.getY());
    }

    /**
     * Gets the x-coordinate of the point.
     *
     * @return The x-coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the point.
     *
     * @return The y-coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * Moves the point by a specified amount in both the x and y directions.
     *
     * @param dx The amount to move the point in the x-direction.
     * @param dy The amount to move the point in the y-direction.
     */
    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param other The other point.
     * @return The distance between this point and the other point.
     */
    public double distanceTo(Point other) {
        double deltaX = other.getX() - x;
        double deltaY = other.getY() - y;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point point = (Point) obj;
        return Double.compare(point.x, x) == 0 &&
                Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
