package g56583.atl.ascii.model;

import g56583.atl.ascii.DesignPattern.Composite.Component;

/**
 * This class represents a line defined by two points. The line is characterized by its start and end points,
 * along with a color attribute inherited from ColoredShape.
 */
public class Line extends Component {
    private final Point startPoint;
    private final Point endPoint;

    /**
     * Creates a line with the specified start and end points and color.
     *
     * @param startPoint The starting point of the line. Must not be null.
     * @param endPoint   The ending point of the line. Must not be null.
     * @param color      The character representing the color of the line.
     */
    public Line(Point startPoint, Point endPoint, char color) {
        super(color);
        if (startPoint == null || endPoint == null) {
            throw new IllegalArgumentException("Points cannot be null");
        }
        this.startPoint = new Point(startPoint);
        this.endPoint = new Point(endPoint);
    }

    @Override
    public void move(double dx, double dy) {
        startPoint.move(dx, dy);
        endPoint.move(dx, dy);
    }

    @Override
    public boolean isInside(Point p) {
        if (p == null) {
            throw new IllegalArgumentException("Point cannot be null");
        }

        double deltaX = endPoint.getX() - startPoint.getX();
        double deltaY = endPoint.getY() - startPoint.getY();

        // Ligne verticale
        if (deltaX == 0) {
            return p.getX() == startPoint.getX() &&
                    Math.min(startPoint.getY(), endPoint.getY()) <= p.getY() &&
                    p.getY() <= Math.max(startPoint.getY(), endPoint.getY());
        }

        // Ligne horizontale
        if (deltaY == 0) {
            return p.getY() == startPoint.getY() &&
                    Math.min(startPoint.getX(), endPoint.getX()) <= p.getX() &&
                    p.getX() <= Math.max(startPoint.getX(), endPoint.getX());
        }

        // Ligne diagonale
        double m = deltaY / deltaX;
        double distance = Math.abs(m * p.getX() - p.getY() - m * startPoint.getX() + startPoint.getY()) /
                Math.sqrt(m * m + 1);
        return distance <= 0.5;
    }

    @Override
    public String toString() {
        return "Line";
    }

    /**
     * Method create for the test and return the start point of the line
     *
     * @return the start point of the line
     */
    public Point getStartPoint() {
        return new Point(startPoint);
    }

    /**
     * Method create for the test and return the end point of the line
     *
     * @return the end point of the line
     */
    public Point getEndPoint() {
        return new Point(endPoint);
    }

    @Override
    public Line copy() {
        return new Line(new Point(this.startPoint), new Point(this.endPoint), this.getColor());
    }

}
