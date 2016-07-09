package fer.ruazosa.ruazosa16_zet.googlemapsmodel;

import org.jsoup.nodes.Document;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import fer.ruazosa.ruazosa16_zet.model.Station;
import fer.ruazosa.ruazosa16_zet.service.DocumentConverter;
import fer.ruazosa.ruazosa16_zet.service.ZETService;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by zlatan on 7/9/16.
 */
public class PlacesService {

    private static PlacesService instance;

    public static PlacesService getInstance(){
        if(instance == null){
            instance = new PlacesService();
        }
        return instance;
    }

    private PlacesInterface service;

    private PlacesService(){
        RxJavaCallAdapterFactory adapter =RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

        Retrofit r = new Retrofit.Builder()
                .baseUrl(PlacesInterface.endpoint)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(adapter)
                .build();
        service = r.create(PlacesInterface.class);
    }

    public void addStationWithCoordinates(double lat, double lon, final Set<Station> set){
        service.reverseGeocodeCoordinates(lat+","+lon).subscribe(new Action1<Location>() {
            @Override
            public void call(Location location) {
                Result result = location.getResults().get(0);
                Station s = new Station(result.getName());
                s.setLatitude(result.getGeometry().getLocation().getLat());
                s.setLongitude(result.getGeometry().getLocation().getLng());
                set.add(s);
            }
        });
    }
}
