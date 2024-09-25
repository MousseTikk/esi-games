package g56583.atl.ascii.DesignPattern.Command;

import g56583.atl.ascii.model.AsciiPaint;
import g56583.atl.ascii.model.Drawing;

/**
 * The create class implements the Command interface to handle the creation of a new drawing in an AsciiPaint instance.
 * It initializes a new drawing area with specified width and height.
 */
public class Create implements Command {
    private final AsciiPaint  asciiPaint;
    private final int width;
    private final int height;
    private Drawing previousDrawing;

    /**
     * Constructs a new Create command with a reference to AsciiPaint and the dimensions for the new drawing area.
     *
     * @param asciiPaint The AsciiPaint instance in which a new drawing will be created.
     * @param width      The width of the new drawing area.
     * @param height     The height of the new drawing area.
     */
    public Create(AsciiPaint asciiPaint, int width, int height) {
        this.asciiPaint = asciiPaint;
        this.width = width;
        this.height = height;
    }

    @Override
    public void execute() {
        previousDrawing = asciiPaint.getDrawing();
        asciiPaint.createNewDrawing(width, height);

    }

    @Override
    public void undo() {
        asciiPaint.setDrawing(previousDrawing);
    }

    @Override
    public void redo() {
        asciiPaint.createNewDrawing(width, height);

    }
}
