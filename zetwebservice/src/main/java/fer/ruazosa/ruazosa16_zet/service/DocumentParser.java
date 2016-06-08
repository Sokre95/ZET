package fer.ruazosa.ruazosa16_zet.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class DocumentParser {

    public static List<String> parseRoutes(String document) {
        List<String> routes = new ArrayList<String>();
        Document document1 = Jsoup.parse(document);
        Elements tags = document1.getElementsByAttributeValueContaining("href", "route_id=");
        for(Element e : tags) routes.add(e.text());
        return routes;
    }

    public static List<String> parseSchedule(String document, int direction) {
        List<String> schedule = new ArrayList<>();
        Document document1 = Jsoup.parse(document);
        Elements scheduleElements = document1.getElementsByAttributeValueContaining("href", "direction_id=" + direction);
        for (Element e : scheduleElements) {
            Element rowElement = e.parent().parent();
            schedule.add(rowElement.text());
        }
        return schedule;
    }

    public static List<String> parseRouteWithStationTimes(String document, int route, int direction, String time) {
        List<String> stationsTimes = new ArrayList<String>();
        Document document1 = Jsoup.parse(document);
        Elements scheduleElements = document1.getElementsByAttributeValueContaining("href", "direction_id=" + direction);
    }

}
