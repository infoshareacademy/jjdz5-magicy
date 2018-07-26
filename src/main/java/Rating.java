public class Rating {
    private Double average;
    private Integer persons;

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Integer getPersons() {
        return persons;
    }

    public void setPersons(Integer persons) {
        this.persons = persons;
    }

    public double newAverage(Integer note){
        return (this.average * this.persons + note)/(this.persons+1);
    }

    public Rating(Double average, Integer persons) {
        this.average = average;
        this.persons = persons;
    }
    public Rating(){

    }
}
