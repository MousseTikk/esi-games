package g56583.atl.ascii.DesignPattern.Command;

import g56583.atl.ascii.model.AsciiPaint;
import g56583.atl.ascii.model.Shape;

/**
 * The Move class represents a command that moves a shape or a group of shapes in an ASCII art drawing.
 * It implements the Command interface and provides methods to execute, undo, and redo the movement operation.
 */
public class Move implements Command {
    private final AsciiPaint paint;
    private final int shapeIndex;
    private final double dx, dy;
    private Shape previousState;

    /**
     * Constructor for Move command.
     *
     * @param paint      AsciiPaint instance for moving shapes.
     * @param shapeIndex Index of the shape to move.
     * @param dx         Horizontal movement distance.
     * @param dy         Vertical movement distance.
     */
    public Move(AsciiPaint paint, int shapeIndex, double dx, double dy) {
        this.paint = paint;
        this.shapeIndex = shapeIndex;
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void execute() {
        previousState = paint.getShapeAt(shapeIndex).copy();
        paint.moveShape(shapeIndex, dx, dy);
    }

    @Override
    public void undo() {
        paint.replaceShape(shapeIndex, previousState);
    }

    @Override
    public void redo() {
        paint.moveShape(shapeIndex, dx, dy);
    }
}
