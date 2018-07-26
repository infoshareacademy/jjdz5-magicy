import java.util.Date;

public class Route {
    private Integer id;
    private Date date;
    private String startCity;
    private String startStreet;
    private String endCity;
    private String endStreet;
    private String pickUpCity;
    private String pickUpStreet;
    private String startTime;
    private String endTime;
    private String pickUpTime;

    public Route(){

    }


    public Route(Integer id, Date date, String startCity, String startStreet, String endCity,
                 String endStreet, String pickUpCity, String pickUpStreet, String startTime, String endTime, String pickUpTime) {
        this.id = id;
        this.date = date;
        this.startCity = startCity;
        this.startStreet = startStreet;
        this.endCity = endCity;
        this.endStreet = endStreet;
        this.pickUpCity = pickUpCity;
        this.pickUpStreet = pickUpStreet;
        this.startTime = startTime;
        this.endTime = endTime;
        this.pickUpTime = pickUpTime;
    }

    public Integer getId() {
        return id;
    }

    public String getStartStreet() {
        return startStreet;
    }

    public String getEndStreet() {
        return endStreet;
    }

    public String getPickUpStreet() {
        return pickUpStreet;
    }

    public Date getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getStartCity() {
        return startCity;
    }
    public String getEndCity() {
        return endCity;
    }
    public String getPickUpCity() {
        return pickUpCity;
    }

    public String getPickUpTime() {
        return pickUpTime;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", date=" + date +
                ", startCity='" + startCity + '\'' +
                ", startStreet='" + startStreet + '\'' +
                ", endCity='" + endCity + '\'' +
                ", endStreet='" + endStreet + '\'' +
                ", pickUpCity='" + pickUpCity + '\'' +
                ", pickUpStreet='" + pickUpStreet + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", pickUpTime='" + pickUpTime + '\'' +
                '}';
    }
}
