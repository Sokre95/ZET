package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ZetData {
    private String baseZetUrl;

    public ZetData(String baseZetUrl) {
        this.baseZetUrl = baseZetUrl;
    }

    public List<String> getDailyTramRoutes() throws IOException {
        //291 is id for daily tram lines
        List<String> dailyTramRoutes = getRoutes(291);
        return dailyTramRoutes;
    }

    public List<String> getNightlyTramRoutes() throws IOException {
        List<String> nightlyTramRoutes = getRoutes(292);
        return nightlyTramRoutes;
    }

    public List<String> getDailyBusRoutes() throws IOException {
        List<String> dailyBusRoutes = getRoutes(294);
        return dailyBusRoutes;
    }

    public List<String> getNightlyBusRoutes() throws IOException {
        List<String> nightlyBusRoutes = getRoutes(295);
        return nightlyBusRoutes;
    }

    private List<String> getRoutes(int routesType) throws IOException {
        List<String> routes = new ArrayList<String>();
        Document document = Jsoup.connect(baseZetUrl + "default.aspx?id=" + routesType).get();
        Elements tags = document.getElementsByAttributeValueContaining("href", "route_id=");
        for (Element line : tags) {
            routes.add(line.text());
        }
        return routes;
    }

    public List<String> getSchedule(int route, int direction) throws IOException {
        List<String> schedule = new ArrayList<String>();
        Document document = Jsoup.connect(baseZetUrl + "default.aspx?id=330&route_id=" + route).get();
        Elements scheduleElements = document.getElementsByAttributeValueContaining("href", "direction_id=" + direction);
        for (Element e : scheduleElements) {
            Element rowElement = e.parent().parent();
            schedule.add(rowElement.text());
        }
        return schedule;
    }

    public List<String> getRouteStationsTimes(int route, int direction, String time) throws IOException {
        List<String> stationsTimes = new ArrayList<String>();
        Document document = Jsoup.connect(baseZetUrl + "default.aspx?id=330&route_id=" + route).get();
        Elements scheduleElements = document.getElementsByAttributeValueContaining("href", "direction_id=" + direction);
        String url = null;
        for (Element e : scheduleElements) {
            if (e.text().contains(time)) {
                url = e.attr("href");
                System.out.println("TU SAM!");
                System.out.println(url);
                break;
            }
        }
        document = Jsoup.connect(baseZetUrl + url).get();
        Elements pageContent = document.getElementsByClass("pageContent");
        Element orderedList = pageContent.get(0);
        Elements listItems = orderedList.getElementsByTag("li");
        for (Element item : listItems) {
            stationsTimes.add(item.text());
        }
        return stationsTimes;
    }
}

