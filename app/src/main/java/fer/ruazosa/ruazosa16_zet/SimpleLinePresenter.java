package fer.ruazosa.ruazosa16_zet;

import android.database.Observable;
import android.util.Log;
import android.widget.ListView;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.io.IOException;
import java.util.List;

import fer.ruazosa.ruazosa16_zet.service.Line;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class SimpleLinePresenter extends MvpBasePresenter<LineView> implements LinePresenter {

    public SimpleLinePresenter() {
    }

    @Override
    public void loadLines(final boolean pullToRefresh) {

        if(!isViewAttached()) return;   // No attached view!

        try {
            ZetWebService zet = ZetWebService.getInstance();
            rx.Observable<List<Line>> routes = zet.getDailyBusRoutes();
                    routes.subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread())
                            .onErrorReturn(new Func1<Throwable, List<Line>>() {
                                @Override
                                public List<Line> call(Throwable throwable) {
                                    getView().showError(throwable, pullToRefresh);
                                    return null;
                                }
                            })
                       .subscribe(new Subscriber<List<Line>>() {
                           @Override
                           public void onCompleted() {
                               return;
                           }

                           @Override
                           public void onError(Throwable e) {
                               getView().showError(e, pullToRefresh);
                           }

                           @Override
                           public void onNext(List<Line> strings) {
                               getView().setData(strings);
                           }

                       });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return;

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
