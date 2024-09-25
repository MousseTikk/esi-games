package g56583.atl.ascii.DesignPattern.Command;

import g56583.atl.ascii.model.*;

/**
 * The Add class implements the Command interface to handle the addition of shapes to an AsciiPaint instance.
 * This class processes the input parameters to create specific shapes like circles, rectangles, squares, and lines.
 */
public class Add implements Command {
    private final AsciiPaint asciiPaint;
    private final String[] inputs;
    private Shape addedShape;

    /**
     * Constructs a new Add command with the specified AsciiPaint and input parameters.
     *
     * @param asciiPaint the specified asciiPaint parameters.
     * @param inputs     the specified input parameters.
     */
    public Add(AsciiPaint asciiPaint, String[] inputs) {
        this.asciiPaint = asciiPaint;
        this.inputs = inputs;
    }

    @Override
    public void execute() {
        String shapeType = inputs[1];
        switch (shapeType) {
            case "circle" -> addedShape = createCircle();
            case "rectangle" -> addedShape = createRectangle();
            case "square" -> addedShape = createSquare();
            case "line" -> addedShape = createLine();
            default -> throw new InvalidShapeTypeException("Invalid shape type: " + shapeType);
        }
        asciiPaint.addShape(addedShape);
    }

    /**
     * Creates a new Circle object with the specified input parameters.
     *
     * @return a new Circle object with the specified input parameters.
     */
    private Circle createCircle() {
        int x = Integer.parseInt(inputs[2]);
        int y = Integer.parseInt(inputs[3]);
        double radius = Double.parseDouble(inputs[4]);
        char color = inputs[5].toUpperCase().charAt(0);
        return new Circle(new Point(x, y), radius, color);
    }

    /**
     * Creates a new Rectangle object with the specified input parameters.
     *
     * @return a new Rectangle object with the specified input parameters.
     */
    private Rectangle createRectangle() {
        int x = Integer.parseInt(inputs[2]);
        int y = Integer.parseInt(inputs[3]);
        double width = Double.parseDouble(inputs[4]);
        double height = Double.parseDouble(inputs[5]);
        char color = inputs[6].toUpperCase().charAt(0);
        return new Rectangle(new Point(x, y), width, height, color);
    }

    /**
     * Creates a new Square object with the specified input parameters.
     *
     * @return a new Square object with the specified input parameters.
     */
    private Square createSquare() {
        int x = Integer.parseInt(inputs[2]);
        int y = Integer.parseInt(inputs[3]);
        double side = Double.parseDouble(inputs[4]);
        char color = inputs[5].toUpperCase().charAt(0);
        return new Square(new Point(x, y), side, color);
    }

    /**
     * Creates a new Line object with the specified input parameters.
     *
     * @return a new Line object with the specified input parameters.
     */
    private Line createLine() {
        int x1 = Integer.parseInt(inputs[2]);
        int y1 = Integer.parseInt(inputs[3]);
        int x2 = Integer.parseInt(inputs[4]);
        int y2 = Integer.parseInt(inputs[5]);
        char color = inputs[6].toUpperCase().charAt(0);
        return new Line(new Point(x1, y1), new Point(x2, y2), color);
    }

    @Override
    public void undo() {
        if (addedShape != null) {
            asciiPaint.removeShape(addedShape);
        }
    }

    @Override
    public void redo() {
        if (addedShape != null) {
            asciiPaint.addShape(addedShape);
        }
    }

    /**
     * Custom exception class for invalid shape types.
     */
    public static class InvalidShapeTypeException extends RuntimeException {
        /**
         * Constructs a new InvalidShapeTypeException with the specified error message.
         *
         * @param message the error message.
         */
        public InvalidShapeTypeException(String message) {
            super(message);
        }
    }
}