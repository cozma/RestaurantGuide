package cs2114.restaurant;

import java.util.Iterator;
import java.util.NoSuchElementException;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * This is the test class for Circular Linked List
 *
 * @author Dagmawi Yeshiwas dagmawi
 * @version Apr 2, 2013
 * @param <E>
 */
public class CircularLinkedListTest<E>
    extends TestCase
{
    private CircularLinkedList<String> list;


    /**
     * Creates a brand new, empty list for each test method.
     */
    public void setUp()
    {
        list = new CircularLinkedList<String>();
    }


    // ----------------------------------------------------------
    /**
     * Test the add() method.
     */
    public void testAdd()
    {
        list.add("bubbles");
        assertEquals(1, list.size());
    }


    /**
     * tests the clear method
     */
    public void testClear()
    {
        list.add("Bibbles");
        list.add("boobles");
        list.clear();
        assertEquals(0, list.size());
    }


    /**
     * tests the getting of the current position
     */
    public void testGetCurrent()
    {
        list.add("yellow");
        assertEquals("yellow", list.getCurrent());
    }


    /**
     * tests the setting of the next position in the list
     */
    public void testNext()
    {
        list.next();
        list.previous();
        list.add("Bibbles");
        list.add("boobles");

        list.next();
        assertEquals("Bibbles", list.getCurrent());

        list.previous();
        assertEquals("boobles", list.getCurrent());

        Iterator<String> iter = list.iterator();
        assertTrue(iter.hasNext());
    }


    /**
     * has next
     */
    public void testHasNext()
    {

        list.add("Bibbles");
        list.add("boobles");

        Iterator<String> iter = list.iterator();

        assertTrue(iter.hasNext());
        assertEquals("boobles", iter.next());

    }


    /**
     * next exemption for iterator
     */
    public void testNextException()
    {
        Iterator<String> iter = list.iterator();
        Exception thrown = null;
        try
        {
            iter.next();
        }
        catch (Exception e)
        {
            thrown = e;
        }

        assertTrue(thrown instanceof NoSuchElementException);
        assertEquals(
            "There is no element" + " remaining in the iterator. The end "
                + "has been reached.",
            thrown.getMessage());
    }


    /**
     * tests the remove method
     */
    public void testRemove()
    {
        Iterator<String> iter = list.iterator();
        Exception thrown = null;
        try
        {
            iter.remove();
        }
        catch (Exception e)
        {
            thrown = e;
        }

        assertTrue(thrown instanceof UnsupportedOperationException);
        assertEquals("Unsupported Exception", thrown.getMessage());
    }


    /**
     * tests the remove current position from the list
     */
    public void testRemoveCurrent()
    {

        list.add("A");
        list.add("B");
        list.add("C");

        list.removeCurrent();
        assertEquals("B", list.getCurrent());
    }


    /**
     * tests the remove current with 1 item in the list
     */
    public void testRemoveOne()
    {
        list.add("Bubbles");

        assertEquals("Bubbles", list.removeCurrent());
    }


    /**
     * tests the remove current exception
     */
    public void testRemoveCurrentException()
    {
        Exception thrown = null;
        try
        {
            list.removeCurrent();
        }
        catch (Exception e)
        {
            thrown = e;
        }

        assertTrue(thrown instanceof NoSuchElementException);
        assertEquals(
            "No such element exception was thrown because the list is Empty",
            thrown.getMessage());
    }


    /**
     * tests the get current exception
     */
    public void testGetCurrentException()
    {
        Exception thrown = null;
        try
        {
            list.getCurrent();
        }
        catch (Exception e)
        {
            thrown = e;
        }

        assertTrue(thrown instanceof NoSuchElementException);
        assertEquals(
            "No such element exception was thrown because the list is Empty",
            thrown.getMessage());
    }


    /**
     * tests the to string method
     */
    public void testToString()
    {
        assertEquals("[]", list.toString());

        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals("[C, B, A]", list.toString());

    }

}
