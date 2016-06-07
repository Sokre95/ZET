package fer.ruazosa.ruazosa16_zet.wrappers;

public class InfoDisplay {

    private String time;
    private String station;
    private String routeName;

    public InfoDisplay(String time, String station, String routeName) {
        this.time = time;
        this.station = station;
        this.routeName = routeName;
    }

    public String getTime() {
        return time;
    }

    public String getStation() {
        return station;
    }

    public String getRouteName() {
        return routeName;
    }
}
