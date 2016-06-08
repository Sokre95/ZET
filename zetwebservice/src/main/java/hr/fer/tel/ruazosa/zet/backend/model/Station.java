package hr.fer.tel.ruazosa.zet.backend.model;

import java.util.Date;

/**
 * Created by zlatan on 6/7/16.
 */
public class Station {
    private double latitude, longitude;
    private String name;
    private Date time;

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
    public void setName(String name) {
        this.name = name;
    }
    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
}
