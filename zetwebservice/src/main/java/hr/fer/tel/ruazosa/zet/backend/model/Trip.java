package hr.fer.tel.ruazosa.zet.backend.model;

import java.util.Date;
import java.util.List;

/**
 * Created by zlatan on 6/7/16.
 */
public class Trip {

    private static class StationTimePair{
        private Station s;
        private Date date;
        public StationTimePair(Station s, Date date){
            this.s = s;
            this.date = date;
        }
        public Station getS() {
            return s;
        }
        public void setS(Station s) {
            this.s = s;
        }
        public Date getDate() {
            return date;
        }
        public void setDate(Date date) {
            this.date = date;
        }
    }

    private List<StationTimePair> stationTimePairs;
    private String tripId;
}
