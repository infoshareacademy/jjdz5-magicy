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
    private String stopTime;
    private String pickUpTime;


    public Route(Integer id, Date date, String startCity, String startStreet, String endCity,
    String endStreet, String pickUpCity, String pickUpStreet, String startTime, String stopTime, String pickUpTime) {
        this.id = id;
        this.date = date;
        this.startCity = startCity;
        this.startStreet = startStreet;
        this.endCity = endCity;
        this.endStreet = endStreet;
        this.pickUpCity = pickUpCity;
        this.pickUpStreet = pickUpStreet;
        this.startTime = startTime;
        this.stopTime = stopTime;
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

    public String getStopTime() {
        return stopTime;
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

}
