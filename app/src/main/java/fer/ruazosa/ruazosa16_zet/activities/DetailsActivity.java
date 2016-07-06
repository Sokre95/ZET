package fer.ruazosa.ruazosa16_zet.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceActivity;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateActivity;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.SerializeableLceViewState;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fer.ruazosa.ruazosa16_zet.LineView;
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

    private Spinner spinner;
    private String lineNumber;
    private int routeDirection = 0;
    //ListView lv;
    //SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy");
    //SimpleDateFormat argDf = new SimpleDateFormat("yyyyMMdd");

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

        getSupportActionBar().setTitle(lineNumber + " " + lineName);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tripAdapter = new TripAdapter(this);
        recyclerView.setAdapter(tripAdapter);
        contentView.setOnRefreshListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.map_button :
                Intent i = new Intent(this, MapActivity.class);
                startActivity(i);
                break;
            case R.id.favorites_button :
                break;
            case R.id.direction_button :
                changeDirection();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
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
        presenter.subscribe(ZetWebService.getInstance().
                getRouteSchedule(i, routeDirection), pullToRefresh);
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
        //ArrayList<String> dates = new ArrayList<String>();
        //Calendar c = Calendar.getInstance();
        //dates.add("Danas");
        //dates.add("Sutra");
        //c.add(Calendar.DAY_OF_YEAR,2);
        //for(int i =0; i<5;i++) {
            //String formattedDate = df.format(c.getTime());
            //dates.add(formattedDate);
            //c.add(Calendar.DAY_OF_YEAR,1);
        //}

        //String[] splitted = lineName.split("-");
        //ArrayList<String> routes = new ArrayList<>();
        //routes.add(splitted[0]);
        //routes.add(splitted[1]);

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.my_spinner_dropdown_item, routes);

        getMenuInflater().inflate(R.menu.activity_details_menu, menu);
        /*MenuItem item = menu.findItem(R.id.spinner);
        spinner = (Spinner) MenuItemCompat.getActionView(item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            Calendar c = Calendar.getInstance();
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //updateUI();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //updateUI();
            }
        });*/

        return true;
    }

    /*private void updateUI() {
        int index = spinner.getSelectedItemPosition();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR,index);
        String date = argDf.format(c.getTime());
        int route = Integer.parseInt(lineNumber);

//        TextView tableRow = (TextView) findViewById(R.id.polazak);
//        tableRow.setText(index+" " + date+ " " + route);

        List<String> schedule = new ArrayList<>();
        schedule.add("PRVdasdasdassI");
        schedule.add("DRUGI");

        //String[] array = new String[] {"PRVI", "DRUGI"};
        lv = (ListView) findViewById(R.id.my_list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, schedule
        );
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getRouteDetails(position);

            }
        });

    }/*

    /*private void getRouteDetails(int position) {
//        TextView tableRow = (TextView) findViewById(R.id.polazak);
//        tableRow.setText("Trazim detalje");
//
//
//        tableRow.setText((String)lv.getItemAtPosition(position));

        Intent i = new Intent(this, RouteDetailsActivity.class );
        i.putExtra("ROUTE_DETAILS", (String)lv.getItemAtPosition(position));
        startActivity(i);
    }*/
}