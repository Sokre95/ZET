package fer.ruazosa.ruazosa16_zet.googlemapsmodel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zlatan on 7/9/16.
 */
public interface PlacesInterface {
    public static final String endpoint = "https://maps.googleapis.com/maps/api/place/nearbysearch/";

    @GET("json?key=AIzaSyBAC1J2zOfyanzlNa1bXXCPL8Y36mYymYE&radius=10&type=transit_station")
    Observable<Location> reverseGeocodeCoordinates(@Query("location") String latLon);
}
