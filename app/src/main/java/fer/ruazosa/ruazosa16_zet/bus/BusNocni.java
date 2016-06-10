package fer.ruazosa.ruazosa16_zet.bus;


import java.io.IOException;

import fer.ruazosa.ruazosa16_zet.LineFragment;
import fer.ruazosa.ruazosa16_zet.presenters.MvpLceRxPresenter;
import fer.ruazosa.ruazosa16_zet.presenters.NightBusPresenter;
import fer.ruazosa.ruazosa16_zet.ZetWebService;


public class BusNocni extends LineFragment {

    public BusNocni() {
    }

    @Override
    public MvpLceRxPresenter createPresenter() {
        return new NightBusPresenter();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        try {
            presenter.subscribe(ZetWebService.getInstance().getNightlyBusRoutes(), pullToRefresh);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

}
