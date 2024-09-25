package g56583.esi.atl.DesignPattern;

/**
 * The `Observable` interface defines the contract for objects that can be observed
 * by other classes, called observers. Classes that implement this interface can
 * register and notify observers when changes occur.
 */
public interface Observable {
    /**
     * Notifies all registered observers that a change has occurred in the observable object.
     */
    public void notifyObserver();

    /**
     * Registers an observer to receive notifications from the observable object.
     *
     * @param obs The observer to be registered.
     */
    public void addObserver(Observer obs);

    /**
     * Removes an observer from the list of registered observers.
     *
     * @param obs The observer to be removed.
     */
    public void removeObserver(Observer obs);
}
