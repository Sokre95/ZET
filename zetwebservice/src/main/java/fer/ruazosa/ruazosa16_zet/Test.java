package fer.ruazosa.ruazosa16_zet;

import java.io.IOException;
import java.util.ArrayList;

import fer.ruazosa.ruazosa16_zet.model.Line;
import fer.ruazosa.ruazosa16_zet.model.Trip;
import rx.functions.Action1;

public class Test {

    public static void main(String[] args) {
        try {
            ZetWebService.getInstance().getDailyBusRoutes().subscribe(new Action1<ArrayList<Line>>() {

                @Override
                public void call(ArrayList<Line> lines) {
                    for(Line l : lines) {
                        System.out.println(l.toString());
                        try{
                            ZetWebService.getInstance().loadLine(l).subscribe(new Action1<Line>(){

                                @Override
                                public void call(Line line) {
                                    ZetWebService.getInstance().loadTrip(line.getTrips().get(0))
                                            .subscribe(new Action1<Trip>() {
                                                @Override
                                                public void call(Trip trip) {
                                                    System.out.println(trip.getTimeTable());
                                                }
                                            });
                                }
                            });
                            Thread.sleep(1500);
                        } catch(Exception e){
                            e.printStackTrace();;
                        }
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true);
    }

}
