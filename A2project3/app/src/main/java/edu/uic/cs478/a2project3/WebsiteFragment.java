package edu.uic.cs478.a2project3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class WebsiteFragment extends Fragment {

    private ListView listView;
    private WebView webView;
    private LocationAdapter adapter;
    private ListViewModel listViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment_website layout
        View view = inflater.inflate(R.layout.fragment_website, container, false);

        // Initialize WebView and ListView
        webView = view.findViewById(R.id.webview);
        listView = view.findViewById(R.id.listView);
        webView.clearCache(true);

        // Load the URL passed as an argument into the WebView (if available)
        if (getArguments() != null && getArguments().containsKey("url")) {
            String url = getArguments().getString("url");
            if (url != null && !url.isEmpty()) {
                webView.loadUrl(url);
            }
        }

        // Set up the ListView with the adapter
        adapter = new LocationAdapter(getContext());  // Initialize with an empty list
        listView.setAdapter(adapter);

        // Set up the ViewModel to observe the list of locations
        listViewModel = new ViewModelProvider(requireActivity()).get(ListViewModel.class);
        listViewModel.getLocationList().observe(getViewLifecycleOwner(), new Observer<List<Location>>() {
            @Override
            public void onChanged(List<Location> locations) {
                // Update the adapter when the location list changes
                if (adapter != null) {
                    adapter.updateLocations(locations);
                }
            }
        });

        // Handle item clicks on the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Location selectedLocation = adapter.getItem(position);
                if (selectedLocation != null && webView != null) {
                    // Load the selected location's URL into the WebView
                    webView.loadUrl(selectedLocation.getSiteUrl());
                }
            }
        });

        return view;
    }
}