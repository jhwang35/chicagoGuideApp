package edu.uic.cs478.a2project3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class LocationListFragment extends Fragment {
    private ListView listView;
    private LocationAdapter adapter;
    private ListViewModel listViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        listView = view.findViewById(R.id.listView);
        adapter = new LocationAdapter(getContext());
        listView.setAdapter(adapter);

        listViewModel = new ViewModelProvider(requireActivity()).get(ListViewModel.class);
        // look for changes
        listViewModel.getLocationList().observe(getViewLifecycleOwner(), new Observer<List<Location>>() {
            @Override
            public void onChanged(List<Location> locations) {
                adapter.updateLocations(locations);
            }
        });

        //click opens web fragment
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected Location
                Location selectedLocation = adapter.getItem(position);
                // Create a new instance of WebsiteFragment and pass data via Bundle
                WebsiteFragment websiteFragment = new WebsiteFragment();
                Bundle bundle = new Bundle();
                bundle.putString("url", selectedLocation.getSiteUrl());
                bundle.putString("name", selectedLocation.getName());
                websiteFragment.setArguments(bundle);
                // Replace current fragment with WebsiteFragment and add to back stack if needed
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, websiteFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }
}
