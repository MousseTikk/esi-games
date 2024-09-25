package g56583.atl.ascii.DesignPattern.Command;

public interface Command {
    /**
     * Executes the specified command
     */
    void execute();

    /**
     * Undoes the specified command
     */
    void undo();

    /**
     * redoes the specified command
     */
    void redo();
}
