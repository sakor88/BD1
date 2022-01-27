package sample.DBClasses;

public class ZamowienieNowe {

    private Integer id_zam_nowe;
    private Integer id_klient;
    private Integer id_sprzedawca;
    private String data_realizacji;
    private Integer cena;

    public ZamowienieNowe(Integer id_zam_nowe, Integer id_klient, Integer id_sprzedawca, String data_realizacji, Integer cena) {
        this.id_zam_nowe = id_zam_nowe;
        this.id_klient = id_klient;
        this.id_sprzedawca = id_sprzedawca;
        this.data_realizacji = data_realizacji;
        this.cena = cena;
    }

    public Integer getId_zam_nowe() {
        return id_zam_nowe;
    }

    public Integer getId_klient() {
        return id_klient;
    }

    public Integer getId_sprzedawca() {
        return id_sprzedawca;
    }

    public String getData_realizacji() {
        return data_realizacji;
    }

    public Integer getCena() {
        return cena;
    }
}
