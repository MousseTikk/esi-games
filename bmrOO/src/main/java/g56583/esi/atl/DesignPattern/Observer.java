package g56583.esi.atl.DesignPattern;

/**
 * The `Observer` interface defines the contract for objects that wish to be notified
 * of changes in observable objects. Classes that implement this interface can
 * receive notifications when an observable object changes.
 */
public interface Observer {
    /**
     * This method is called by an observable object when a change occurs.
     * Implement this method to specify the actions to be taken in response to the change.
     */
    public void update();
}
