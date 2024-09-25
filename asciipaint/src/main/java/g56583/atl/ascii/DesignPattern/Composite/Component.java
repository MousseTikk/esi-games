package g56583.atl.ascii.DesignPattern.Composite;

import g56583.atl.ascii.model.*;

/**
 * The abstract Component class represents a basic building block for composite shapes in an ASCII art drawing.
 * It extends the ColoredShape class, providing a common base for both individual shapes and composite groups.
 * Subclasses must implement specific functionality for drawing and moving.
 */
public abstract class Component extends ColoredShape {
    /**
     * Constructs a Component with the specified color.
     *
     * @param color the character representing the color of the component.
     */
    public Component(char color) {
        super(color);
    }

    @Override
    public abstract Component copy();


}
