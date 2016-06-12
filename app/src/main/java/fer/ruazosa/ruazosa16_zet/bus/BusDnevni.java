package fer.ruazosa.ruazosa16_zet.bus;


import java.io.IOException;

import fer.ruazosa.ruazosa16_zet.presenters.DailyBusPresenter;
import fer.ruazosa.ruazosa16_zet.LineFragment;
import fer.ruazosa.ruazosa16_zet.presenters.MvpLceRxPresenter;
import fer.ruazosa.ruazosa16_zet.ZetWebService;

public class BusDnevni extends LineFragment {

    public BusDnevni() {
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        try {
            presenter.subscribe(ZetWebService.getInstance().getDailyBusRoutes(), pullToRefresh);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MvpLceRxPresenter createPresenter() {
        return new DailyBusPresenter();
    }

}
