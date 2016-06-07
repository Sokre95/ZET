package hr.fer.tel.ruazosa.zet.backend;

import org.jsoup.nodes.Document;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import hr.fer.tel.ruazosa.zet.backend.service.DocumentConverter;
import hr.fer.tel.ruazosa.zet.backend.service.ZETService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
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
        call.enqueue(new Callback<Document>() {
            @Override
            public void onResponse(Call<Document> call, Response<Document> response) {
                System.out.println(response.body().html());
            }

            @Override
            public void onFailure(Call<Document> call, Throwable t) {

            }
        });
    }
}
