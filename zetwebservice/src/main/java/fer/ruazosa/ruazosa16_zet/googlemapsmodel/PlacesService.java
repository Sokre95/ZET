package fer.ruazosa.ruazosa16_zet.googlemapsmodel;

import org.jsoup.nodes.Document;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fer.ruazosa.ruazosa16_zet.model.Station;
import fer.ruazosa.ruazosa16_zet.service.DocumentConverter;
import fer.ruazosa.ruazosa16_zet.service.ZETService;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
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
                try {
                    Result result = location.getResults().get(0);
                    Station s = new Station(result.getName());
                    s.setLatitude(result.getGeometry().getLocation().getLat());
                    s.setLongitude(result.getGeometry().getLocation().getLng());
                    set.add(s);
                } catch(IndexOutOfBoundsException ex){}
            }
        });
    }

    private Func1<Location, List<Station>> locationToStationMapper(){
        return new Func1<Location, List<Station>>() {
            @Override
            public List<Station> call(Location location) {
                List<Station> list = new ArrayList<>();
                for(Result r: location.getResults()){
                    Station s = new Station(r.getName());
                    s.setLatitude(r.getGeometry().getLocation().getLat());
                    s.setLongitude(r.getGeometry().getLocation().getLng());
                    list.add(s);
                }
                return list;
            }
        };
    }

    public Observable<List<Station>> findNearestStations(double lat, double lon){
        Observable<List<Station>> observable = service.reverseGeocodeCoordinates(lat+","+lon).map(locationToStationMapper());
        return observable;
    }

    public Observable<List<Station>> findNearestBusStations(double lat, double lon){
        Observable<List<Station>> obs = service.findNearestBusStations(lat+","+lon).map(locationToStationMapper());
        return obs;
    }

    public Observable<List<Station>> findNearestLightRailStations(double lat, double lon){
        Observable<List<Station>> obs = service.findNearestLightRailStations(lat+","+lon).map(locationToStationMapper());
        return obs;
    }
}
