import java.io.Serializable;
import java.util.NoSuchElementException;

/**
 * Queue.java.
 * Unique implementation of queue.
 * Used as unique lists of items in each slot of the vending machine.
 * @author Cory Berger
 * @author Avin Patel
 * @version 2/12/2021
 */
public class Queue<T> implements Serializable 
{ 
    private static final long serialVersionUID = 7721226330051047789L;

    private int size;
    private T first;  //beginning of queue
    private T last;   //end of queue
    private static final int DEFAULT_CAPACITY = 8;

    /**
     * Default constructor
     */
    public Queue() {
        this(DEFAULT_CAPACITY);
    }

    public Queue(int initialCapacity) {
        size = initialCapacity;
        first = null;
        last = null;
    }

    /**
     * Use this for when we want the 'Sold Out' icon to appear
     * @return true if queue is empty, otherwise returns false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the size of the queue
     * @return number of items in queue
     */
    public int size() {
        return size;
    }

    /**
     * Add an object to the queue
     * @return true if successful
     */
    public boolean add(T obj) {
        return false; //temp to see other errors
    }

    /**
     * Adds element to queue if doing so does not violate capacity restrictions
     * @return true if object added successfully.
     */
    public boolean offer(T obj) {
        try {
            return add(obj);
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Removes an item (i.e purchase) from the front of a slot in the vending machine 
     * @return the item removed
     */
    public T remove() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        return null; //temp
    }

    /**
     * Returns the first item in the queue without removing it.
     * @return the head of queue
     */
    public T element() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return null; //temp
    }
    
    /**
     * Returns the first item in the queue
     * @return the first object of queue, null if queue is empty
     */
    public T peek() {
        try {
            return element();
        } catch (Exception ex) {
            return null;
        }
    }

     /**
      * Returns the object at the front of queue
      * @return and remove the first object of queue, null if empty
      */
    public T poll() {
        try {
            return remove();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Clears the queue
     */
    public void clear() {
        first = null;
        size = 0;
    }  // We can do this using arrays(easier) or linkedlist ... arrayqueue or linkedqueue 
}