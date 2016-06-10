package fer.ruazosa.ruazosa16_zet.service;

import okhttp3.ResponseBody;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Scanner;

import retrofit2.Converter;

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
