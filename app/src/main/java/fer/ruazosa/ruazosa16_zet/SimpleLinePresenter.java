package fer.ruazosa.ruazosa16_zet;

import android.database.Observable;
import android.util.Log;
import android.widget.ListView;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.io.IOException;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SimpleLinePresenter extends MvpBasePresenter<LineView> implements LinePresenter {

    public SimpleLinePresenter() {
    }

    @Override
    public void loadLines(boolean pullToRefresh) {

        if(!isViewAttached()) return;   // No attached view!
        getView().showLoading(pullToRefresh);

        try {
            ZetWebService zet = ZetWebService.getInstance();
            rx.Observable<List<String>> routes = zet.getDailyBusRoutes();
                    routes.subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread())
                       .subscribe(new Subscriber<List<String>>() {
                           @Override
                           public void onCompleted() {
                           }

                           @Override
                           public void onError(Throwable e) {
                           }

                           @Override
                           public void onNext(List<String> strings) {
                               getView().setData(strings);
                           }
                       });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void attachView(LineView view) {
        super.attachView(view);
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
