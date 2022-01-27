package sample.DBClasses;

public class Sprzedawca {

    private Integer id_sprzedawca;
    private String imie;
    private String nazwisko;
    private String telefon;
    private String email;

    public Integer getId_sprzedawca() {
        return id_sprzedawca;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getEmail() {
        return email;
    }

    public Sprzedawca(Integer id_sprzedawca, String imie, String nazwisko, String telefon, String email) {
        this.id_sprzedawca = id_sprzedawca;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.telefon = telefon;
        this.email = email;
    }
}
