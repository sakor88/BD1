package sample.DBClasses;

public class SamochodNowy {

    private Integer  id_samochod_nowy;
    private Integer id_wyposazenie;
    private String model;
    private String marka;

    public Integer getId_samochod_nowy() {
        return id_samochod_nowy;
    }

    public Integer getId_wyposazenie() {
        return id_wyposazenie;
    }

    public String getModel() {
        return model;
    }

    public String getMarka() {
        return marka;
    }

    public SamochodNowy(Integer id_samochod_nowy, Integer id_wyposazenie, String model, String marka) {
        this.id_samochod_nowy = id_samochod_nowy;
        this.id_wyposazenie = id_wyposazenie;
        this.model = model;
        this.marka = marka;
    }
}
