package fer.ruazosa.ruazosa16_zet.model;

import java.util.List;

/**
 * Created by zlatan on 6/7/16.
 */
public class Route {
    private int routeId;
    private String routeName;
    private List<Station> stations;
    private List<Trip> trips;

    public int getRouteId() {
        return routeId;
    }
    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }
    public String getRouteName() {
        return routeName;
    }
    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }
    public List<Trip> getTrips() {
        return trips;
    }
    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
    public List<Station> getStations() {
        return stations;
    }
    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
}
