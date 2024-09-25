package g56583.atl.ascii.DesignPattern.Command;

import g56583.atl.ascii.model.AsciiPaint;

/**
 * The Color class implements the Command interface to handle color changes of shapes in an AsciiPaint instance.
 * It changes the color of a specific shape based on the shape index and the new color provided.
 */
public class Color implements Command {
    private final AsciiPaint asciiPaint;
    private final int shapeIndex;
    private final char newColor;
    private char oldColor;

    /**
     * Constructs a new Color command with a reference to AsciiPaint and the input parameters for changing color.
     *
     * @param asciiPaint The AsciiPaint instance in which the shape's color will be changed.
     * @param inputs     The parameters for the color change, including the shape index and the new color.
     */
    public Color(AsciiPaint asciiPaint, String[] inputs) {
        this.asciiPaint = asciiPaint;
        this.shapeIndex = Integer.parseInt(inputs[1]);
        this.newColor = validateColor(inputs[2]);
    }

    @Override
    public void execute() {
        try {
            oldColor = asciiPaint.getColorOfShape(shapeIndex);
            asciiPaint.changeShapeColor(shapeIndex, newColor);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid shape index or color.");
        }
    }

    /**
     * Validates the color input and returns the validated color.
     *
     * @param input the color input to validate
     * @return the validated color
     */
    private char validateColor(String input) {
        if (!input.matches("[A-Za-z]")) {
            throw new IllegalArgumentException("Invalid color. Color must be a single letter from A to Z.");
        }
        return input.toUpperCase().charAt(0);
    }

    @Override
    public void undo() {
        asciiPaint.changeShapeColor(shapeIndex, oldColor);
    }

    @Override
    public void redo() {
        asciiPaint.changeShapeColor(shapeIndex, newColor);
    }


}
