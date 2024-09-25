package g56583.atl.ascii.DesignPattern.Command;

import g56583.atl.ascii.DesignPattern.Composite.Composite;
import g56583.atl.ascii.model.AsciiPaint;
import g56583.atl.ascii.model.Shape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Group class represents a command that groups a set of shapes in an ASCII art drawing.
 * It implements the Command interface and provides methods to execute, undo, and redo the grouping operation.
 * Shapes to be grouped are identified by their indices in the AsciiPaint's list of shapes.
 */
public class Group implements Command {
    private final AsciiPaint paint;
    private final List<Integer> indices;
    private List<Shape> originalShapes;

    private Composite previousGroup;

    /**
     * Constructs a Group command with the specified AsciiPaint and array of indices.
     *
     * @param paint   the AsciiPaint instance representing the ASCII art drawing.
     * @param indices an array of indices of shapes to be grouped.
     */
    public Group(AsciiPaint paint, String[] indices) {
        this.paint = paint;
        this.indices = Arrays.stream(indices)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        this.previousGroup = null;
    }

    @Override
    public void execute() {
        if (indices.isEmpty()) {
            throw new IllegalArgumentException("No indices provided for grouping.");
        }
        char groupColor = paint.getColorOfShape(indices.get(0));
        List<Shape> list = new ArrayList<>();
        for (Integer index : indices) {
            Shape shapeAt = paint.getShapeAt(index);
            list.add(shapeAt);
        }
        originalShapes = list;
        previousGroup = paint.createGroup(indices, groupColor);
    }

    @Override
    public void undo() {
        if (previousGroup != null) {
            paint.ungroupSpecificGroup(previousGroup);
        }

    }

    @Override
    public void redo() {
        if (originalShapes != null) {
            paint.recreateGroup(originalShapes, previousGroup.getColor());
        }
    }
}
