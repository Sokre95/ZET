package fer.ruazosa.ruazosa16_zet.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Scanner;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;
import retrofit.mime.TypedString;

/**
 * Created by zlatan on 6/7/16.
 */
public class DocumentConverter implements Converter {
    @Override
    public Object fromBody(TypedInput body, Type type) throws ConversionException {
        Scanner scanner = null;
        try {
            scanner = new Scanner( body.in() ).useDelimiter( "\\A" );

            String html = scanner.hasNext() ? scanner.next() : "";
            body.in().close();

            //return html;
            return Jsoup.parse(html);
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        throw new RuntimeException("Failed to get data.");
    }

    @Override
    public TypedOutput toBody(Object object) {
        return new TypedString(String.valueOf(object));
    }
}
