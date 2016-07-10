package fer.ruazosa.ruazosa16_zet.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateActivity;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.SerializeableLceViewState;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import fer.ruazosa.ruazosa16_zet.R;
import fer.ruazosa.ruazosa16_zet.TripView;
import fer.ruazosa.ruazosa16_zet.ZetWebService;
import fer.ruazosa.ruazosa16_zet.adapters.TripAdapter;
import fer.ruazosa.ruazosa16_zet.model.Line;
import fer.ruazosa.ruazosa16_zet.model.Trip;
import fer.ruazosa.ruazosa16_zet.presenters.MvpLceRxPresenter;
import fer.ruazosa.ruazosa16_zet.presenters.TripPresenter;

public class DetailsActivity extends MvpLceViewStateActivity<SwipeRefreshLayout, ArrayList<Trip>,
        TripView, MvpLceRxPresenter<TripView, ArrayList<Trip>>>
        implements TripView, SwipeRefreshLayout.OnRefreshListener {

    private static final String FAVOURITES = "FAVOURITES";
    private Spinner spinner;
    private String lineNumber;
    private int routeDirection = 0;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.list)
    RecyclerView recyclerView;
    @BindView(R.id.direction_name)
    TextView directionName;
    protected TripAdapter tripAdapter;
    private String lineName;

    private String smjer1;
    private String smjer2;
    private String trenutniSmjer;
    ActionMenuItemView favbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("DATA");
        lineNumber = bundle.getString("LINE NUMBER");
        lineName = bundle.getString("LINE NAME");

        String[] splitted = lineName.split("-");
        smjer1 = splitted[0].trim();
        smjer2 = splitted[1].trim();

        trenutniSmjer = smjer2;

        directionName.setText("Smjer : " + trenutniSmjer);

        getSupportActionBar().setTitle(lineNumber);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tripAdapter = new TripAdapter(this, lineNumber, routeDirection);
        recyclerView.setAdapter(tripAdapter);
        contentView.setOnRefreshListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.map_button :
                Intent i = new Intent(this, MapActivity.class);
                startActivity(i);
                break;
            case R.id.favorites_button :
                toggleAddRemoveFavourite();
                break;
            case R.id.direction_button :
                changeDirection();
                break;
            case android.R.id.home :
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggleAddRemoveFavourite() {
        SharedPreferences appPref = getSharedPreferences(FAVOURITES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = appPref.edit();
        ActionMenuItemView favbutton = (ActionMenuItemView) findViewById(R.id.favorites_button);
        if (appPref.contains(lineNumber)) {
            //remove from favourites
            editor.remove(lineNumber);
            //TODO make favourite button unchacked
            favbutton.setIcon(getResources().getDrawable(R.drawable.ic_favorites_unchecked));
            Toast.makeText(getApplicationContext(), "Removed from favourites", Toast.LENGTH_SHORT).show();
        } else {
            //add to favourites
            editor.putString(lineNumber,lineName);
            favbutton.setIcon(getResources().getDrawable(R.drawable.ic_favorites));
            Toast.makeText(getApplicationContext(), "Added to favourites", Toast.LENGTH_SHORT).show();
        }
        editor.commit();
    }

    private void changeDirection() {
        if (routeDirection == 0) routeDirection = 1;
        else routeDirection = 0;
        if(trenutniSmjer.equals(smjer1)) trenutniSmjer = smjer2;
        else trenutniSmjer = smjer1;
        loadData(false);
        directionName.setText("Smjer : " + trenutniSmjer);
        tripAdapter.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MvpLceRxPresenter<TripView, ArrayList<Trip>> createPresenter() {
        return new TripPresenter();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    @Override
    public void setData(ArrayList<Trip> data) {
        tripAdapter.setTrips(data);
        contentView.setRefreshing(false);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        int i = Integer.valueOf(lineNumber);
        Line l = new Line(i);
        l.setName(lineName);
        presenter.subscribe(ZetWebService.getInstance().
                getRouteSchedule(l, routeDirection), pullToRefresh);
    }

    @Override
    public void onRefresh() {
        loadData(true);
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
    public LceViewState<ArrayList<Trip>, TripView> createViewState() {
        setRetainInstance(true);
        return new SerializeableLceViewState<ArrayList<Trip>, TripView>();
    }

    @Override
    public ArrayList<Trip> getData() {
        return tripAdapter.getTrips();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_details_menu, menu);
        if (getSharedPreferences(FAVOURITES,Context.MODE_PRIVATE).contains(lineNumber)){
            menu.findItem(R.id.favorites_button).setIcon(R.drawable.ic_favorites);
        }
        return true;
    }

}