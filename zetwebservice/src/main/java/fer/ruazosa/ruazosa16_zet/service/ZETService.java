package fer.ruazosa.ruazosa16_zet.service;

import org.jsoup.nodes.Document;

import javax.print.Doc;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

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
    void getRoutes(@Query("id") int id, Callback<Document> document);

    @GET("/default.aspx?id="+BASE_LINEVIEW_ID)
    void getRouteDefault(@Query("route_id") int routeId, Callback<Document> document);

    @GET("/default.aspx?id="+BASE_LINEVIEW_ID)
    void getRouteForDate(@Query("route_id") int routeId, @Query("datum") String date,
                         Callback<Document> document);

    @GET("/default.aspx?id="+BASE_TRIPVIEW_ID)
    void getTrip(@Query("route_id") int routeId, @Query("trip_id") String trip_id, @Query("direction") int direction,
                 Callback<Document> document);
}
