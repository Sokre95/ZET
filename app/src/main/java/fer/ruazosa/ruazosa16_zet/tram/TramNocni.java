package fer.ruazosa.ruazosa16_zet.tram;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;

import fer.ruazosa.ruazosa16_zet.LineFragment;
import fer.ruazosa.ruazosa16_zet.R;
import fer.ruazosa.ruazosa16_zet.model.Line;
import fer.ruazosa.ruazosa16_zet.presenters.MvpLceRxPresenter;
import fer.ruazosa.ruazosa16_zet.presenters.NightTramPresenter;
import fer.ruazosa.ruazosa16_zet.ZetWebService;

public class TramNocni extends LineFragment {

    public TramNocni() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tram_nocni, container, false);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        if(rotated) {
            rotated = false;
            return;
        } else {
            try {
                presenter.subscribe(ZetWebService.getInstance().getNightlyTramRoutes(), pullToRefresh);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    @Override
    public MvpLceRxPresenter createPresenter() {
        return new NightTramPresenter();
    }

}
