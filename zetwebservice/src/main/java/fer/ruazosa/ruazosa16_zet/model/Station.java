package fer.ruazosa.ruazosa16_zet.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zlatan on 6/7/16.
 */
public class Station implements Serializable {
    private double latitude, longitude;
    private String name;

    public Station(String name){
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Station stat = (Station) obj;
        return name.equals(stat.getName());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
