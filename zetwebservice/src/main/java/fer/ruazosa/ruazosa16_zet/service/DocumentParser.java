package fer.ruazosa.ruazosa16_zet.service;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fer.ruazosa.ruazosa16_zet.ZetWebService;
import fer.ruazosa.ruazosa16_zet.googlemapsmodel.PlacesService;
import fer.ruazosa.ruazosa16_zet.model.Line;
import fer.ruazosa.ruazosa16_zet.model.Station;
import fer.ruazosa.ruazosa16_zet.model.Trip;

public class DocumentParser {
    public static ArrayList<Line> parseRoutes(Document document) {
        ArrayList<Line> routes = new ArrayList<Line>();
        Elements tags = document.getElementsByAttributeValueContaining("href", "route_id=");
        for (Element e : tags) {
            String tagText = e.text();
            tagText = tagText.replaceAll("\\p{Pd}", "-");
            tagText = tagText.replaceAll("&" + "nbsp;", " ");
            tagText = tagText.replaceAll(String.valueOf((char) 160), " ");
            String[] route = tagText.split(" +", 2);

            Line l = new Line(Integer.parseInt(route[0]));
            l.setName(route[1]);
            routes.add(l);
        }
        return routes;
    }

    public static ArrayList<Trip> parseSchedule(Line l,Document document, int direction) {
        ArrayList<Trip> schedule = new ArrayList<>();
        Elements scheduleElements = document.getElementsByAttributeValueContaining("href", "direction_id=" + direction);
        for (Element e : scheduleElements) {
            // primjer elementa: tr><td><a href='...'>06:15:00</a></td><td>Kaptol</td><td>&nbsp;</td><td>Britanski trg</td></tr>
            Element tableRowElement = e.parent().parent(); //  tu je <tr>..</tr> uhvacen
            Elements tableRowElementData = tableRowElement.children(); // tu imamo sve <td>..</td> tagove unutar jednog tr taga

            String id = tableRowElementData.get(0).child(0).attr("href").split("trip_id=")[1].split("&")[0];
            Trip t = new Trip(l, id, direction);

            t.setDepartureTimeWithString(tableRowElementData.get(0).text());
            t.setStartingPoint(new Station(tableRowElementData.get(1).text()));
            t.setDestination(new Station(tableRowElementData.get(3).text()));

            schedule.add(t);
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

    public static ArrayList<Trip.StationTimePair> timesAtStation(Trip t, Document document) {
        ArrayList<Trip.StationTimePair> res = new ArrayList<>();
        Element pageContentDiv = document.getElementsByClass("pageContent").get(0);
        for(Element timeAtStation: pageContentDiv.getAllElements()){
            String[] data = timeAtStation.text().split(" - ");
            try {
                Date d = ZetWebService.DATE_FORMAT.parse(data[0]);
                Station s = new Station(data[1]);
                for(Station stat: t.getLine().getStations()){
                    if(stat.equals(s)){
                        //Replace station containing name and no coordinates with the station containing
                        //both name and coordinates.
                        s = stat;
                        break;
                    }
                }

                res.add(new Trip.StationTimePair(s, d));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    public static List<Station> parseStations(Document document){
        String coordinatesLink = document.getElementsByClass("leftMenu").get(0).children().get(1).attr("src").split("markers")[0];
        List<Station> res = new ArrayList<>();

        Matcher m = Pattern.compile("\\|(\\d+\\.\\d+),(\\d+\\.\\d+)").matcher(coordinatesLink);
        PlacesService places = PlacesService.getInstance();
        int num = 1;
        List<double[]> coordinates = new ArrayList<>();
        while(m.find()){
            String[] c = m.group().substring(1).split(",");
            //places.addStationWithCoordinates
            //        (Double.parseDouble(c[0]), Double.parseDouble(c[1]), res);
            coordinates.add(new double[]{Double.parseDouble(c[0]), Double.parseDouble(c[1])});
            //Station s = new Station((num++)+"");
            //s.setLatitude(Double.parseDouble(c[0]));
            //s.setLongitude(Double.parseDouble(c[1]));
        }
        /*
        Map<Integer, Station> stations = new HashMap<>();
        places.syncFindStation(coordinates, stations);
        for(int i=0; i<coordinates.size(); i++){
            res.add(stations.get(i));
        }
        */
        for (int i = 0; i < coordinates.size(); i++) {
            Station s = new Station("");
            s.setLatitude(coordinates.get(i)[0]);
            s.setLongitude(coordinates.get(i)[1]);
            res.add(s);
        }
        return res;
    }
}
