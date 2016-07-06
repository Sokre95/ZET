package fer.ruazosa.ruazosa16_zet.model;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fer.ruazosa.ruazosa16_zet.ZetWebService;
import jdk.nashorn.internal.runtime.ParserException;

public class Trip implements Serializable {
    private int routeId;
    private String id;
    private int direction;

    private List<StationTimePair> timeTable = new ArrayList<>();
    private Station startingPoint;
    private Station destination;
    private Date departureTime;

    public Trip(int routeId,String id, int direction) {
        this.routeId = routeId;
        this.id = id;
        this.direction = direction;
    }

    public int getRouteId() {
        return routeId;
    }
    public int getDirection() {
        return direction;
    }
    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }
    public Date getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTimeWithString(String time){
        try {
            departureTime = ZetWebService.DATE_FORMAT.parse(time);
        } catch(ParseException ex){
            ex.printStackTrace();
        }
    }
    public String departureTimeAsString(){
        return ZetWebService.DATE_FORMAT.format(departureTime);
    }
    public String getId() {
        return id;
    }
    public void setStartingPoint(Station startingPoint) {
        this.startingPoint = startingPoint;
    }
    public Station getStartingPoint() {
        return startingPoint;
    }
    public void setDestination(Station destination) {
        this.destination = destination;
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
