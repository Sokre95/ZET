package fer.ruazosa.ruazosa16_zet;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
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

    public Observable<List<Line>> getDailyTramRoutes() throws IOException {
        Observable<List<Line>> dailyTramRoutes = getRoutes(ZETService.TRAM_LINES_DAY_ID);
        return dailyTramRoutes;
    }

    public Observable<List<Line>> getNightlyTramRoutes() throws IOException {
        Observable<List<Line>> nightlyTramRoutes = getRoutes(ZETService.TRAM_LINES_NIGHT_ID);
        return nightlyTramRoutes;
    }

    public Observable<List<Line>> getDailyBusRoutes() throws IOException {
        Observable<List<Line>> dailyBusRoutes = getRoutes(ZETService.BUS_LINES_DAY_ID);
        return dailyBusRoutes;
    }

    public Observable<List<Line>> getNightlyBusRoutes() throws IOException {
        Observable<List<Line>> nightlyBusRoutes = getRoutes(ZETService.BUS_LINES_NIGHT_ID);
        return nightlyBusRoutes;
    }

    private Observable<List<Line>> getRoutes(final int routesType) {
        return Observable.defer(new Func0<Observable<List<Line>>>() {
            @Override
            public Observable<List<Line>> call() {
                Observable<Document> doc = service.getRoutes(routesType);
                Observable<List<Line>> lines = doc.map(new Func1<Document, List<Line>>() {
                    @Override
                    public List<Line> call(Document document) {
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
    public Observable<List<Trip>> getRouteSchedule(final int routeId, final int routeDirection) {
        return Observable.defer(new Func0<Observable<List<Trip>>>() {
            @Override
            public Observable<List<Trip>> call() {
                Observable<Document> doc = service.getRoutes(routeId);
                Observable<List<Trip>> trips = doc.map(new Func1<Document, List<Trip>>() {
                    @Override
                    public List<Trip> call(Document document) {
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
    public Observable<List<Trip>> getRouteScheduleByDate(final int routeId, final int routeDirection, final String date) throws IOException {
        return Observable.defer(new Func0<Observable<List<Trip>>>() {
            @Override
            public Observable<List<Trip>> call() {
                final Observable<Document> doc = service.getRouteScheduleForDate(routeId, date);
                Observable<List<Trip>> trips = doc.map(new Func1<Document, List<Trip>>() {
                    @Override
                    public List<Trip> call(Document document) {
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
    public Observable<List<String>> getTripStationsTimes(final int routeId, final int tripDirection, final String tripTime) throws IOException {
        Observable<List<String>> stationTimes = service.getRouteSchedule(routeId).map(new Func1<Document, List<String>>() {
            @Override
            public List<String> call(Document document) {
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

    public Observable<List<String>> getAvailableDatesForRouteSchedules(final int routeId) {
        Observable<List<String>> routeDates = service.getRouteSchedule(routeId).map(new Func1<Document, List<String>>() {
            @Override
            public List<String> call(Document document) {
                return DocumentParser.parseRouteScheduleDates(document);
            }
        });
        return routeDates;
    }
}
