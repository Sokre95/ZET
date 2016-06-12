package fer.ruazosa.ruazosa16_zet.tram;


import java.io.IOException;

import fer.ruazosa.ruazosa16_zet.LineFragment;
import fer.ruazosa.ruazosa16_zet.presenters.MvpLceRxPresenter;
import fer.ruazosa.ruazosa16_zet.presenters.NightTramPresenter;
import fer.ruazosa.ruazosa16_zet.ZetWebService;


public class TramNocni extends LineFragment {

    public TramNocni() {
    }

    @Override
    public MvpLceRxPresenter createPresenter() {
        return new NightTramPresenter();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        try {
            presenter.subscribe(ZetWebService.getInstance().getNightlyTramRoutes(), pullToRefresh);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

}
