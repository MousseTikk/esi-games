package g56583.atl.ascii.DesignPattern.Composite;

import g56583.atl.ascii.model.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * The Composite class represents a composite shape in the Composite design pattern.
 * It extends the Component class and can contain other Component instances,
 * including other Composites, thus forming a tree structure of shapes.
 */
public class Composite extends Component {
    private final List<Component> children ;

    /**
     * Constructs a Composite object with the specified color.
     *
     * @param color The character representing the color of the composite shape.
     */
    public Composite(char color) {
        super(color);
        this.children = new ArrayList<>();
    }

    public Composite(Composite other) {
        super(other.getColor());
        this.children = new ArrayList<>();
        for (Component child : other.getChildren()) {
            this.children.add(child.copy());
        }
    }


    /**
     * Adds a child component to this composite.
     *
     * @param component The component to be added.
     */
    public void add(Component component) {
        children.add(component);
    }

    /**
     * Gets the list of child components in this composite.
     *
     * @return A list of child components.
     */
    public List<Component> getChildren() {
        return children;
    }

    /**
     * Moves the composite and all its children by the specified distances.
     *
     * @param dx The horizontal distance to move the composite.
     * @param dy The vertical distance to move the composite.
     */
    @Override
    public void move(double dx, double dy) {
        for (Component child : children) {
            child.move(dx, dy);
        }
    }

    /**
     * Checks if a given point is inside any of the child components of this composite.
     *
     * @param p The point to be checked.
     * @return True if the point is inside any child component, false otherwise.
     */
    @Override
    public boolean isInside(Point p) {
        for (Component child : children) {
            if (child.isInside(p)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Composite copy() {
        Composite copy = new Composite(this.getColor());
        for (Component child : this.children) {
            copy.add(child.copy());
        }
        return copy;
    }
}