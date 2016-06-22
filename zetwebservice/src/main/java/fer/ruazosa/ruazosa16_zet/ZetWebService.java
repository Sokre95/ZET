package fer.ruazosa.ruazosa16_zet;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import fer.ruazosa.ruazosa16_zet.model.Line;
import fer.ruazosa.ruazosa16_zet.model.Trip;
import fer.ruazosa.ruazosa16_zet.service.DocumentConverter;
import fer.ruazosa.ruazosa16_zet.service.DocumentParser;
import fer.ruazosa.ruazosa16_zet.service.ZETService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ZetWebService {

    private static ZetWebService instance;
    private ZETService service;
    private Retrofit retrofit;

    private ZetWebService() {
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        retrofit = new Retrofit.Builder()
                .baseUrl(ZETService.ENDPOINT)
                .addConverterFactory(new Converter.Factory() {
                    @Override
                    public Converter<ResponseBody, Document> responseBodyConverter
                            (Type type, Annotation[] annotations, Retrofit retrofit) {
                        return new DocumentConverter();
                    }
                })
                .addCallAdapterFactory(rxAdapter)
                .build();
        service = retrofit.create(ZETService.class);
    }

    public static synchronized ZetWebService getInstance() {
        if (instance == null) {
            instance = new ZetWebService();
        }
        return instance;
    }

    public Observable<ArrayList<Line>> getDailyTramRoutes() throws IOException {
        Observable<ArrayList<Line>> dailyTramRoutes = getRoutes(ZETService.TRAM_LINES_DAY_ID);
        return dailyTramRoutes;
    }

    public Observable<ArrayList<Line>> getNightlyTramRoutes() throws IOException {
        Observable<ArrayList<Line>> nightlyTramRoutes = getRoutes(ZETService.TRAM_LINES_NIGHT_ID);
        return nightlyTramRoutes;
    }

    public Observable<ArrayList<Line>> getDailyBusRoutes() throws IOException {
        Observable<ArrayList<Line>> dailyBusRoutes = getRoutes(ZETService.BUS_LINES_DAY_ID);
        return dailyBusRoutes;
    }

    public Observable<ArrayList<Line>> getNightlyBusRoutes() throws IOException {
        Observable<ArrayList<Line>> nightlyBusRoutes = getRoutes(ZETService.BUS_LINES_NIGHT_ID);
        return nightlyBusRoutes;
    }

    private Observable<ArrayList<Line>> getRoutes(final int routesType) {
        return Observable.defer(new Func0<Observable<ArrayList<Line>>>() {
            @Override
            public Observable<ArrayList<Line>> call() {
                Observable<Document> doc = service.getRoutes(routesType);
                Observable<ArrayList<Line>> lines = doc.map(new Func1<Document, ArrayList<Line>>() {
                    @Override
                    public ArrayList<Line> call(Document document) {
                        return DocumentParser.parseRoutes(document);
                    }
                });
                return lines;
            }
        });
    }

    /**
     * @param routeId        unique route id
     * @param routeDirection number which may be 0 or 1
     */
    public Observable<ArrayList<Trip>> getRouteSchedule(final int routeId, final int routeDirection) {
        return Observable.defer(new Func0<Observable<ArrayList<Trip>>>() {
            @Override
            public Observable<ArrayList<Trip>> call() {
                Observable<Document> doc = service.getRouteSchedule(routeId);
                Observable<ArrayList<Trip>> trips = doc.map(new Func1<Document, ArrayList<Trip>>() {
                    @Override
                    public ArrayList<Trip> call(Document document) {
                        return DocumentParser.parseSchedule(document, routeDirection);
                    }
                });
                return trips;
            }
        });
    }

    /**
     * @param routeId        unique route id
     * @param routeDirection number which may be 0 or 1
     * @param date           date String in yyyymmdd format
     */
    public Observable<ArrayList<Trip>> getRouteScheduleByDate(final int routeId, final int routeDirection, final String date) throws IOException {
        return Observable.defer(new Func0<Observable<ArrayList<Trip>>>() {
            @Override
            public Observable<ArrayList<Trip>> call() {
                final Observable<Document> doc = service.getRouteScheduleForDate(routeId, date);
                Observable<ArrayList<Trip>> trips = doc.map(new Func1<Document, ArrayList<Trip>>() {
                    @Override
                    public ArrayList<Trip> call(Document document) {
                        return DocumentParser.parseSchedule(document, routeDirection);
                    }
                });
                return trips;
            }
        });
    }

    /**
     * @param routeId       unique route id
     * @param tripDirection number which may be 0 or 1
     * @param tripTime      time string in hh:mm:ss format
     */
    public Observable<ArrayList<String>> getTripStationsTimes(final int routeId, final int tripDirection, final String tripTime) throws IOException {
        Observable<ArrayList<String>> stationTimes = service.getRouteSchedule(routeId).map(new Func1<Document, ArrayList<String>>() {
            @Override
            public ArrayList<String> call(Document document) {
                String tripId = DocumentParser.getTripIdForScheduleAndTime(document, tripDirection, tripTime);
                if (tripId == null) {
                    return null;
                }
                Call<Document> tripCall = service.getTrip(routeId, tripId, tripDirection);
                try {
                    Document tripDoc = tripCall.execute().body();
                    return DocumentParser.parseRouteWithStationTimes(tripDoc);
                } catch (IOException e) {
                    return null;
                }
            }
        });
        return stationTimes;
    }

    public Observable<ArrayList<String>> getAvailableDatesForRouteSchedules(final int routeId) {
        Observable<ArrayList<String>> routeDates = service.getRouteSchedule(routeId).map(new Func1<Document, ArrayList<String>>() {
            @Override
            public ArrayList<String> call(Document document) {
                return DocumentParser.parseRouteScheduleDates(document);
            }
        });
        return routeDates;
    }
}
