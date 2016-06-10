package fer.ruazosa.ruazosa16_zet.service;

import org.jsoup.nodes.Document;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

public interface ZETService {
    public static final int TRAM_LINES_DAY_ID = 291;
    public static final int TRAM_LINES_NIGHT_ID = 292;
    public static final int BUS_LINES_DAY_ID = 294;
    public static final int BUS_LINES_NIGHT_ID = 295;

    public static final int BASE_LINEVIEW_ID = 330;
    public static final int BASE_TRIPVIEW_ID = 331;

    public static final String ENDPOINT = "http://www.zet.hr/";

    @GET("/default.aspx")
    Observable<Document> getRoutes(@Query("id") int id);

    @GET("/default.aspx?id="+BASE_LINEVIEW_ID)
    Observable<Document> getRouteDefault(@Query("route_id") int routeId);

    @GET("/default.aspx?id="+BASE_LINEVIEW_ID)
    Observable<Document> getRouteForDateWithDirection(@Query("route_id") int routeId, @Query("datum") String date,
                                                @Query("pravac") int direction);

    @GET("/default.aspx?id="+BASE_TRIPVIEW_ID)
    Observable<Document> getTrip(@Query("route_id") int routeId, @Query("trip_id") String trip_id, @Query("direction") int direction);

    @GET("/default.aspx?id="+BASE_LINEVIEW_ID)
    Observable<Document> getRouteWithDirection(@Query("route_id") int routeId, @Query("pravac") int direction);

    @GET
    Observable<Document> getSpecificTimeRoute(@Url String url);
}
