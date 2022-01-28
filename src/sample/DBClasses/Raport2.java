package sample.DBClasses;

public class Raport2 {

    private Float avg;
    private String imie;
    private String nazwisko;

    public Raport2(Float avg, String imie, String nazwisko) {
        this.avg = avg;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public Float getAvg() {
        return avg;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }
}
