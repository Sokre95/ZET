package fer.ruazosa.ruazosa16_zet.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Line implements Serializable {

    private int id;
    private String name;

    private List<Station> stations = new ArrayList<>();
    private List<Trip> trips0 = new ArrayList<>();
    private List<Trip> trips1 = new ArrayList<>();

    public Line(int lineNumber) {
        this.id = lineNumber;
    }

    public Line(int lineNumber, String name) {
        this.id = lineNumber;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Trip> getTrips(int dir) {
        return dir==0?trips0:trips1;
    }

    public void setTrips(List<Trip> trips, int dir) {
        if(dir==0){
            trips0 = trips;
        } else {
            trips1 = trips;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        if (id == line.getId()) return false;
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
