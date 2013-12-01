package cs2114.restaurant;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import realtimeweb.yelp.exceptions.BusinessSearchException;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author Dagmawi Yeshiwas dagmawi
 * @version 2013.04.15
 */
public class RestaurantScreenTests
    extends student.AndroidTestCase<RestaurantScreen>
{
    // ~ Fields ................................................................

    private Button   next;
    private Button   previous;
    private Button   viewMap;
    private TextView restaurantName;
    private EditText searchField;


    // ~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Test cases that extend AndroidTestCase must have a parameterless
     * constructor that calls super() and passes it the screen/activity class
     * being tested.
     */
    public RestaurantScreenTests()
    {
        super(RestaurantScreen.class);
    }


    // ~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Initializes the text fixtures.
     */
    public void setUp()
    {
        // Not Used
    }


    /**
     * Tests the case that the business search is completed successfully
     */
    public void testBusinessSearchCompleted()
    {
        enterText(searchField, "Blacksburg, VA");
        assertEquals(
            "Virginia Polytechnic Institute and State University",
            restaurantName.getText());

    }


    /**
     * this tests the next button
     */
    public void testNext()
    {
        enterText(searchField, "Blacksburg, VA");
        click(next);
    }


    /**
     * this tests the previous button
     */
    public void testPrevious()
    {
        enterText(searchField, "Blacksburg, VA");
        click(previous);
    }


    /**
     * Tests the case that the business search fails
     */
    public void testBusinessSearchFailed()
    {
        getInstrumentation().runOnMainSync(new Runnable() {
            public void run()
            {
                getScreen().businessSearchFailed(
                    new BusinessSearchException("", "", ""));
            }
        });
        assertFalse(next.isEnabled());
        assertFalse(previous.isEnabled());
        assertFalse(viewMap.isEnabled());
    }


    /**
     * Tests the view map method
     */
    public void testViewMap()
    {
        prepareForUpcomingActivity(Intent.ACTION_VIEW);
        enterText(searchField, "Blacksburg, VA");
        click(viewMap);
    }
}
