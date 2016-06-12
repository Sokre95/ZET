package fer.ruazosa.ruazosa16_zet.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fer.ruazosa.ruazosa16_zet.R;

public class DetailsActivity extends AppCompatActivity {


    Spinner spinner;
    String lineNumber;
    ListView lv;
    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy");
    SimpleDateFormat argDf = new SimpleDateFormat("yyyyMMdd");


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        lineNumber = i.getStringExtra("LINE_NUMBER");

        getSupportActionBar().setTitle(lineNumber);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        ArrayList<String> dates = new ArrayList<String>();
        Calendar c = Calendar.getInstance();
        dates.add("Danas");
        dates.add("Sutra");
        c.add(Calendar.DAY_OF_YEAR,2);
        for(int i =0; i<5;i++) {
            String formattedDate = df.format(c.getTime());
            dates.add(formattedDate);
            c.add(Calendar.DAY_OF_YEAR,1);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.my_spinner_dropdown_item, dates );

        getMenuInflater().inflate(R.menu.activity_details_menu, menu);
        MenuItem item = menu.findItem(R.id.spinner);
        spinner = (Spinner) MenuItemCompat.getActionView(item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            Calendar c = Calendar.getInstance();
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                updateUI();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                updateUI();
            }
        });

        return true;
    }

    private void updateUI() {
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

    }

    private void getRouteDetails(int position) {
//        TextView tableRow = (TextView) findViewById(R.id.polazak);
//        tableRow.setText("Trazim detalje");
//
//
//        tableRow.setText((String)lv.getItemAtPosition(position));

        Intent i = new Intent(this, RouteDetailsActivity.class );
        i.putExtra("ROUTE_DETAILS", (String)lv.getItemAtPosition(position));
        startActivity(i);
    }
}