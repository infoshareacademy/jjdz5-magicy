import java.util.List;

public class DriversList {
    private final JsonToList jsonToList = new JsonToList();
    private final ReadProperties readProperties = new ReadProperties();
    private String path = "driver.json";
    private List<Driver> driversList = jsonToList.driversToList(path);

    public List<Driver> getDriversList() {
        return driversList;
    }

    public void setDriversList(List<Driver> advertsList) {
        this.driversList = advertsList;
    }
}
