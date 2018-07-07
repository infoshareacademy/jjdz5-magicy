import java.sql.Time;
import java.util.Date;

public class Route {
    private Integer id;
    private String startAddress;
    private String stopAddress;
    private String routePoint;
    private Date date;
    private String startTime;
    private String stopTime;

    public Route(Integer id, String startAddress, String stopAddress, String routePoint, Date date, String startTime, String stopTime) {
        this.id = id;
        this.startAddress = startAddress;
        this.stopAddress = stopAddress;
        this.routePoint = routePoint;
        this.date = date;
        this.startTime = startTime;
        this.stopTime = stopTime;
    }

    public Integer getId() {
        return id;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public String getStopAddress() {
        return stopAddress;
    }

    public String getRoutePoint() {
        return routePoint;
    }

    public Date getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getStopTime() {
        return stopTime;
    }
}
