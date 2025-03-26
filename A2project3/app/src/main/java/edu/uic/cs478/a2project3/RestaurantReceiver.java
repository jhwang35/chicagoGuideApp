package edu.uic.cs478.a2project3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class RestaurantReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // switch to restaurant activity when broadcast is received
        Toast.makeText(context, "RestaurantReceiver in action! ", Toast.LENGTH_SHORT).show();
        Intent rIntent = new Intent(context, RestaurantActivity.class);
        context.startActivity(rIntent);
    }
}
