package sample.DBClasses;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Controller;

public class Klient {

    private Integer id_klient;
    private String imie;
    private String nazwisko;
    private String telefon;
    private String email;

    public Klient(Integer id_klient, String imie, String nazwisko, String telefon, String email) {
        this.id_klient = id_klient;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.telefon = telefon;
        this.email = email;
    }

    public Integer getId_klient() {
        return id_klient;
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
}
