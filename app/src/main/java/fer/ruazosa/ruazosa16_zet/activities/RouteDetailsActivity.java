package fer.ruazosa.ruazosa16_zet.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fer.ruazosa.ruazosa16_zet.R;

public class RouteDetailsActivity extends AppCompatActivity {

    String routeDetails;

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_details);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        routeDetails = i.getStringExtra("ROUTE_DETAILS");

        getSupportActionBar().setTitle(routeDetails);
    }

    private void updateUI(){
//
//        Calendar c = Calendar.getInstance();
//        c.add(Calendar.DAY_OF_YEAR,index);
//        String date = argDf.format(c.getTime());
//        int route = Integer.parseInt(lineNumber);


        List<String> schedule = new ArrayList<>();
        schedule.add("PRVdasdasdassI");
        schedule.add("DRUGI");
        schedule.add(routeDetails);

        ListView lv = (ListView) findViewById(R.id.my_list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, schedule
        );
        lv.setAdapter(adapter);
    }
}
