package cs2114.restaurant;

import android.widget.EditText;
import realtimeweb.yelp.BusinessQuery;
import android.widget.TextView;
import realtimeweb.yelp.DetailedLocation;
import sofia.content.ContentViewer;
import android.net.Uri;
import android.widget.RatingBar;
import android.widget.ImageView;
import android.widget.Button;
import java.util.List;
import realtimeweb.yelp.BusinessSearch;
import realtimeweb.yelp.Business;
import realtimeweb.yelp.SearchResponse;
import realtimeweb.yelp.exceptions.BusinessSearchException;
import realtimeweb.yelp.BusinessSearchListener;
import sofia.app.Screen;

// -------------------------------------------------------------------------
/**
 * This is the screen class that will display the application
 *
 * @author Dagmawi Yeshiwas dagmawi
 * @version 2013.04.13
 */
public class RestaurantScreen
    extends Screen
    implements BusinessSearchListener
{

    // ~ Fields ................................................................

    private CircularLinkedList<Business> list;
    private BusinessSearch               info;
    private Button                       next;
    private Button                       previous;
    private Button                       viewMap;
    private ImageView                    imageView;
    private RatingBar                    ratingBar;
    private List<Business>               newBus;
    private TextView                     numRatings;
    private TextView                     restaurantName;
    private EditText                     searchField;


    // ~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * The initialization of the screen
     */
    public void initialize()
    {
        list = new CircularLinkedList<Business>();
        info = BusinessSearch.getInstance();
    }


    /**
     * Response to a completed search
     *
     * @param input
     *            the input search
     */
    public void businessSearchCompleted(SearchResponse input)
    {
        newBus = input.getBusinesses();
        // info.searchBusinesses(new BusinessQuery((Location)input), this);
        list.clear();

        for (Business bus : newBus)
        {
            list.add(bus);
        }
        ratingBar.setRating(list.getCurrent().getRating());
        String uriString = list.getCurrent().getImageUrl();
        Uri uri = Uri.parse(uriString);
        imageView.setImageURI(uri);
        numRatings.setText("(" + list.getCurrent().getReviewCount() + ")"
            + "ratings");
        restaurantName.setText(list.getCurrent().getName());

    }


    /**
     * Response to a failed search
     *
     * @param fail
     *            the exception of the fail of the search
     */
    public void businessSearchFailed(BusinessSearchException fail)
    {
        next.setEnabled(false);
        previous.setEnabled(false);
        viewMap.setEnabled(false);

    }


    /**
     * The next button is clicked
     */
    public void nextClicked()
    {
        list.next();

    }


    /**
     * The previous button is clicked
     */
    public void previousClicked()
    {
        list.previous();
    }


    /**
     * The view map button is clicked
     */
    public void viewMapClicked()
    {
        // display map location in internet browser
        DetailedLocation location = list.getCurrent().getLocation();

        String uriString =
            "http://maps.google.com/maps?q=" + location.getLatitude() + ","
                + location.getLongitude();
        Uri uri = Uri.parse(uriString);
        new ContentViewer(uri).start(this);
    }


    /**
     * The user is done editing the search field
     */
    public void searchFieldEditingDone()
    {
        BusinessQuery loc = new BusinessQuery(searchField.getText().toString());
        info.searchBusinesses(loc, this);
    }

}
