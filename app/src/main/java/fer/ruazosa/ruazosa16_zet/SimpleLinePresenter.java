package fer.ruazosa.ruazosa16_zet;

import android.widget.ListView;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

public class SimpleLinePresenter extends MvpBasePresenter<LineView> implements LinePresenter {

    public SimpleLinePresenter() {
    }

    @Override
    public void loadLines(boolean pullToRefresh) {

        if(!isViewAttached()) return;   // No attached view!
        getView().showLoading(pullToRefresh);

    }

    @Override
    public void attachView(LineView view) {

    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
