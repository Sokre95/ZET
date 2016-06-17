package fer.ruazosa.ruazosa16_zet.bus;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.SerializeableLceViewState;

import java.io.IOException;
import java.util.ArrayList;

import fer.ruazosa.ruazosa16_zet.LineView;
import fer.ruazosa.ruazosa16_zet.R;
import fer.ruazosa.ruazosa16_zet.model.Line;
import fer.ruazosa.ruazosa16_zet.presenters.DailyBusPresenter;
import fer.ruazosa.ruazosa16_zet.LineFragment;
import fer.ruazosa.ruazosa16_zet.presenters.MvpLceRxPresenter;
import fer.ruazosa.ruazosa16_zet.ZetWebService;

public class BusDnevni extends LineFragment {

    public BusDnevni() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bus_dnevni, container, false);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        if(rotated) {
            rotated = false;
            return;
        } else {
            try {
                presenter.subscribe(ZetWebService.getInstance().getDailyBusRoutes(), pullToRefresh);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public MvpLceRxPresenter createPresenter() {
        return new DailyBusPresenter();
    }

}
