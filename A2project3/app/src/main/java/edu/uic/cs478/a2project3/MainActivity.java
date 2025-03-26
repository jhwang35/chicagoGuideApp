package edu.uic.cs478.a2project3;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    private AttractionReceiver attractionReceiver;
    private RestaurantReceiver restaurantReceiver;

    private static final String ATTRACTION_INTENT = "edu.uic.cs478.ActivityA1.showAttractions";
    private static final String RESTAURANT_INTENT = "edu.uic.cs478.ActivityA1.showRestaurants";

    private boolean isAttractionDisplayed = false;
    private boolean isRestaurantDisplayed = false;


    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        attractionReceiver = new AttractionReceiver();
        restaurantReceiver = new RestaurantReceiver();

        // create intent filters for attractions and restaurants
        // filters make it so we only look for specific broadcasts

        IntentFilter filter1; // filters for attractions
        IntentFilter filter2; // filters for restaurants

        filter1 = new IntentFilter(ATTRACTION_INTENT);
        filter2 = new IntentFilter(RESTAURANT_INTENT);

        //register our receivers with the filters
        registerReceiver(attractionReceiver, filter1, Context.RECEIVER_EXPORTED);
        registerReceiver(restaurantReceiver, filter2, Context.RECEIVER_EXPORTED);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set the overflow menu icon color
        Drawable overflowIcon = toolbar.getOverflowIcon();
        if (overflowIcon != null) {
            overflowIcon.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


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

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(attractionReceiver);
        unregisterReceiver(restaurantReceiver);
    }
}