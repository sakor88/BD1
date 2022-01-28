package sample.DBClasses;

public class Serwisant {

    public static Integer id = 6;

    private Integer id_serwisant;
    private String imie;
    private String nazwisko;
    private String telefon;
    private String email;

    public Integer getId_serwisant() {
        return id_serwisant;
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

    public Serwisant(Integer id_serwisant, String imie, String nazwisko, String telefon, String email) {
        this.id_serwisant = id_serwisant;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.telefon = telefon;
        this.email = email;
    }
}
