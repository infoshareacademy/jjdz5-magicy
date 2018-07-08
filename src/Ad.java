import java.util.Date;

class Ad {
    Long id;
    Date date;
    Long routeId;

    public Ad(Long id, Date date, Long routeId) {
        this.id = id;
        this.date = date;
        this.routeId = routeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }
}

