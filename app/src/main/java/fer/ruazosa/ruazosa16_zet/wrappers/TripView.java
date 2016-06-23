package fer.ruazosa.ruazosa16_zet.wrappers;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.ArrayList;

import fer.ruazosa.ruazosa16_zet.model.Trip;

public interface TripView extends MvpLceView<ArrayList<Trip>> {
    void setData(ArrayList<Trip> data);
}
