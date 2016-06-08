package fer.ruazosa.ruazosa16_zet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

import fer.ruazosa.ruazosa16_zet.service.DocumentConverter;
import fer.ruazosa.ruazosa16_zet.service.DocumentParser;
import fer.ruazosa.ruazosa16_zet.service.ZETService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class Main {
    public static void main(String[]args){

        Retrofit r = new Retrofit.Builder()
                .baseUrl(ZETService.ENDPOINT)
                .addConverterFactory(new Converter.Factory() {
                    @Override
                    public Converter<ResponseBody, Document> responseBodyConverter
                            (Type type, Annotation[] annotations, Retrofit retrofit) {
                        return new DocumentConverter();
                    }
                })
                .build();

        /*************************/
        /**** GET ZET ROUTES *****/
        /*************************/

        /**** BUS DAILY ROUTES ****/
        System.out.println("Dnevne linije autobusa");
        System.out.println("----------------------");

        ZETService service = r.create(ZETService.class);
        Call<Document> call = service.getRoutes(ZETService.BUS_LINES_DAY_ID);
        try {
            String document = call.execute().body().html();
            List<String> routes = DocumentParser.parseRoutes(document);
            for(String s : routes) System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**** BUS NIGHT ROUTES ****/
        System.out.println("Nocne linije autobusa");
        System.out.println("----------------------");

        call = service.getRoutes(ZETService.BUS_LINES_NIGHT_ID);
        try {
            String document = call.execute().body().html();
            List<String> routes = DocumentParser.parseRoutes(document);
            for(String s : routes) System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**** TRAM DAILY ROUTES ****/
        System.out.println("Dnevne linije tramvaja");
        System.out.println("----------------------");

        call = service.getRoutes(ZETService.TRAM_LINES_DAY_ID);
        try {
            String document = call.execute().body().html();
            List<String> routes = DocumentParser.parseRoutes(document);
            for(String s : routes) System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**** TRAM NIGHT ROUTES ****/
        System.out.println("Nocne linije tramvaja");
        System.out.println("----------------------");

        call = service.getRoutes(ZETService.TRAM_LINES_NIGHT_ID);
        try {
            String document = call.execute().body().html();
            List<String> routes = DocumentParser.parseRoutes(document);
            for(String s : routes) System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

        call = service.getRouteWithDirection(2, 0);
        try {
            String document = call.execute().body().html();
            List<String> schedule = DocumentParser.parseSchedule(document, 0);
            for(String s : schedule) System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
