package fer.ruazosa.ruazosa16_zet;

import org.jsoup.nodes.Document;

import fer.ruazosa.ruazosa16_zet.service.DocumentConverter;
import fer.ruazosa.ruazosa16_zet.service.ZETService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by zlatan on 6/7/16.
 */
public class Main {
    public static void main(String[]args){
        RestAdapter ra = new RestAdapter.Builder()
                .setConverter(new DocumentConverter())
                .setEndpoint(ZETService.ENDPOINT)
                .build();
        ZETService service = ra.create(ZETService.class);
        service.getTrip(6, "0_11_613_6_10149", 0, new Callback<Document>() {
            @Override
            public void success(Document document, Response response) {
                System.out.println(document.html());
            }
            @Override
            public void failure(RetrofitError error) {
                System.out.println(error.getMessage());
                System.out.println(error.getBody());
            }
        });
    }
}
