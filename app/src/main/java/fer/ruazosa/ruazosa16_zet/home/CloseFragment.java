package fer.ruazosa.ruazosa16_zet.home;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import fer.ruazosa.ruazosa16_zet.R;
import fer.ruazosa.ruazosa16_zet.googlemapsmodel.PlacesService;
import fer.ruazosa.ruazosa16_zet.model.Station;
import rx.functions.Action1;


public class CloseFragment extends Fragment implements LocationListener, OnMapReadyCallback {

    GoogleMap closeMap;

    private LocationManager locationManager;
    private Location location;

    private final long MIN_TIME_BETWEEN_UPDATES = 0;
    private final float MIN_DISTANCE_BETWEEN_UPDATES = 10;

    public CloseFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_close, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.close_map)).getMapAsync(this);
        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BETWEEN_UPDATES, MIN_DISTANCE_BETWEEN_UPDATES, this);
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        closeMap = googleMap;


        //rusi se nes u backendu
        /*
        ZetWebService.getInstance().getNearestStationsWithLines(location.getLatitude(),location.getLongitude()).subscribe(new Action1<Map<Station, List<Line>>>() {
            @Override
            public void call(final Map<Station, List<Line>> stationListMap) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (Station station: stationListMap.keySet()) {
                            LatLng station_postion = new LatLng(station.getLatitude(),station.getLongitude());
                            googleMap.addMarker(new MarkerOptions().position(station_postion).title(station.getName()));
                        }
                    }
                });
            }
        });
        */

        LatLng user_position = new LatLng(location.getLatitude(), location.getLongitude());
        googleMap.addMarker(new MarkerOptions().position(user_position).title("Tvoja pozicija").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user_position, 12.6f));

        PlacesService.getInstance().findNearestStations(location.getLatitude(), location.getLongitude()).subscribe(new Action1<List<Station>>() {
            @Override
            public void call(final List<Station> stations) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (Station station : stations) {
                            LatLng station_postion = new LatLng(station.getLatitude(), station.getLongitude());
                            googleMap.addMarker(new MarkerOptions().position(station_postion).title(station.getName()));
                        }
                    }
                });
            }
        });
    }
}

