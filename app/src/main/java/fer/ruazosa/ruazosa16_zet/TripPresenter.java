package fer.ruazosa.ruazosa16_zet;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

public interface TripPresenter extends MvpPresenter<TripView> {
    void loadLines(boolean pullToRefresh);
}
