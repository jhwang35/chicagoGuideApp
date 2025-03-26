package edu.uic.cs478.A1project3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class ActivityA1 extends AppCompatActivity {

    protected Button attractions;
    protected Button restaurants;

    private static final String ATTRACTION_INTENT = "edu.uic.cs478.ActivityA1.showAttractions";
    private static final String RESTAURANT_INTENT = "edu.uic.cs478.ActivityA1.showRestaurants";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Bind interface elements (buttons)
        attractions = findViewById(R.id.b1);
        restaurants = findViewById(R.id.b2);

        // Set up listeners for the buttons
        attractions.setOnClickListener(attractionListener);
        restaurants.setOnClickListener(restaurantListener);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public View.OnClickListener attractionListener = v -> {
        // send broadcast out for attractions page to show
        Intent aIntent = new Intent(ATTRACTION_INTENT);
        Toast.makeText(this,"Attractions Requested", Toast.LENGTH_SHORT).show();
        sendBroadcast(aIntent, null);

    };

    public View.OnClickListener restaurantListener = v -> {
        // send broadcast out for restaurant page to show
        Intent rIntent = new Intent(RESTAURANT_INTENT);
        Toast.makeText(this,"Restaurants Requested", Toast.LENGTH_SHORT).show();
        sendBroadcast(rIntent,null);
    };
}