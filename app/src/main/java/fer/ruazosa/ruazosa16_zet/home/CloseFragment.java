package fer.ruazosa.ruazosa16_zet.home;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

    private SupportMapFragment supportMapFragment;
    private LocationManager locationManager;
    private boolean cameraPositioned = false;

    private final long MIN_TIME_BETWEEN_UPDATES = 10000;
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
        supportMapFragment = ((SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.close_map));
        supportMapFragment.getMapAsync(this);
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
    }

    @Override
    public void onResume() {
        super.onResume();
        if (closeMap != null) {
            updateMap(null);
        }
    }

    private void noGpsMessage() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Za pregled najbližih stanica potrebna je usluga lokacije!")
                .setCancelable(false)
                .setPositiveButton("Uključi lokaciju", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("Nastavi bez lokacije", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                        Toast.makeText(getContext(), "Nije moguće prikazati najbliže stanice bez usluge lokacije", Toast.LENGTH_LONG).show();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onLocationChanged(Location location) {
        if (closeMap != null) {
            updateMap(location);
        }
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
        closeMap = googleMap;
        closeMap.setMyLocationEnabled(true);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            noGpsMessage();
        }
        else{
            updateMap(null);
        }
    }

    private void updateMap(Location location) {
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            noGpsMessage();
        } else {
            if (location == null) {
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
            }
            if (location != null) {
                LatLng user_position = new LatLng(location.getLatitude(), location.getLongitude());
                if (cameraPositioned == false) {
                    closeMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user_position, 12));
                    cameraPositioned = true;
                }

                PlacesService.getInstance().findNearestStations(user_position.latitude, user_position.longitude).subscribe(new Action1<List<Station>>() {
                    @Override
                    public void call(final List<Station> stations) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                for (Station station : stations) {
                                    LatLng station_postion = new LatLng(station.getLatitude(), station.getLongitude());
                                    MarkerOptions marker = new MarkerOptions().position(station_postion).title(station.getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus));
                                    closeMap.addMarker(marker);
                                    //cdLog.d("marker", marker.getTitle());
                                }
                            }
                        });
                    }
                });
            }
        }
    }
}
