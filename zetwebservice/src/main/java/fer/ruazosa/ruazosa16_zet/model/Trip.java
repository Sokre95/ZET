package fer.ruazosa.ruazosa16_zet.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fer.ruazosa.ruazosa16_zet.ZetWebService;

public class Trip implements Serializable {
    private String id;
    private List<StationTimePair> timeTable = new ArrayList<>();
    private Station startingPoint;
    private Station destination;
    private Date departureTime;
    private int direction;

    public Trip(String id, Date departureTime, Station startingPoint, Station destination) {
        this.id = id;
        this.startingPoint = startingPoint;
        this.destination = destination;
        this.departureTime = departureTime;
    }

    public int getDirection() {
        return direction;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }
    public Date getDepartureTime() {
        return departureTime;
    }
    public String getId() {
        return id;
    }
    public Station getStartingPoint() {
        return startingPoint;
    }
    public Station getDestination() {
        return destination;
    }
    public List<StationTimePair> getTimeTable() {
        return timeTable;
    }
    public void setTimeTable(List<StationTimePair> timeTable) {
        this.timeTable = timeTable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trip trip = (Trip) o;

        if (!departureTime.equals(trip.getDepartureTime())) return false;
        if (!startingPoint.equals(trip.getStartingPoint())) return false;
        return destination.equals(trip.getDestination());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return ZetWebService.DATE_FORMAT.format(departureTime) +" " + startingPoint + " " + destination;
    }


    public static class StationTimePair{
        private Station s;
        private Date time;

        public StationTimePair(Station s, Date time){
            this.s = s;
            this.time = time;
        }
        public StationTimePair(){

        }
        public Station getS() {
            return s;
        }
        public void setS(Station s) {
            this.s = s;
        }
        public Date getTime() {
            return time;
        }
        public void setTime(Date time) {
            this.time = time;
        }
    }


}
