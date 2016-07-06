package fer.ruazosa.ruazosa16_zet.model;

import java.io.Serializable;
import java.util.List;

public class Line implements Serializable {

    private int id;
    private String name;

    private List<Station> stations;
    private List<Trip> trips;

    public Line(int lineNumber, String lineName) {
        this.id = lineNumber;
        this.name = lineName;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public List<Station> getStations() {
        return stations;
    }
    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
    public List<Trip> getTrips() {
        return trips;
    }
    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        if (id==line.getId()) return false;
        return name.equals(line.name);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return id + "-" + name;
    }
}
