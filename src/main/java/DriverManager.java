import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DriverManager {
    private final DriversList driversList = new DriversList();
    private  final UserInput userInput = new UserInput();

    public void showDrivers(List<Driver> driversList) {
        for (Driver driver : driversList) {
            System.out.println(driver.getId() + " - " + driver.getName() + " " + driver.getSurname()
                    + ", " + driver.getCity() + ", rating: " + driver.getRating().getAverage() + "/" + driver.getRating().getPersons());
        }
        giveRating(driversList, userInput.askForDriverId("Choose the driver you want to rate"));
        writeDriverData(driversList);

    }

    Integer getMaxId(List<Driver> driversList){
        Integer maxId = 2;
        for(Driver driver: driversList){
            if(driver.getId()> maxId){
                maxId = driver.getId();
            }
        }
        return maxId;
    }

    public void giveRating(List<Driver> driversList, Integer driverNum){
        for(Driver driver: driversList){
            if(driverNum.equals(driver.getId())){

                driver.getRating().setAverage(driver.getRating().newAverage(userInput.askForRating("Give your rating from 0 to 5")));
                driver.getRating().setPersons(driver.getRating().getPersons()+1);
                System.out.println("---New Rating----");
                System.out.println(driver.getId()+" - "+driver.getName()+" "+ driver.getSurname()
                        + ", "+ driver.getCity()+", rating: "+ driver.getRating().getAverage()+"/"+driver.getRating().getPersons());
                System.out.println("-------");
                break;
            }
        }
    }

    public void writeDriverData(List<Driver> driversList){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String arrayToJson = objectMapper.writeValueAsString(driversList);
            BufferedWriter writer = new BufferedWriter(new FileWriter("driver.json"));
            writer.write(arrayToJson);
            writer.close();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
