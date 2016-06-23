package fer.ruazosa.ruazosa16_zet;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import fer.ruazosa.ruazosa16_zet.wrappers.TripView;

public interface TripPresenter extends MvpPresenter<TripView> {
    void loadLines(boolean pullToRefresh);
}
