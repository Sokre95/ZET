package fer.ruazosa.ruazosa16_zet.presenters;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import java.util.ArrayList;

import fer.ruazosa.ruazosa16_zet.model.Trip;
import fer.ruazosa.ruazosa16_zet.wrappers.TripView;

public class TripPresenter extends MvpLceRxPresenter<TripView, ArrayList<Trip>>{

    public TripPresenter() {
    }
}
