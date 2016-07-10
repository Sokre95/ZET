package fer.ruazosa.ruazosa16_zet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fer.ruazosa.ruazosa16_zet.model.Line;
import fer.ruazosa.ruazosa16_zet.model.Station;
import fer.ruazosa.ruazosa16_zet.model.Trip;
import rx.functions.Action1;

public class Test {

    public static void main(String[] args) {
        try {
            final Scanner in = new Scanner(System.in);
            ZetWebService.getInstance().getDailyTramRoutes().subscribe(new Action1<ArrayList<Line>>() {
                @Override
                public void call(ArrayList<Line> lines) {
                    for(int i=1; i<=lines.size(); i++){
                        System.out.println(i + ". " + lines.get(i-1));
                    }
                    System.out.println("Enter the number of the line you wish to open.");
                    int index = in.nextInt()-1;
                    System.out.println("Loading data for line " + lines.get(index) + "...");

                    ZetWebService.getInstance().loadLine(lines.get(index)).subscribe(new Action1<Line>() {
                        @Override
                        public void call(Line line) {
                            try {
                                Thread.sleep(5000);
                            } catch (Exception ex){}
                            /*
                            //Kod za učitavanje podataka o Trip-ovima
                            for(int i=1; i<=line.getTrips().size(); i++){
                                System.out.println(i + ". " + line.getTrips().get(i-1));
                            }
                            System.out.println("Enter the number of the trip you wish to open.");
                            int index = in.nextInt()-1;
                            System.out.println("Opening details for trip " + line.getTrips().get(index));
                            ZetWebService.getInstance().loadTrip(line.getTrips().get(index)).subscribe(new Action1<Trip>() {
                                @Override
                                public void call(Trip trip) {
                                    for(int i=1; i<=trip.getTimeTable().size(); i++){
                                        System.out.println(i + ". " + trip.getTimeTable().get(i-1));
                                    }
                                }
                            });*/

                            //Kod za ispisivanje podataka o stanicama
                            for(Station s: line.getStations()){
                                System.out.println(s);
                            }
                            System.out.println("Done.");
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(true);
    }

}
