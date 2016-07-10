package fer.ruazosa.ruazosa16_zet.googlemapsmodel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zlatan on 7/9/16.
 */
public interface PlacesInterface {
    public static final String endpoint = "https://maps.googleapis.com/maps/api/place/nearbysearch/";

    public static final String MAPS_KEY = "AIzaSyDuFGBmHyGQ5kyDC7xdTcHQlOTBmRvB0mU";
            // "AIzaSyCkpTfBc2wgayv__NnrnLNzYawTfX65ov0";

    @GET("json?rankby=distance&type=transit_station&key="+MAPS_KEY)
    Observable<Location> reverseGeocodeCoordinates(@Query("location") String latLon);

    @GET("json?type=bus_station&key="+MAPS_KEY)
    Observable<Location> findNearestBusStations(@Query("location") String userLocation);

    @GET("json?type=light_rail_station&key="+MAPS_KEY)
    Observable<Location> findNearestLightRailStations(@Query("location") String userLocation);

    @GET("json?type=transit_station&rankby=distance&key="+MAPS_KEY)
    Call<Location> nearestTransitStation(@Query("location") String location);
}
