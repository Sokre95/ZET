package fer.ruazosa.ruazosa16_zet.model;

import java.io.Serializable;
import java.util.List;

public class Line implements Serializable {

    private String lineNumber;
    private String lineName;

    private List<Station> stations;
    private List<ShortTrip> trips;

    public Line(String lineNumber, String lineName) {
        this.lineNumber = lineNumber;
        this.lineName = lineName;
    }

    public String getLineNumber() {
        return lineNumber;
    }
    public String getLineName() {
        return lineName;
    }

    public List<Station> getStations() {
        return stations;
    }
    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
    public List<ShortTrip> getTrips() {
        return trips;
    }
    public void setTrips(List<ShortTrip> trips) {
        this.trips = trips;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        if (!lineNumber.equals(line.lineNumber)) return false;
        return lineName.equals(line.lineName);

    }

    @Override
    public int hashCode() {
        int result = lineNumber.hashCode();
        result = 31 * result + lineName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return lineNumber + "-" + lineName;
    }
}
