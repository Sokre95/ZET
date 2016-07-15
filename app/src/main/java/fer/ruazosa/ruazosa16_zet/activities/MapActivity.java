package fer.ruazosa.ruazosa16_zet.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fer.ruazosa.ruazosa16_zet.R;
import fer.ruazosa.ruazosa16_zet.ZetWebService;
import fer.ruazosa.ruazosa16_zet.model.Line;
import fer.ruazosa.ruazosa16_zet.model.Station;
import rx.functions.Action1;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private GoogleMap mMap;
    private Line line;
    private ZetWebService zetWebService;
    private int lineNumberInt;
    private String lineNumberString;
    private List<Station> stationList = new ArrayList<>();
    private MapActivity context;
    private final LatLng ZAGREB = new LatLng(45.815399, 15.966568);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        ButterKnife.bind(this);

        Intent i = getIntent();
        context = this;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            lineNumberString = extras.getString("LINE");
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(lineNumberString);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        lineNumberInt = Integer.parseInt(lineNumberString);
        line = new Line(lineNumberInt);

        zetWebService = zetWebService.getInstance();

        zetWebService.loadLine(line).subscribe(new Action1<Line>() {
            @Override

            public void call(Line line) {
                stationList = line.getStations();
                runOnUiThread(new Runnable() {
                    public void run() {
                        if (stationList.size() == 0) {
                            //Toast.makeText(context, "Nesposobni ZET, nisu sposobni ni pružat podatke za sve linije jednako, prestrašno!", Toast.LENGTH_LONG).show();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            context.finish();
                        } else {
                            putRouteOnMap();
                        }
                    }
                });
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ZAGREB, 10));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        //Toast.makeText(this,"DOHVAĆANJE PODATAKA...potrebno vrijeme za dohvat podataka može biti i više sekundi ovisno o Vašoj Internet vezi",Toast.LENGTH_LONG).show();

    }

    public void putRouteOnMap() {

        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.width(5).color(Color.BLUE);
        for(Station station : stationList){
            LatLng stationLatLng = new LatLng(station.getLatitude(),station.getLongitude());
            mMap.addMarker(new MarkerOptions().position(stationLatLng).title(station.getName()));
            polylineOptions.add(stationLatLng);
        }
        mMap.addPolyline(polylineOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(stationList.get(stationList.size()/2).getLatitude(),stationList.get(stationList.size()/2).getLongitude()),12));
    }

}
