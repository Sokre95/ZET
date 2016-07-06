package fer.ruazosa.ruazosa16_zet.service;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fer.ruazosa.ruazosa16_zet.ZetWebService;
import fer.ruazosa.ruazosa16_zet.model.Line;
import fer.ruazosa.ruazosa16_zet.model.Station;
import fer.ruazosa.ruazosa16_zet.model.Trip;

public class DocumentParser {
    public static ArrayList<Line> parseRoutes(Document document) {
        ArrayList<Line> routes = new ArrayList<Line>();
        Elements tags = document.getElementsByAttributeValueContaining("href", "route_id=");
        for (Element e : tags) {
            String tagText = e.text();
            tagText = tagText.replaceAll("&" + "nbsp;", " ");
            tagText = tagText.replaceAll(String.valueOf((char) 160), " ");
            String[] route = tagText.split(" +", 2);
            routes.add(new Line(Integer.parseInt(route[0]), route[1]));
        }
        return routes;
    }

    public static ArrayList<Trip> parseSchedule(Document document, int direction) {
        ArrayList<Trip> schedule = new ArrayList<>();
        Elements scheduleElements = document.getElementsByAttributeValueContaining("href", "direction_id=" + direction);
        for (Element e : scheduleElements) {
            // primjer elementa: tr><td><a href='...'>06:15:00</a></td><td>Kaptol</td><td>&nbsp;</td><td>Britanski trg</td></tr>
            Element tableRowElement = e.parent().parent(); //  tu je <tr>..</tr> uhvacen
            Elements tableRowElementData = tableRowElement.children(); // tu imamo sve <td>..</td> tagove unutar jednog tr taga
            String id = tableRowElementData.get(0).child(0).attr("href").split("&")[3].split("=")[1];
            try {
                schedule.add(new Trip(id,
                        ZetWebService.DATE_FORMAT.parse(tableRowElementData.get(0).text()),
                        new Station(tableRowElementData.get(1).text()),
                        new Station(tableRowElementData.get(3).text())));
            } catch (ParseException ex){
                ex.printStackTrace();
            }

            //schedule.add(new Trip(tableRowElementData.get(0).text(), tableRowElementData.get(1).text(), tableRowElementData.get(3).text()));
        }
        return schedule;
    }

    public static String getTripIdForScheduleAndTime(Document scheduleDocument, int direction, String time) {
        String tripId = null;
        Elements scheduleElements = scheduleDocument.getElementsByAttributeValueContaining("href", "direction_id=" + direction);
        for (Element e : scheduleElements) {
            if (e.text().contains(time)) {
                String url = e.attr("href");
                tripId = url.split("&")[2].split("=")[1];
                break;
            }
        }
        return tripId;
    }

    public static ArrayList<String> parseRouteWithStationTimes(Document document) {
        ArrayList<String> stationsTimes = new ArrayList<String>();
        Elements pageContent = document.getElementsByClass("pageContent");
        Element orderedList = pageContent.get(0);
        Elements listItems = orderedList.getElementsByTag("li");
        for (Element item : listItems) {
            stationsTimes.add(item.text());
        }
        return stationsTimes;
    }

    public static ArrayList<String> parseRouteScheduleDates(Document document) {
        ArrayList<String> dates = new ArrayList<String>();
        Elements pageContent = document.getElementsByClass("pageContent");
        Element pageContentDiv = pageContent.get(0);
        Elements datesOptionTags = pageContentDiv.getElementsByTag("option");
        String dateText = null;
        for (Element date : datesOptionTags) {
            if (date.text().contains(" ")) {
                dateText = date.text().split(" ")[0];
            } else {
                dateText = date.text();
            }
            dates.add(dateText);
        }
        return dates;
    }

}
