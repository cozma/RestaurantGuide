package cs2114.restaurant;

import java.util.NoSuchElementException;
import java.util.Iterator;
import cs2114.restaurant.CircularList;

// -------------------------------------------------------------------------
/**
 * The main list class
 *
 * @author Dagmawi Yeshiwas dagmawi
 * @version Mar 31, 2013 type of parameter
 * @param <E>
 *            Type of element being used
 */
public class CircularLinkedList<E>
    implements CircularList<E>, Iterable<E>
{
    private Node<E> currentPos;

    private int     size;      // the size of the list


    // ----------------------------------------------------------
    /**
     * Create a new CircularLinkedList object.
     */
    public CircularLinkedList()
    {
        size = 0;
        currentPos = null;
    }


    /**
     * constructor for the iterator
     *
     * @return a new list iterator
     */
    public Iterator<E> iterator()
    {
        return new ListIterator();
    }


    // -------------------------------------------------------------------------
    /**
     * This is the iterator class for the Circular Linked List
     *
     * @author Dagmawi Yeshiwas dagmawi
     * @version Apr 2, 2013
     */
    public class ListIterator
        implements Iterator<E>
    {
        private int     position;
        private Node<E> node;


        /**
         * constructor
         */
        public ListIterator()
        {
            position = 0;
            node = currentPos;
        }


        /**
         * This returns a boolean of whether there is something next in the
         * iteration
         *
         * @return whether there is a next item
         */
        public boolean hasNext()
        {
            if (size > position)
            {
                return true;
            }
            else
            {
                return false;
            }
        }


        /**
         * returns the next item
         *
         * @return the next item
         */
        public E next()
        {
            if (hasNext())
            {
                E data = node.data();
                position++;
                node = node.next();
                return data;

            }
            else
            {
                throw new NoSuchElementException("There is no element"
                    + " remaining in the iterator. The end "
                    + "has been reached.");
            }
        }


        /**
         * Remove an item
         */
        public void remove()
        {

            throw new UnsupportedOperationException("Unsupported Exception");

        }
    }


    /**
     * takes the circular list and adds a position to it
     *
     * @param data
     *            the data that is input
     */
    public void add(E data)
    {
        if (size == 0)
        {
            Node<E> nodeNew = new Node<E>(data);
            nodeNew.join(nodeNew);
            currentPos = nodeNew;
            size++;
        }
        else
        {
            Node<E> before = currentPos.previous();
            before.split();
            Node<E> addPos = new Node<E>(data);
            before.join(addPos);
            addPos.join(currentPos);
            currentPos = addPos;
            size++;
        }
    }


    /**
     * makes a new circular linked list
     */
    public void clear()
    {
        new CircularLinkedList<E>();
        size = 0;
    }


    /**
     * gets the current position
     *
     * @return the current position
     */
    public E getCurrent()
    {
        ListIterator listIter = new ListIterator();
        if (listIter.hasNext())
        {
            return currentPos.data();
        }
        else
        {
            throw new NoSuchElementException("No such element exception was"
                + " thrown because the list is Empty");
        }
    }


    /**
     * sets the current position to the next position
     */
    public void next()
    {
        if (size > 0)
        {
            Node<E> nextPos = currentPos.next();
            currentPos = nextPos;
        }

    }


    /**
     * Sets the current position the the previous position
     */
    public void previous()
    {
        if (size > 0)
        {
            Node<E> previous = currentPos.previous();
            currentPos = previous;
        }

    }


    /**
     * removes the current position
     *
     * @return the data of the current position
     */
    public E removeCurrent()
    {
        if (size() > 0)
        {
            Node<E> current = currentPos;
            E data = current.data();
            if (size() == 1)
            {
                clear();
            }
            else
            {
                Node<E> previousCur = currentPos.previous();
                Node<E> nextCur = currentPos.next();
                previousCur.split();
                currentPos.split();
                previousCur.join(nextCur);
                currentPos = nextCur;
                size--;
            }
            return data;
        }
        else
        {
            throw new NoSuchElementException("No such element exception was"
                + " thrown because the list is Empty");
        }

    }


    /**
     * returns the size of the LinkedList
     *
     * @return the size of the list
     */
    public int size()
    {
        return size;
    }


    /**
     * turns the list to string output
     *
     * @return list the list of the data in string form
     */
    public String toString()
    {
        String listOfPos = new String("");
        for (int i = 0; i < size(); i++)
        {
            currentPos = currentPos.previous();
            listOfPos = (String)getCurrent() + ", " + listOfPos;
        }
        String finalList = new String("");
        if (!listOfPos.isEmpty())
        {
            finalList = listOfPos.substring(0, listOfPos.length() - 2);
        }
        return "[" + finalList + "]";
    }

}
