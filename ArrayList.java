import java.util.Arrays;
import java.lang.IndexOutOfBoundsException;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.io.Serializable;

/**
 * ArrayList.java.
 * Unique implementation of ArrayList.
 * Used for each player's inventory.
 * @author Cory Berger
 * @author Avin Patel
 * @author Zane Yankalunas
 * @version 2/13/2021
 */

 public class ArrayList<T> implements Iterable<T>, Serializable 
 {
    private static final long serialVersionUID = 2442109487125007016L;

    private static final int DEFAULT_CAPACITY = 25;
    private Object[] list;
    private int capacity;
    public int size;

    /**
     * Default constructor
     */
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        capacity = initialCapacity;
        list = new Object[initialCapacity];
        size = 0;
    }

    /**
     * Adds obj to list at specified location
     * @param index
     * @param obj
     */
    public void add (int index, T obj) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Maybe try a different index 🤔");
        }
        if (size == capacity) {
            resizeArray(capacity * 2);
        }
        for (int i = size; i > index; i--) {
            list[i] = list[i-1];
        }
        list[index] = obj;
        size++;
    }

    /**
     * Adds obj at the end of list
     * @param obj
     */
    public void add (T obj) { 
        if (size == capacity) {
            resizeArray(capacity * 2);
        }
        list[size++] = obj;
    }

    /**
     * Removes obj at specified location
     * @param index
     * @return obj that was removed
     */
    public T remove (int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                "Sorry about the error :(\nHere's a fun fact: A deck of cards can be shuffled in more ways that the are atoms on earth"
            ); 
        }
        T obj = get(index);
        for (int i = index; i < size - 1; i++) {
            list[i] = list[i+1];
        }
        list[size--] = null;
        return obj;
    }

    /**
     * resizes the array
     * @param newCapacity
     * @return capacity - the new capacity
     */
    public int resizeArray(int newCapacity){
        list = Arrays.copyOf(list, newCapacity);
        return capacity = newCapacity;
    }

    /**
     * returns size of inventory
     * @return size
     */
    public int size() {
        return size;
    }
    
    /**
     * determines if the arraylost is mepty with a boolean value
     * @return true  - size is 0
     * @return false - size > 0
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the arraylist
     */
    public void clear() {
        list = new Object[capacity];
        size = 0;
    }
    
    /**
     * Makes a copy of the list as an array
     * @return copy of the array
     */
    public Object[] toArray() {
        return Arrays.copyOf(list, size);
    }

    /**
     * Returns the obj at specified index
     * @param index
     * @return obj
     */
    @SuppressWarnings("unchecked")
    public T get (int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Maybe dont use random numbers as input next time... ");
        }
        T obj = (T) list[index];
        return obj;
    }

    /**
     * Repalces item at specified index with a new obj
     * @param index
     * @param obj
     * @return obj that was there perviously
     */
    public T set(int index, T obj) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("I promise its not a random error just to annoy you ");
        }
        T oldObj = get(index);
        list[index] = obj;
        return oldObj;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int nextIndex = 0;

            public boolean hasNext() {
                return nextIndex < size;
            }

            @SuppressWarnings("unchecked")
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T)list[nextIndex++];
            }

            public boolean hasPrevious() {
                return nextIndex > 0;
            }
            
            @SuppressWarnings("unchecked")
            public T previous() {
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                return (T)list[nextIndex--];
            }
        };
    }
    //cant think of any other methods that we need -Avin
}