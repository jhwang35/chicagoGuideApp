package edu.uic.cs478.a2project3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AttractionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // switch to attraction activity when broadcast is received
        Toast.makeText(context, "AttractionReceiver in action! ", Toast.LENGTH_SHORT).show();
        Intent aIntent = new Intent(context, AttractionActivity.class);
        context.startActivity(aIntent);

    }
}
