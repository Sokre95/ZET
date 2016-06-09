package fer.ruazosa.ruazosa16_zet.service;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import fer.ruazosa.ruazosa16_zet.model.Line;

public class DocumentParser {

    public static List<Line> parseRoutes(Document document) {
        List<Line> routes = new ArrayList<Line>();
        Elements tags = document.getElementsByAttributeValueContaining("href", "route_id=");
        for(Element e : tags) {
            String tagText = e.text();
            tagText = tagText.replaceAll("&"+"nbsp;", " ");
            tagText = tagText.replaceAll(String.valueOf((char) 160), " ");
            String[] route = tagText.split(" +", 2);
            routes.add(new Line(route[0], route[1]));
        }
        return routes;
    }

    public static List<String> parseSchedule(Document document, int direction) {
        List<String> schedule = new ArrayList<>();
        Elements scheduleElements = document.getElementsByAttributeValueContaining("href", "direction_id=" + direction);
        for (Element e : scheduleElements) {
            Element rowElement = e.parent().parent();
            schedule.add(rowElement.text());
        }
        return schedule;
    }

    public static String getUrlForScheduleTime(Document document, int direction, String time) {
        String url = "";
        Elements scheduleElements = document.getElementsByAttributeValueContaining("href", "direction_id=" + direction);
        for(Element e : scheduleElements) {
            if(e.text().contains(time)) {
                url = e.attr("href");
                break;
            }
        }
        return url;
    }

    public static List<String> parseRouteWithStationTimes(Document document) {
        List<String> stationsTimes = new ArrayList<String>();
        Elements pageContent = document.getElementsByClass("pageContent");
        Element orderedList = pageContent.get(0);
        Elements listItems = orderedList.getElementsByTag("li");
        for (Element item : listItems) {
            stationsTimes.add(item.text());
        }
        return stationsTimes;
    }

}
