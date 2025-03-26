package edu.uic.cs478.a2project3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

// used to populate ListView and WebView from fragment xmls

public class LocationAdapter extends ArrayAdapter<Location> {

    private Context mContext;
    private List<Location> locationList;
    private LayoutInflater inflater;

    public LocationAdapter(Context context){
        super(context, 0);
        mContext = context;
        this.locationList = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);
    }

    public void updateLocations(List<Location> locations) {

        if (locations != null) {
            this.locationList.clear();
            this.locationList.addAll(locations);
            notifyDataSetChanged();

        }
    }

    @Override
    public int getCount() {
        return locationList.size();
    }

    @Override
    public Location getItem(int position) {
        return locationList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        // inflate list view and input our information
        if (convertView == null){
                convertView = inflater.inflate(R.layout.item_location, parent, false);

        }

        Location location = getItem(position);

        // Bind data to the UI elements inside the item layout
        TextView locationNameTextView = convertView.findViewById(R.id.locationName);  // Assuming a TextView with ID 'locationName'
        locationNameTextView.setText(location.getName());  // Set the location name to the TextView

        return convertView;
    }


}
