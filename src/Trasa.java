import java.sql.Time;
import java.util.Date;

public class Trasa {
    private String adresStart;
    private String adresKoniec;
    private String adresPktPosredni;
    private Date data;
    private Time godzinaStart;
    private Time godzinaKoniec;

    public String getAdresStart() {
        return adresStart;
    }

    public void setAdresStart(String adresStart) {
        this.adresStart = adresStart;
    }

    public String getAdresKoniec() {
        return adresKoniec;
    }

    public void setAdresKoniec(String adresKoniec) {
        this.adresKoniec = adresKoniec;
    }

    public String getAdresPktPosredni() {
        return adresPktPosredni;
    }

    public void setAdresPktPosredni(String adresPktPosredni) {
        this.adresPktPosredni = adresPktPosredni;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getGodzinaStart() {
        return godzinaStart;
    }

    public void setGodzinaStart(Time godzinaStart) {
        this.godzinaStart = godzinaStart;
    }

    public Time getGodzinaKoniec() {
        return godzinaKoniec;
    }

    public void setGodzinaKoniec(Time godzinaKoniec) {
        this.godzinaKoniec = godzinaKoniec;
    }

    public Trasa() {
    }
}
