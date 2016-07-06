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
                public void call(ArrayList<Line> trips) {
                    for(Line t : trips) {
                        System.out.println(t.toString());
                        try{
                            ZetWebService.getInstance().getRouteSchedule(t.getId(), 0).subscribe(new Action1<ArrayList<Trip>>() {
                                @Override
                                public void call(ArrayList<Trip> trips) {
                                    for(Trip t: trips){
                                        System.out.println("\t"+t);
                                    }
                                }
                            });
                            Thread.sleep(1500);
                        } catch(Exception e){
                            e.printStackTrace();
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
