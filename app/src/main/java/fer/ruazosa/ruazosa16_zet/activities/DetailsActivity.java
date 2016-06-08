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
import android.widget.Spinner;
import android.widget.Toast;

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
    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy");

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        String lineNumber = i.getStringExtra("LINE_NUMBER");

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
                c.add(Calendar.DAY_OF_YEAR,position);
                String formattedDate = df.format(c.getTime());
                Toast.makeText(getBaseContext(), (CharSequence) parent.getItemAtPosition(position)+" selected", Toast.LENGTH_LONG);
                //   updateUI(formattedDate);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                String formattedDate = df.format(c.getTime());
                Toast.makeText(getBaseContext(), (CharSequence) parent.getItemAtPosition(0)+ " selected", Toast.LENGTH_LONG);
                //  updateUI(formattedDate);
            }
        });

        return true;
    }
}