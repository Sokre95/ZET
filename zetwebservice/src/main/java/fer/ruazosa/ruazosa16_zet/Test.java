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
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        //while(true);
    }

}
