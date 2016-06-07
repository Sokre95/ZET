package fer.ruazosa.ruazosa16_zet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Scanner;

import fer.ruazosa.ruazosa16_zet.service.DocumentConverter;
import fer.ruazosa.ruazosa16_zet.service.ZETService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by zlatan on 6/7/16.
 */
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

        ZETService service = r.create(ZETService.class);
        Call<Document> call = service.getRoutes(ZETService.TRAM_LINES_DAY_ID);
        try {
            System.out.println(call.execute().body().html());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
