package fer.ruazosa.ruazosa16_zet;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

import fer.ruazosa.ruazosa16_zet.service.DocumentConverter;
import fer.ruazosa.ruazosa16_zet.service.DocumentParser;
import fer.ruazosa.ruazosa16_zet.service.ZETService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import rx.Observable;

public class ZetWebService {

    private static ZetWebService instance;
    private ZETService service;
    private Retrofit retrofit;

    private ZetWebService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ZETService.ENDPOINT)
                .addConverterFactory(new Converter.Factory() {
                    @Override
                    public Converter<ResponseBody, Document> responseBodyConverter
                            (Type type, Annotation[] annotations, Retrofit retrofit) {
                        return new DocumentConverter();
                    }
                })
                .build();
        service = retrofit.create(ZETService.class);
    }

    public static synchronized ZetWebService getInstance() {
        if (instance == null) {
            instance = new ZetWebService();
        }
        return instance;
    }

    public Observable<List<String>> getDailyTramRoutes() throws IOException {
        //291 is id for daily tram lines
        Observable<List<String>> dailyTramRoutes = getRoutes(ZETService.TRAM_LINES_DAY_ID);
        return dailyTramRoutes;
    }

    public Observable<List<String>> getNightlyTramRoutes() throws IOException {
        Observable<List<String>> nightlyTramRoutes = getRoutes(ZETService.TRAM_LINES_NIGHT_ID);
        return nightlyTramRoutes;
    }

    public Observable<List<String>> getDailyBusRoutes() throws IOException {
        Observable<List<String>> dailyBusRoutes = getRoutes(ZETService.BUS_LINES_DAY_ID);
        return dailyBusRoutes;
    }

    public Observable<List<String>> getNightlyBusRoutes() throws IOException {
        Observable<List<String>> nightlyBusRoutes = getRoutes(ZETService.BUS_LINES_NIGHT_ID);
        return nightlyBusRoutes;
    }

    private Observable<List<String>> getRoutes(int routesType) throws IOException {
        Call<Document> call = service.getRoutes(routesType);
        Document doc = call.execute().body();
        List<String> routes = DocumentParser.parseRoutes(doc);
        Observable<List<String>> observable = Observable.just(routes);
        return observable;
    }

}
