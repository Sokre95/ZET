package fer.ruazosa.ruazosa16_zet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceFragment;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateFragment;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.SerializeableLceViewState;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fer.ruazosa.ruazosa16_zet.adapters.RouteAdapter;
import fer.ruazosa.ruazosa16_zet.model.Line;
import fer.ruazosa.ruazosa16_zet.presenters.MvpLceRxPresenter;

public abstract class LineFragment extends MvpLceFragment<SwipeRefreshLayout,
        ArrayList<Line>, LineView, MvpLceRxPresenter<LineView, ArrayList<Line>>>
        implements LineView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.list)
    RecyclerView recyclerView;
    protected RouteAdapter routeAdapter;

    ArrayList<Line> data = null;
    protected boolean rotated = false;

    public LineFragment() {
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        if(args != null) {
            data = (ArrayList<Line>)args.getSerializable("DATA");
            rotated = true;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        routeAdapter = new RouteAdapter(getContext());
        recyclerView.setAdapter(routeAdapter);
        contentView.setOnRefreshListener(this);
        if(data != null) {
            setData(data);
            showContent();
            rotated = false;
        } else {
            loadData(false);
        }
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return LinesErrorMessage.get(e, pullToRefresh, getContext());
    }

    @Override
    public abstract void loadData(boolean pullToRefresh);

    @Override
    public void setData(ArrayList<Line> data) {
        routeAdapter.setLines(data);
        routeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public abstract MvpLceRxPresenter createPresenter();

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        contentView.setRefreshing(false);
    }

    public ArrayList<Line> getData() {
        return routeAdapter.getLines();
    }

}
