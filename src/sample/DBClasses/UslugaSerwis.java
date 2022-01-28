package sample.DBClasses;

public class UslugaSerwis {

    public static Integer id = 6;

    private Integer id_usluga;
    private String nazwa;
    private float czas_uslugi;
    private String data;

    public Integer getId_usluga() {
        return id_usluga;
    }

    public String getNazwa() {
        return nazwa;
    }

    public float getCzas_uslugi() {
        return czas_uslugi;
    }

    public String getData() {
        return data;
    }

    public UslugaSerwis(Integer id_usluga, String nazwa, float czas_uslugi, String data) {
        this.id_usluga = id_usluga;
        this.nazwa = nazwa;
        this.czas_uslugi = czas_uslugi;
        this.data = data;
    }
}
