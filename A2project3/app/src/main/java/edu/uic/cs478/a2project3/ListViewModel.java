package edu.uic.cs478.a2project3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

/*
 * stores and manages UI-related data
 * shares data between activities and fragments
 */
public class ListViewModel extends ViewModel{
    private final MutableLiveData<List<Location>> locationList = new MutableLiveData<>();

    public void setLocationList(List<Location> locations) {
        locationList.setValue(locations);
    }

    public LiveData<List<Location>> getLocationList() {
        return locationList;
    }
}
