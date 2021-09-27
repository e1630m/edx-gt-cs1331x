/**
 * Represents the List Abstract Data Type
 * @author 1331 TA's
 * @version 1.0
 * @param <T> The type of elements in this list
 */
public interface List<T> {

    /**
     * Adds the passed in data to the specified index.
     * @param data  the data to add to the List
     * @param index the index to add at
     */
    void addAtIndex(T data, int index);

    /**
     * Retrieves the data of the node that's specified.
     * @param index the index of the node whose data we are retrieving
     */
    T getAtIndex(int index);

    /**
     * Removes the data at the specified index and returns the data that was removed.
     * @param index removes the Node at this index
     */
    T removeAtIndex(int index);

    /**
     * Removes the Node with the data passed in if a Node containing the data exists.
     * @param data the data to remove from the List
     */
    T remove(T data);

    /**
     * Clears the LinkedList - garbage collection is your friend here.
     * THIS SOLUTION SHOULD CAN BE O(1)
     */
    void clear();

    /**
     * Checks whether the LinkedList is empty or not.
     * @return boolean value indicating whether it's empty or not
     */
    boolean isEmpty();

    /**
     * Returns the size of the List
     * @return integer size of the list
     */
    int size();

}
