import java.util.Date;

public class Ad {

    private Integer id;
    private Date date;
    private Integer routeId;

    public Ad() {
    }

    public Ad(Integer id, Date date) {
        this.id = id;
        this.date = date;
    }

    public Integer getid() {
        return id;
    }

    public Date getdate() {
        return date;
    }

    public void setdate(Date date) {
        this.date = date;
    }

    public Integer getRouteId() {
        return routeId;
    }
}
