package sample.DBClasses;

public class SamochodUzywany {

    public static Integer id = 6;

    private Integer id_samochod_uzywany;
    private Integer id_naped;
    private Integer przebieg;
    private String marka;
    private Boolean bezwypadkowosc;
    private Integer cena;
    private Integer rok_produkcji;
    private Integer nr_wlasciciela;
    private String model;

    public Integer getId_samochod_uzywany() {
        return id_samochod_uzywany;
    }

    public Integer getId_naped() {
        return id_naped;
    }

    public Integer getPrzebieg() {
        return przebieg;
    }

    public String getMarka() {
        return marka;
    }

    public Boolean getBezwypadkowosc() {
        return bezwypadkowosc;
    }

    public Integer getCena() {
        return cena;
    }

    public Integer getRok_produkcji() {
        return rok_produkcji;
    }

    public Integer getNr_wlasciciela() {
        return nr_wlasciciela;
    }

    public String getModel() {
        return model;
    }



    public SamochodUzywany(Integer id_samochod_uzywany, Integer id_naped, Integer przebieg, String marka, Boolean bezwypadkowosc, Integer cena, Integer rok_produkcji, Integer nr_wlasciciela, String model) {
        this.id_samochod_uzywany = id_samochod_uzywany;
        this.id_naped = id_naped;
        this.przebieg = przebieg;
        this.marka = marka;
        this.bezwypadkowosc = bezwypadkowosc;
        this.cena = cena;
        this.rok_produkcji = rok_produkcji;
        this.nr_wlasciciela = nr_wlasciciela;
        this.model = model;
    }


}
