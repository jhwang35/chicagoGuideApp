package edu.uic.cs478.a2project3;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AttractionActivity extends AppCompatActivity {

    private boolean isAttractionDisplayed = false;
    private boolean isRestaurantDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment); // Ensure this points to the correct layout file that includes the FragmentContainerView

        // Define your list of locations
        List<Location> attractionList = new ArrayList<>();
        attractionList.add(new Location("Lincoln Park Zoo", "https://www.lpzoo.org/"));
        attractionList.add(new Location("Navy Pier", "https://navypier.org/"));
        attractionList.add(new Location("Museum of Science and Industry", "https://www.msichicago.org/"));
        attractionList.add(new Location("Art Institute", "https://www.artic.edu/"));
        attractionList.add(new Location("TILT", "https://360chicago.com/tilt"));

        // Pass the list to the ViewModel
        ListViewModel viewModel = new ViewModelProvider(this).get(ListViewModel.class);
        viewModel.setLocationList(attractionList);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set the overflow menu icon color
        Drawable overflowIcon = toolbar.getOverflowIcon();
        if (overflowIcon != null) {
            overflowIcon.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);

        }

        // Only load the fragment if this is the first time the activity is being created (to avoid replacing it on configuration change)
        if (savedInstanceState == null) {
            // Create and load the fragment dynamically
            LocationListFragment locationListFragment = new LocationListFragment();


            // Replace the container with the LocationListFragment
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainerView, locationListFragment);
            transaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflates the overflow menu that was created in options_menu.xml file
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (Objects.requireNonNull(item.getTitle()).toString()) {
            case "View Attractions":
                //check if page is already shown and manually switch activity if not
                if (!isAttractionDisplayed) {
                    Intent intent = new Intent(this, AttractionActivity.class);
                    startActivity(intent);
                    isAttractionDisplayed = true;
                    isRestaurantDisplayed = false;

                }
                return true;
            case "View Restaurants":
                if (!isRestaurantDisplayed) { // send broadcast out for restaurant page to show
                    Intent intent = new Intent(this, RestaurantActivity.class);
                    startActivity(intent);
                    isAttractionDisplayed = false;
                    isRestaurantDisplayed = true;
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
