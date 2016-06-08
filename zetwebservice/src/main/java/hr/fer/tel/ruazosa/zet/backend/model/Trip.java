package hr.fer.tel.ruazosa.zet.backend.model;

import java.util.List;

/**
 * Created by zlatan on 6/7/16.
 */
public class Trip{
    private List<Station> stations;
    private String tripId;

    public List<Station> getStations() {
        return stations;
    }
    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
    public String getTripId() {
        return tripId;
    }
    public void setTripId(String tripId) {
        this.tripId = tripId;
    }
}
