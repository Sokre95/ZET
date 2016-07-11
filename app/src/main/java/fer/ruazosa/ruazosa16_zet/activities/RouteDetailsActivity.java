package fer.ruazosa.ruazosa16_zet.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateActivity;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.SerializeableLceViewState;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import fer.ruazosa.ruazosa16_zet.R;
import fer.ruazosa.ruazosa16_zet.RouteDetailsView;
import fer.ruazosa.ruazosa16_zet.TripView;
import fer.ruazosa.ruazosa16_zet.ZetWebService;
import fer.ruazosa.ruazosa16_zet.adapters.RouteDetailsAdapter;
import fer.ruazosa.ruazosa16_zet.adapters.TripAdapter;
import fer.ruazosa.ruazosa16_zet.model.Trip;
import fer.ruazosa.ruazosa16_zet.presenters.MvpLceRxPresenter;
import fer.ruazosa.ruazosa16_zet.presenters.RouteDetailsPresenter;
import fer.ruazosa.ruazosa16_zet.service.ZETService;

public class RouteDetailsActivity extends MvpLceViewStateActivity<SwipeRefreshLayout, ArrayList<String>,
        RouteDetailsView, MvpLceRxPresenter<RouteDetailsView, ArrayList<String>>>
        implements RouteDetailsView, SwipeRefreshLayout.OnRefreshListener {

    private static final String DEPARTURE_TIME = "Departure time";
    private static final String LINE_NUMBER = "Line number";
    private static final String DIRECTION = "Direction";
    private static final String DIRECTION_NAME = "Direction name";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.list)
    RecyclerView recyclerView;

    private int direction;
    private String lineNumber;
    private String departureTime;
    private String directionName;

    private RouteDetailsAdapter routeDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        routeDetailsAdapter = new RouteDetailsAdapter();
        recyclerView.setAdapter(routeDetailsAdapter);

        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("DATA");

        direction = bundle.getInt(DIRECTION);
        lineNumber = bundle.getString(LINE_NUMBER).trim();
        departureTime = bundle.getString(DEPARTURE_TIME);
        directionName = bundle.getString(DIRECTION_NAME);

        contentView.setOnRefreshListener(this);

        getSupportActionBar().setTitle(lineNumber + " Smjer : " + directionName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    @NonNull
    @Override
    public MvpLceRxPresenter<RouteDetailsView, ArrayList<String>> createPresenter() {
        return new RouteDetailsPresenter();
    }

    @Override
    public ArrayList<String> getData() {
        return routeDetailsAdapter.getDetails();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    @Override
    public void setData(ArrayList<String> data) {
        routeDetailsAdapter.setDetails(data);

    }

    @Override
    public void loadData(boolean pullToRefresh) {
        try {
            presenter.subscribe(ZetWebService.getInstance().
                    getTripStationsTimes(Integer.parseInt(lineNumber),
                            direction, departureTime), pullToRefresh);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @Override
    public LceViewState<ArrayList<String>, RouteDetailsView> createViewState() {
        setRetainInstance(true);
        return new SerializeableLceViewState<ArrayList<String>, RouteDetailsView>();
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }
}
