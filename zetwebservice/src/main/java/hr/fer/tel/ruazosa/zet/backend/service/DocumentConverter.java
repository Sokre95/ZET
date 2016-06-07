package hr.fer.tel.ruazosa.zet.backend.service;

import okhttp3.ResponseBody;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Scanner;

import retrofit2.Converter;

/**
 * Created by zlatan on 6/7/16.
 */
public class DocumentConverter implements Converter<ResponseBody, Document> {

    @Override
    public Document convert(ResponseBody value) throws IOException {
        Scanner scanner = null;
        try {
            scanner = new Scanner( value.byteStream() ).useDelimiter( "\\A" );

            String html = scanner.hasNext() ? scanner.next() : "";
            value.byteStream().close();

            return Jsoup.parse(html);
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        throw new RuntimeException("Failed to get data.");
    }
}
