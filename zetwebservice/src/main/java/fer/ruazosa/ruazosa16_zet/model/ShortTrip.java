package fer.ruazosa.ruazosa16_zet.model;

import java.util.Date;

/**
 * Created by zlatan on 6/7/16.
 */
public class ShortTrip {
    private String tripId;
    private Date startTime;
    private Station startingPoint;
    private Station destination;

    public String getTripId() {
        return tripId;
    }
    public void setTripId(String tripId) {
        this.tripId = tripId;
    }
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Station getStartingPoint() {
        return startingPoint;
    }
    public void setStartingPoint(Station startingPoint) {
        this.startingPoint = startingPoint;
    }
    public Station getDestination() {
        return destination;
    }
    public void setDestination(Station destination) {
        this.destination = destination;
    }
}
