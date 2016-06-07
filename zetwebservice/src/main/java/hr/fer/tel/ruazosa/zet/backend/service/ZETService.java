package hr.fer.tel.ruazosa.zet.backend.service;

import org.jsoup.nodes.Document;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zlatan on 6/7/16.
 */
public interface ZETService {
    public static final int TRAM_LINES_DAY_ID = 291;
    public static final int TRAM_LINES_NIGHT_ID = 292;
    public static final int BUS_LINES_DAY_ID = 294;
    public static final int BUS_LINES_NIGHT_ID = 295;

    public static final int BASE_LINEVIEW_ID = 330;
    public static final int BASE_TRIPVIEW_ID = 331;

    public static final String ENDPOINT = "http://www.zet.hr/";

    @GET("/default.aspx")
    Call<Document> getRoutes(@Query("id") int id);

    @GET("/default.aspx?id="+BASE_LINEVIEW_ID)
    Call<Document> getRouteDefault(@Query("route_id") int routeId);

    @GET("/default.aspx?id="+BASE_LINEVIEW_ID)
    Call<Document> getRouteForDate(@Query("route_id") int routeId, @Query("datum") String date);

    @GET("/default.aspx?id="+BASE_TRIPVIEW_ID)
    Call<Document> getTrip(@Query("route_id") int routeId, @Query("trip_id") String trip_id, @Query("direction") int direction);
}
