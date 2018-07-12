import java.util.Date;

class Advert {
    private Long id;
    private Date date;
    private Long routeId;
    private Driver driver;
    private Route route;

    public Advert(Long id, Date date, Long routeId) {
        this.id = id;
        this.date = date;
        this.routeId = routeId;
    }

    public Advert(Long id, Date date, Long routeId, Driver driver, Route route) {
        this.id = id;
        this.date = date;
        this.routeId = routeId;
        this.driver = driver;
        this.route = route;
    }

    public Driver getDriver() {
        return driver;
    }

    public Route getRoute() {
        return route;
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

