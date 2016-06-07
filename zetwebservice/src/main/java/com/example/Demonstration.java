package com.example;

import java.io.IOException;

public class Demonstration {

    public static void main(String[] args) throws IOException {
        ZetData zetData = new ZetData("http://www.zet.hr/");
        System.out.println("Dnevne linije:");
        zetData.getDailyTramRoutes().forEach(e -> System.out.println(e));
        System.out.println("Nocne linije:");
        zetData.getNightlyTramRoutes().forEach(e -> System.out.println(e));

        System.out.println("raspored za dvojku");
        zetData.getSchedule(2, 0).forEach(e -> System.out.println(e));
        System.out.println("raspored dolazaka na pojedinu stanicu za dvojku u: " + zetData.getSchedule(2, 0).get(0));
        zetData.getRouteStationsTimes(2, 0, zetData.getSchedule(13, 0).get(0).split("")[0]).forEach(e -> System.out.println(e));

        System.out.println("Busevi;dnevni:");
        zetData.getDailyBusRoutes().forEach(e -> System.out.println(e));
        System.out.println("nocni:");
        zetData.getNightlyBusRoutes().forEach(e -> System.out.println(e));

        System.out.println("Raspored za 215");
        zetData.getSchedule(215, 0).forEach(e -> System.out.println(e));
        System.out.println("raspored dolazaka na pojedinu stanicu za 215 u: " + zetData.getSchedule(215, 0).get(0));
        zetData.getRouteStationsTimes(215, 0, zetData.getSchedule(215, 0).get(0).split(" ")[0]).forEach(e -> System.out.println(e));

    }
}
