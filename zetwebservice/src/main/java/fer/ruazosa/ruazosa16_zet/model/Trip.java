package fer.ruazosa.ruazosa16_zet.model;

import java.io.Serializable;

public class Trip implements Serializable {

    String departureTime;
    String startingPoint;
    String destionation;

    public Trip(String departureTime, String startingPoint, String destionation) {
        this.departureTime = departureTime;
        this.startingPoint = startingPoint;
        this.destionation = destionation;
    }

    public String getDepartureTime() {

        return departureTime;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public String getDestionation() {
        return destionation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trip trip = (Trip) o;

        if (!departureTime.equals(trip.departureTime)) return false;
        if (!startingPoint.equals(trip.startingPoint)) return false;
        return destionation.equals(trip.destionation);

    }

    @Override
    public int hashCode() {
        int result = departureTime.hashCode();
        result = 31 * result + startingPoint.hashCode();
        result = 31 * result + destionation.hashCode();
        return result;
    }
}
