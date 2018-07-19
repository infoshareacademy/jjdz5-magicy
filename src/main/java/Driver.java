public class Driver extends User {

    private Integer id;
    private String city;
    private String district;

    public Driver(String name, String surname, String phone, String city, String district, Double rating, Integer id) {
        super(name, surname, phone, rating);
        this.city = city;
        this.district = district;
        this.id = id;
    }

    public Driver(){
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public Integer getId() {
        return id;
    }

    public void setDistrict(String district) {

        this.district = district;
    }

}
