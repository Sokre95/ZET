package fer.ruazosa.ruazosa16_zet.tram;

import java.io.IOException;

import fer.ruazosa.ruazosa16_zet.presenters.DailyTramPresenter;
import fer.ruazosa.ruazosa16_zet.LineFragment;
import fer.ruazosa.ruazosa16_zet.presenters.MvpLceRxPresenter;
import fer.ruazosa.ruazosa16_zet.ZetWebService;

public class TramDnevni extends LineFragment {

    public TramDnevni() {
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        try {
            presenter.subscribe(ZetWebService.getInstance().getDailyTramRoutes(), pullToRefresh);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MvpLceRxPresenter createPresenter() {
        return new DailyTramPresenter();
    }

}
