public class User {
    private String name;
    private String surname;
    private String phone;
    private Double rating;

    public User(String name, String surname, String phone, Double rating) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.rating = rating;
    }

    public User(){

    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
