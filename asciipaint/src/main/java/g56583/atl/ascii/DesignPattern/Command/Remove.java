package g56583.atl.ascii.DesignPattern.Command;

import g56583.atl.ascii.model.AsciiPaint;
import g56583.atl.ascii.model.Shape;

/**
 * The Remove class represents a command that removes a shape from an ASCII art drawing.
 * It implements the Command interface and provides methods to execute, undo, and redo the removal operation.
 */
public class Remove implements Command {
    private final AsciiPaint paint;
    private final int shapeIndex;
    private Shape removedShape;

    /**
     * Constructs a Remove command with the specified AsciiPaint and shape index.
     *
     * @param paint      the AsciiPaint instance representing the ASCII art drawing.
     * @param shapeIndex the index of the shape to be removed.
     */
    public Remove(AsciiPaint paint, int shapeIndex) {
        this.paint = paint;
        this.shapeIndex = shapeIndex;
        this.removedShape = null;
    }

    @Override
    public void execute() {
        this.removedShape = paint.getShapeAt(shapeIndex);
        paint.removeShape(shapeIndex);
    }

    @Override
    public void undo() {
        if (removedShape != null) {
            paint.addShapeAt(shapeIndex, removedShape);
        }
    }

    @Override
    public void redo() {
        execute();
    }
}
