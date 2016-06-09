package fer.ruazosa.ruazosa16_zet.bus;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fer.ruazosa.ruazosa16_zet.LinePresenter;
import fer.ruazosa.ruazosa16_zet.LineView;
import fer.ruazosa.ruazosa16_zet.LinesErrorMessage;
import fer.ruazosa.ruazosa16_zet.SimpleLinePresenter;
import fer.ruazosa.ruazosa16_zet.adapters.RouteAdapter;
import fer.ruazosa.ruazosa16_zet.data.ExampleData;
import fer.ruazosa.ruazosa16_zet.wrappers.Line;
import fer.ruazosa.ruazosa16_zet.R;

public class BusDnevni extends MvpLceFragment<SwipeRefreshLayout, List<String>, LineView, LinePresenter>
        implements LineView, SwipeRefreshLayout.OnRefreshListener {

    private List<Line> linije;
    @BindView(R.id.list) RecyclerView recyclerView;
    RouteAdapter routeAdapter;

    public BusDnevni() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bus_dnevni, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //linije = ExampleData.getDnevniBus();
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        routeAdapter = new RouteAdapter(getContext());
        recyclerView.setAdapter(routeAdapter);
        contentView.setOnRefreshListener(this);
        //loadData(false);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return LinesErrorMessage.get(e, pullToRefresh, getContext());
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadLines(pullToRefresh);
    }

    @Override
    public void setData(List<String> data) {
        routeAdapter.setLines(data);
        routeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public LinePresenter createPresenter() {
        return new SimpleLinePresenter();
    }

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

}
