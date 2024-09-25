package g56583.atl.ascii.DesignPattern.Command;

import g56583.atl.ascii.DesignPattern.Composite.Composite;
import g56583.atl.ascii.model.AsciiPaint;

/**
 * The Ungroup class represents a command that ungroups a composite shape in an ASCII art drawing.
 * It implements the Command interface and provides methods to execute, undo, and redo the ungrouping operation.
 */
public class Ungroup implements Command {
    private final AsciiPaint paint;
    private final int groupIndex;
    private Composite originalGroup;

    /**
     * Constructs an Ungroup command with the specified AsciiPaint and group index.
     *
     * @param paint      the AsciiPaint instance representing the ASCII art drawing.
     * @param groupIndex the index of the group to be ungrouped.
     */
    public Ungroup(AsciiPaint paint, int groupIndex) {
        this.paint = paint;
        this.groupIndex = groupIndex;
    }

    @Override
    public void execute() {
        this.originalGroup = paint.getGroup(groupIndex);
        paint.ungroupShape(groupIndex);
    }

    @Override
    public void undo() {
        paint.regroup(originalGroup);
    }

    @Override
    public void redo() {
        execute();
    }
}