import java.util.List;

public class AdvertsList {
    private final JsonToList jsonToList = new JsonToList();
    private final ReadProperties readProperties = new ReadProperties();
    private String path = readProperties.readFilePath();
    private List<Advert> advertsList = jsonToList.jsonToList(path);

    public List<Advert> getAdvertsList() {
        return advertsList;
    }

    public void setAdvertsList(List<Advert> advertsList) {
        this.advertsList = advertsList;
    }
}
