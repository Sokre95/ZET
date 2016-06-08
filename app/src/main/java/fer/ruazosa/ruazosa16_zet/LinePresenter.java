package fer.ruazosa.ruazosa16_zet;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

public interface LinePresenter extends MvpPresenter<LineView> {

    public void loadLines(boolean pullToRefresh);

}
