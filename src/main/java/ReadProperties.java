import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Properties;

public class ReadProperties {
    private final AppProperties appProperties = loadProperties();

    public static AppProperties loadProperties(){
        try {
            FileReader reader = new FileReader("config.properties");
            Properties properties = new Properties();
            properties.load(reader);

            return new AppProperties(properties);

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public String readCity(){
        return appProperties.getCity();
    }

    public String readFilePath(){
        return appProperties.getFilePath();
    }
}
