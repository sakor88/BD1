package sample.DBClasses;

public class Wyposazenie {

    public static Integer id = 6;

    private Integer id_wyposazenie;
    private Integer id_naped;
    private String wersja_wyposazenia;

    public Wyposazenie(Integer id_wyposazenie, Integer id_naped, String wersja_wyposazenia) {
        this.id_wyposazenie = id_wyposazenie;
        this.id_naped = id_naped;
        this.wersja_wyposazenia = wersja_wyposazenia;
    }

    public Integer getId_wyposazenie() {
        return id_wyposazenie;
    }

    public Integer getId_naped() {
        return id_naped;
    }

    public String getWersja_wyposazenia() {
        return wersja_wyposazenia;
    }
}
