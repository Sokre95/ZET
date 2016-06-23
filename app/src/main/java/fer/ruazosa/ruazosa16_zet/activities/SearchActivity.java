package fer.ruazosa.ruazosa16_zet.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import fer.ruazosa.ruazosa16_zet.R;
import fer.ruazosa.ruazosa16_zet.adapters.RouteAdapter;
import fer.ruazosa.ruazosa16_zet.model.Line;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.list)
    RecyclerView recyclerView;
    @BindView(R.id.errorView)
    TextView errorView;

    private ArrayList<Line> data = null;
    private String query = null;
    private ArrayList<Line> queryResult = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        handleEvent(getIntent());
    }

    private void handleEvent(Intent searchIntent) {
        if(searchIntent.ACTION_SEARCH == searchIntent.getAction()) {
            Bundle bundle = searchIntent.getBundleExtra("Bundle");
            query = bundle.getString("QUERY").toUpperCase();
            data = (ArrayList<Line>) bundle.getSerializable("DATA");
            Toast.makeText(getApplicationContext(), query, Toast.LENGTH_LONG).show();
            findElements();
            displayData();
        }
    }

    private void findElements() {
        for(Line e : data) {
            if(e.toString().contains(query)) {
                queryResult.add(e);
            }
        }
    }

    private void displayData() {
        if(queryResult.size() != 0) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            RouteAdapter routeAdapter = new RouteAdapter(queryResult, this);
            recyclerView.setAdapter(routeAdapter);
        } else {
            errorView.setText("No data for given query!");
        }
    }

}
