package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.DBClasses.*;

import java.sql.*;

import java.io.IOException;

public class Controller {

    private Parent root;
    private Scene scene;
    private Stage stage;

    // ------------------ Polaczenie z bazą ------------------

    public Connection getConnection() {

        System.out.println("Sprawdzenie czy sterownik jest zarejestrowany w menadzerze");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Nie znaleziono sterownika!");
            System.out.println("Wyduk sledzenia bledu i zakonczenie.");
            cnfe.printStackTrace();
            System.exit(1);
        }
        System.out.println("Zarejstrowano sterownik - OK, kolejny krok nawiazanie polaczenia z baza danych.");

        Connection conn = null;

        try {
            String url = "jdbc:postgresql://tyke.db.elephantsql.com:5432/btzzzahi";
            String username = "btzzzahi";
            String password = "WjXcJGCobr0VAVpBbAA1TaO-L8wtwiBF";

            conn = DriverManager.getConnection(url,username,password);
            System.out.println("Udalo Sie połączyć z bazą danych!");
            return conn;
        } catch (SQLException se) {
            System.out.println("Brak polaczenia z baza danych, wydruk logu sledzenia i koniec.");
            se.printStackTrace();
            System.exit(1);
        }
        return null;
    }


    // ------------------ Kolumny FXML ------------------

    // Klient  --------

    @FXML
    private TableView<Klient> KlientTable;

    @FXML
    private TableColumn<Klient,Integer> colKlientId;

    @FXML
    private TableColumn<Klient,String> colKlientImie;

    @FXML
    private TableColumn<Klient,String> colKlientNazwisko;

    @FXML
    private TableColumn<Klient,String> colKlientTelefon;

    @FXML
    private TableColumn<Klient,String> colKlientEmail;

    // Naped  --------

    @FXML
    private TableView<Naped> NapedTable;

    @FXML
    private TableColumn<Naped,Integer> colNapedId;

    @FXML
    private TableColumn<Naped,String> colNapedKod;

    @FXML
    private TableColumn<Naped,Integer> colNapedMoc;

    @FXML
    private TableColumn<Naped,String> colNapedSkrzynia;

    @FXML
    private TableColumn<Naped,Integer> colNapedPojemnosc;

    @FXML
    private TableColumn<Naped,Integer> colNapedMoment;

    // SamochodNowy  --------

    @FXML
    private TableView<SamochodNowy> SamochodNowyTable;

    @FXML
    private TableColumn<SamochodNowy,Integer> colSamochodNowyId;

    @FXML
    private TableColumn<SamochodNowy,Integer> colSamochodNowyIdWyposazenie;

    @FXML
    private TableColumn<SamochodNowy,String> colSamochodNowyModel;

    @FXML
    private TableColumn<SamochodNowy,String> colSamochodNowyMarka;

    // SamochodSerwis  --------

    @FXML
    private TableView<SamochodSerwis> SamochodSerwisTable;

    @FXML
    private TableColumn<SamochodSerwis,Integer> colSamochodSerwisNrRej ;

    @FXML
    private TableColumn<SamochodSerwis,String> colSamochodSerwisModel;

    @FXML
    private TableColumn<SamochodSerwis,String> colSamochodSerwisMarka;


    // SamochodUzywany  --------

    @FXML
    private TableView<SamochodUzywany> SamochodUzywanyTable;

    @FXML
    private TableColumn<SamochodUzywany,Integer> colSamochodUzywanyId;

    @FXML
    private TableColumn<SamochodUzywany,Integer> colSamochodUzywanyIdNaped;

    @FXML
    private TableColumn<SamochodUzywany,Integer> colSamochodUzywanyPrzebieg;

    @FXML
    private TableColumn<SamochodUzywany,Boolean> colSamochodUzywanyBezwypadkowosc;

    @FXML
    private TableColumn<SamochodUzywany,Integer> colSamochodUzywanyCena;

    @FXML
    private TableColumn<SamochodUzywany,Integer> colSamochodUzywanyRokProdukcji;

    @FXML
    private TableColumn<SamochodUzywany,Integer> colSamochodUzywanyNrWlasciciela;

    @FXML
    private TableColumn<SamochodUzywany,String> colSamochodUzywanyMarka;

    @FXML
    private TableColumn<SamochodUzywany,String> colSamochodUzywanyModel;


    // Serwisant  --------

    @FXML
    private TableView<Serwisant> SerwisantTable;

    @FXML
    private TableColumn<Serwisant,Integer> colSerwisantId;

    @FXML
    private TableColumn<Serwisant,String> colSerwisantImie;

    @FXML
    private TableColumn<Serwisant,String> colSerwisantNazwisko;

    @FXML
    private TableColumn<Serwisant,String> colSerwisantTelefon;

    @FXML
    private TableColumn<Serwisant,String> colSerwisantEmail;


    // Sprzedawca --------

    @FXML
    private TableView<Sprzedawca> SprzedawcaTable;

    @FXML
    private TableColumn<Sprzedawca,Integer> colSprzedawcaId;

    @FXML
    private TableColumn<Sprzedawca,String> colSprzedawcaImie;

    @FXML
    private TableColumn<Sprzedawca,String> colSprzedawcaNazwisko;

    @FXML
    private TableColumn<Sprzedawca,String> colSprzedawcaTelefon;

    @FXML
    private TableColumn<Sprzedawca,String> colSprzedawcaEmail;


    // UslugaSerwis--------

    @FXML
    private TableView<UslugaSerwis> UslugaSerwisTable;

    @FXML
    private TableColumn<UslugaSerwis,Integer> colUslugaSerwisId;

    @FXML
    private TableColumn<UslugaSerwis,String> colUslugaSerwisNazwa;

    @FXML
    private TableColumn<UslugaSerwis,Float> colUslugaSerwisCzas;

    @FXML
    private TableColumn<UslugaSerwis,String> colUslugaSerwisData;


    // Wyposazenie --------

    @FXML
    private TableView<Wyposazenie> WyposazenieTable;

    @FXML
    private TableColumn<Wyposazenie,Integer> colWyposazenieId;

    @FXML
    private TableColumn<Wyposazenie,Integer> colWyposazenieIdNaped;

    @FXML
    private TableColumn<Wyposazenie,String> colWyposazenieWersja;

    // ZamowienieNowe --------

    @FXML
    private TableView<ZamowienieNowe> ZamowienieNoweTable;

    @FXML
    private TableColumn<ZamowienieNowe,Integer> colZamowienieNoweId;

    @FXML
    private TableColumn<ZamowienieNowe,Integer> colZamowienieNoweIdKlient;

    @FXML
    private TableColumn<ZamowienieNowe,Integer> colZamowienieNoweIdSprzedawca;

    @FXML
    private TableColumn<ZamowienieNowe,String> colZamowienieNoweDataRealizacji;

    @FXML
    private TableColumn<ZamowienieNowe,Integer> colZamowienieNoweCena;

    // Raport 1 --------

    @FXML
    private TableView<Klient> Raport1Table;

    @FXML
    private TableColumn<Klient,Integer> colRaport1Id;

    @FXML
    private TableColumn<Klient,String> colRaport1Imie;

    @FXML
    private TableColumn<Klient,String> colRaport1Nazwisko;

    @FXML
    private TableColumn<Klient,String> colRaport1Telefon;

    @FXML
    private TableColumn<Klient,String> colRaport1Email;

    // Raport 2 --------

    @FXML
    private TableView<Raport2> Raport2Table;

    @FXML
    private TableColumn<Raport2,Float> colRaport2Avg;

    @FXML
    private TableColumn<Raport2,String> colRaport2Imie;

    @FXML
    private TableColumn<Raport2,String> colRaport2Nazwisko;


    // Raport 3 --------

    @FXML
    private TableView<SamochodUzywany> Raport3Table;

    @FXML
    private TableColumn<SamochodUzywany, Integer> colRaport3IdSamochodUzywany;

    @FXML
    private TableColumn<SamochodUzywany, Integer> colRaport3IdNaped;

    @FXML
    private TableColumn<SamochodUzywany, Integer> colRaport3Przebieg;

    @FXML
    private TableColumn<SamochodUzywany, Boolean> colRaport3Bezwypadkowosc;

    @FXML
    private TableColumn<SamochodUzywany, Integer> colRaport3Cena;

    @FXML
    private TableColumn<SamochodUzywany, Integer> colRaport3RokProdukcji;

    @FXML
    private TableColumn<SamochodUzywany, Integer> colRaport3NrWlasciciela;

    @FXML
    private TableColumn<SamochodUzywany, String> colRaport3Marka;

    @FXML
    private TableColumn<SamochodUzywany, String> colRaport3Model;


    // -------------- Wyświetlanie Raport1 ---------------

    public ObservableList<Klient> getRaport1List(){
        ObservableList<Klient> klientList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM raport1";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Klient klient;
            while(rs.next()) {
                klient = new Klient(rs.getInt("id_klient"), rs.getString("imie"), rs.getString("nazwisko"), rs.getString("telefon"), rs.getString("email"));
                klientList.add(klient);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return klientList;
    }

    public void showRaport1(){

        ObservableList<Klient> klientList = getRaport1List();

        //System.out.println("" + klientList.get(0).getId_klient());

        colRaport1Id.setCellValueFactory(new PropertyValueFactory<Klient,Integer>("id_klient"));
        colRaport1Imie.setCellValueFactory(new PropertyValueFactory<Klient,String>("imie"));
        colRaport1Nazwisko.setCellValueFactory(new PropertyValueFactory<Klient,String>("nazwisko"));
        colRaport1Telefon.setCellValueFactory(new PropertyValueFactory<Klient,String>("telefon"));
        colRaport1Email.setCellValueFactory(new PropertyValueFactory<Klient,String>("email"));

        Raport1Table.setItems(klientList);
    }

    // -------------- Wyświetlanie Raport2 ---------------

    public ObservableList<Raport2> getRaport2List(){
        ObservableList<Raport2> raport2List = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM raport2";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Raport2 raport2;
            while(rs.next()) {
                raport2 = new Raport2(rs.getFloat("sredni_czas_uslugi"), rs.getString("imie"), rs.getString("nazwisko"));
                raport2List.add(raport2);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return raport2List;
    }

    public void showRaport2(){

        ObservableList<Raport2> raport2List = getRaport2List();

        colRaport2Avg.setCellValueFactory(new PropertyValueFactory<Raport2,Float>("avg"));
        colRaport2Imie.setCellValueFactory(new PropertyValueFactory<Raport2,String>("imie"));
        colRaport2Nazwisko.setCellValueFactory(new PropertyValueFactory<Raport2,String>("nazwisko"));

        Raport2Table.setItems(raport2List);
    }

    // -------------- Wyświetlanie Raport3 ---------------

    public ObservableList<SamochodUzywany> getRaport3List(){
        ObservableList<SamochodUzywany> samochodUzywanyList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM raport3";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            SamochodUzywany samochodUzywany;
            while(rs.next()) {
                samochodUzywany = new SamochodUzywany(rs.getInt("id_samochod_uzywany"), rs.getInt("id_naped"), rs.getInt("przebieg"), rs.getString("marka"), rs.getBoolean("bezwypadkowosc"), rs.getInt("cena"), rs.getInt("rok_produkcji"), rs.getInt("nr_wlasciciela"), rs.getString("model"));
                samochodUzywanyList.add(samochodUzywany);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return samochodUzywanyList;
    }

    public void showRaport3(){

        ObservableList<SamochodUzywany> samochodUzywanyList = getRaport3List();

        colRaport3IdSamochodUzywany.setCellValueFactory(new PropertyValueFactory<SamochodUzywany,Integer>("id_samochod_uzywany"));
        colRaport3IdNaped.setCellValueFactory(new PropertyValueFactory<SamochodUzywany,Integer>("id_naped"));
        colRaport3Przebieg.setCellValueFactory(new PropertyValueFactory<SamochodUzywany,Integer>("przebieg"));
        colRaport3Marka.setCellValueFactory(new PropertyValueFactory<SamochodUzywany,String>("marka"));
        colRaport3Bezwypadkowosc.setCellValueFactory(new PropertyValueFactory<SamochodUzywany,Boolean>("bezwypadkowosc"));
        colRaport3Cena .setCellValueFactory(new PropertyValueFactory<SamochodUzywany,Integer>("cena"));
        colRaport3RokProdukcji.setCellValueFactory(new PropertyValueFactory<SamochodUzywany,Integer>("rok_produkcji"));
        colRaport3NrWlasciciela.setCellValueFactory(new PropertyValueFactory<SamochodUzywany,Integer>("nr_wlasciciela"));
        colRaport3Model.setCellValueFactory(new PropertyValueFactory<SamochodUzywany,String>("model"));

        Raport3Table.setItems(samochodUzywanyList);
    }



    // -------------- Wyświetlanie Klient ---------------

    public ObservableList<Klient> getKlientList(){
        ObservableList<Klient> klientList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM projekt.klient ORDER BY id_klient";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Klient klient;
            while(rs.next()) {
                klient = new Klient(rs.getInt("id_klient"), rs.getString("imie"), rs.getString("nazwisko"), rs.getString("telefon"), rs.getString("email"));
                klientList.add(klient);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return klientList;
    }

    public void showKlient(){

        ObservableList<Klient> klientList = getKlientList();

        //System.out.println("" + klientList.get(0).getId_klient());

        colKlientId.setCellValueFactory(new PropertyValueFactory<Klient,Integer>("id_klient"));
        colKlientImie.setCellValueFactory(new PropertyValueFactory<Klient,String>("imie"));
        colKlientNazwisko.setCellValueFactory(new PropertyValueFactory<Klient,String>("nazwisko"));
        colKlientTelefon.setCellValueFactory(new PropertyValueFactory<Klient,String>("telefon"));
        colKlientEmail.setCellValueFactory(new PropertyValueFactory<Klient,String>("email"));

        KlientTable.setItems(klientList);
    }

    // -------------- Wyświetlanie Naped ---------------

    public ObservableList<Naped> getNapedList(){
        ObservableList<Naped> napedList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM projekt.naped";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Naped naped;
            while(rs.next()) {
                naped = new Naped(rs.getInt("id_naped"), rs.getString("kod_silnika"), rs.getInt("moc"), rs.getString("skrzynia_biegow"), rs.getInt("pojemnosc"), rs.getInt("moment_obrotowy"));
                napedList.add(naped);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return napedList;
    }

    public void showNaped(){

        ObservableList<Naped> napedList = getNapedList();

        colNapedId.setCellValueFactory(new PropertyValueFactory<Naped,Integer>("id_Naped"));
        colNapedKod.setCellValueFactory(new PropertyValueFactory<Naped,String>("kod_silnika"));
        colNapedMoc.setCellValueFactory(new PropertyValueFactory<Naped,Integer>("moc"));
        colNapedSkrzynia.setCellValueFactory(new PropertyValueFactory<Naped,String>("skrzynia_biegow"));
        colNapedPojemnosc.setCellValueFactory(new PropertyValueFactory<Naped,Integer>("pojemnosc"));
        colNapedMoment.setCellValueFactory(new PropertyValueFactory<Naped,Integer>("moment_obrotowy"));

        NapedTable.setItems(napedList);
    }

    // -------------- Wyświetlanie SamochodNowy ---------------

    public ObservableList<SamochodNowy> getSamochodNowyList(){
        ObservableList<SamochodNowy> SamochodNowyList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM projekt.samochod_nowy";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            SamochodNowy samochodNowy;
            while(rs.next()) {
                samochodNowy = new SamochodNowy(rs.getInt("id_samochod_nowy"), rs.getInt("id_wyposazenie"), rs.getString("model"), rs.getString("marka"));
                SamochodNowyList.add(samochodNowy);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return SamochodNowyList;
    }

    public void showSamochodNowy(){

        ObservableList<SamochodNowy> samochodNowyList = getSamochodNowyList();

        colSamochodNowyId.setCellValueFactory(new PropertyValueFactory<SamochodNowy,Integer>("id_samochod_nowy"));
        colSamochodNowyIdWyposazenie.setCellValueFactory(new PropertyValueFactory<SamochodNowy,Integer>("id_wyposazenie"));
        colSamochodNowyMarka.setCellValueFactory(new PropertyValueFactory<SamochodNowy,String>("marka"));
        colSamochodNowyModel.setCellValueFactory(new PropertyValueFactory<SamochodNowy,String>("model"));

        SamochodNowyTable.setItems(samochodNowyList);
    }

    // -------------- Wyświetlanie ZamowienieNowe ---------------

    public ObservableList<ZamowienieNowe> getZamowienieNoweList(){
        ObservableList<ZamowienieNowe> zamowienieNoweList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM projekt.zamowienie_nowe";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            ZamowienieNowe zamowienieNowe;
            while(rs.next()) {
                zamowienieNowe = new ZamowienieNowe(rs.getInt("id_zam_nowe"), rs.getInt("id_klient"), rs.getInt("id_sprzedawca"), rs.getString("data_realizacji"), rs.getInt("cena"));
                zamowienieNoweList.add(zamowienieNowe);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return zamowienieNoweList;
    }

    public void showZamowienieNowe(){

        ObservableList<ZamowienieNowe> zamowienieNoweList = getZamowienieNoweList();

        colZamowienieNoweId.setCellValueFactory(new PropertyValueFactory<ZamowienieNowe,Integer>("id_zam_nowe"));
        colZamowienieNoweIdKlient.setCellValueFactory(new PropertyValueFactory<ZamowienieNowe,Integer>("id_klient"));
        colZamowienieNoweIdSprzedawca.setCellValueFactory(new PropertyValueFactory<ZamowienieNowe,Integer>("id_sprzedawca"));
        colZamowienieNoweDataRealizacji.setCellValueFactory(new PropertyValueFactory<ZamowienieNowe,String>("data_realizacji"));
        colZamowienieNoweCena.setCellValueFactory(new PropertyValueFactory<ZamowienieNowe,Integer>("cena"));

        ZamowienieNoweTable.setItems(zamowienieNoweList);
    }

    // -------------- Wyświetlanie SamochodSerwis ---------------

    public ObservableList<SamochodSerwis> getSamochodSerwisList(){
        ObservableList<SamochodSerwis> samochodSerwisList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM projekt.samochod_serwis";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            SamochodSerwis samochodSerwis;
            while(rs.next()) {
                samochodSerwis = new SamochodSerwis(rs.getString("nr_rej"), rs.getString("marka"), rs.getString("model") );
                samochodSerwisList.add(samochodSerwis);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return samochodSerwisList;
    }

    public void showSamochodSerwis(){

        ObservableList<SamochodSerwis> samochodSerwisList = getSamochodSerwisList();

        colSamochodSerwisNrRej.setCellValueFactory(new PropertyValueFactory<SamochodSerwis,Integer>("nr_rej"));
        colSamochodSerwisMarka.setCellValueFactory(new PropertyValueFactory<SamochodSerwis,String>("marka"));
        colSamochodSerwisModel.setCellValueFactory(new PropertyValueFactory<SamochodSerwis,String>("model"));


        SamochodSerwisTable.setItems(samochodSerwisList);
    }

    // -------------- Wyświetlanie SamochodUzywany ---------------

    public ObservableList<SamochodUzywany> getSamochodUzywanyList(){
        ObservableList<SamochodUzywany> samochodUzywanyList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM projekt.samochod_uzywany";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            SamochodUzywany samochodUzywany;
            while(rs.next()) {
                samochodUzywany = new SamochodUzywany(rs.getInt("id_samochod_uzywany"), rs.getInt("id_naped"), rs.getInt("przebieg"), rs.getString("marka"), rs.getBoolean("bezwypadkowosc"), rs.getInt("cena"), rs.getInt("rok_produkcji"), rs.getInt("nr_wlasciciela"), rs.getString("model"));
                samochodUzywanyList.add(samochodUzywany);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return samochodUzywanyList;
    }

    public void showSamochodUzywany(){

        ObservableList<SamochodUzywany> samochodUzywanyList = getSamochodUzywanyList();

        colSamochodUzywanyId.setCellValueFactory(new PropertyValueFactory<SamochodUzywany,Integer>("id_samochod_uzywany"));
        colSamochodUzywanyIdNaped.setCellValueFactory(new PropertyValueFactory<SamochodUzywany,Integer>("id_naped"));
        colSamochodUzywanyPrzebieg.setCellValueFactory(new PropertyValueFactory<SamochodUzywany,Integer>("przebieg"));
        colSamochodUzywanyMarka.setCellValueFactory(new PropertyValueFactory<SamochodUzywany,String>("marka"));
        colSamochodUzywanyBezwypadkowosc.setCellValueFactory(new PropertyValueFactory<SamochodUzywany,Boolean>("bezwypadkowosc"));
        colSamochodUzywanyCena .setCellValueFactory(new PropertyValueFactory<SamochodUzywany,Integer>("cena"));
        colSamochodUzywanyRokProdukcji.setCellValueFactory(new PropertyValueFactory<SamochodUzywany,Integer>("rok_produkcji"));
        colSamochodUzywanyNrWlasciciela.setCellValueFactory(new PropertyValueFactory<SamochodUzywany,Integer>("nr_wlasciciela"));
        colSamochodUzywanyModel.setCellValueFactory(new PropertyValueFactory<SamochodUzywany,String>("model"));

        SamochodUzywanyTable.setItems(samochodUzywanyList);
    }


    // -------------- Wyświetlanie Serwisant ---------------

    public ObservableList<Serwisant> getSerwisantList(){
        ObservableList<Serwisant> serwisantList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM projekt.serwisant ORDER BY id_serwisant";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Serwisant serwisant;
            while(rs.next()) {
                serwisant = new Serwisant(rs.getInt("id_Serwisant"), rs.getString("imie"), rs.getString("nazwisko"), rs.getString("telefon"), rs.getString("email"));
                serwisantList.add(serwisant);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return serwisantList;
    }

    public void showSerwisant(){

        ObservableList<Serwisant> serwisantList = getSerwisantList();

        //System.out.println("" + SerwisantList.get(0).getId_Serwisant());

        colSerwisantId.setCellValueFactory(new PropertyValueFactory<Serwisant,Integer>("id_Serwisant"));
        colSerwisantImie.setCellValueFactory(new PropertyValueFactory<Serwisant,String>("imie"));
        colSerwisantNazwisko.setCellValueFactory(new PropertyValueFactory<Serwisant,String>("nazwisko"));
        colSerwisantTelefon.setCellValueFactory(new PropertyValueFactory<Serwisant,String>("telefon"));
        colSerwisantEmail.setCellValueFactory(new PropertyValueFactory<Serwisant,String>("email"));

        SerwisantTable.setItems(serwisantList);
    }

    // -------------- Wyświetlanie Sprzedawca ---------------

    public ObservableList<Sprzedawca> getSprzedawcaList(){
        ObservableList<Sprzedawca> sprzedawcaList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM projekt.sprzedawca ORDER BY id_sprzedawca";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Sprzedawca sprzedawca;
            while(rs.next()) {
                sprzedawca = new Sprzedawca(rs.getInt("id_Sprzedawca"), rs.getString("imie"), rs.getString("nazwisko"), rs.getString("telefon"), rs.getString("email"));
                sprzedawcaList.add(sprzedawca);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return sprzedawcaList;
    }

    public void showSprzedawca(){

        ObservableList<Sprzedawca> sprzedawcaList = getSprzedawcaList();

        //System.out.println("" + SprzedawcaList.get(0).getId_Sprzedawca());

        colSprzedawcaId.setCellValueFactory(new PropertyValueFactory<Sprzedawca,Integer>("id_Sprzedawca"));
        colSprzedawcaImie.setCellValueFactory(new PropertyValueFactory<Sprzedawca,String>("imie"));
        colSprzedawcaNazwisko.setCellValueFactory(new PropertyValueFactory<Sprzedawca,String>("nazwisko"));
        colSprzedawcaTelefon.setCellValueFactory(new PropertyValueFactory<Sprzedawca,String>("telefon"));
        colSprzedawcaEmail.setCellValueFactory(new PropertyValueFactory<Sprzedawca,String>("email"));

        SprzedawcaTable.setItems(sprzedawcaList);
    }

    // -------------- Wyświetlanie Klient ---------------

    public ObservableList<UslugaSerwis> getUslugaSerwisList(){
        ObservableList<UslugaSerwis> uslugaSerwisList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM projekt.serwis_usluga";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            UslugaSerwis uslugaSerwis;
            while(rs.next()) {
                uslugaSerwis = new UslugaSerwis(rs.getInt("id_usluga"), rs.getString("nazwa"), rs.getFloat("czas_uslugi"), rs.getString("data"));
                uslugaSerwisList.add(uslugaSerwis);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return uslugaSerwisList;
    }

    public void showUslugaSerwis(){

        ObservableList<UslugaSerwis> uslugaSerwisList = getUslugaSerwisList();

        //System.out.println("" + UslugaSerwisList.get(0).getId_UslugaSerwis());

        colUslugaSerwisId.setCellValueFactory(new PropertyValueFactory<UslugaSerwis,Integer>("id_usluga"));
        colUslugaSerwisNazwa.setCellValueFactory(new PropertyValueFactory<UslugaSerwis,String>("nazwa"));
        colUslugaSerwisCzas.setCellValueFactory(new PropertyValueFactory<UslugaSerwis,Float>("czas_uslugi"));
        colUslugaSerwisData.setCellValueFactory(new PropertyValueFactory<UslugaSerwis,String>("data"));

        UslugaSerwisTable.setItems(uslugaSerwisList);
    }

    // -------------- Wyświetlanie Wyposazenie ---------------

    public ObservableList<Wyposazenie> getWyposazenieList(){
        ObservableList<Wyposazenie> wyposazenieList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM projekt.wyposazenie";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Wyposazenie wyposazenie;
            while(rs.next()) {
                wyposazenie = new Wyposazenie(rs.getInt("id_wyposazenie"), rs.getInt("id_naped"), rs.getString("wersja_wyposazenia"));
                wyposazenieList.add(wyposazenie);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return wyposazenieList;
    }

    public void showWyposazenie(){

        ObservableList<Wyposazenie> wyposazenieList = getWyposazenieList();

        //System.out.println("" + WyposazenieList.get(0).getId_Wyposazenie());

        colWyposazenieId.setCellValueFactory(new PropertyValueFactory<Wyposazenie,Integer>("id_wyposazenie"));
        colWyposazenieIdNaped.setCellValueFactory(new PropertyValueFactory<Wyposazenie,Integer>("id_naped"));
        colWyposazenieWersja.setCellValueFactory(new PropertyValueFactory<Wyposazenie,String>("wersja_wyposazenia"));

        WyposazenieTable.setItems(wyposazenieList);
    }



    // ------------------ Funkcje onclick dla poszczegĂłlnych przyciskĂłw ------------------

    public void NapedClick(ActionEvent event) throws IOException{
        showNaped();
    }

    public void KlientClick(ActionEvent event) throws IOException{
        showKlient();
    }

    public void SamochodNowyClick(ActionEvent event) throws IOException{
        showSamochodNowy();
    }

    public void SamochodSerwisClick(ActionEvent event) throws IOException{
        showSamochodSerwis();
    }

    public void SamochodUzywanyClick(ActionEvent event) throws IOException{
        showSamochodUzywany();
    }

    public void SerwisantClick(ActionEvent event) throws IOException{
        showSerwisant();
    }

    public void SprzedawcaClick(ActionEvent event) throws IOException{
        showSprzedawca();
    }

    public void UslugaSerwisClick(ActionEvent event) throws IOException{
        showUslugaSerwis();
    }

    public void WyposazenieClick(ActionEvent event) throws IOException{
        showWyposazenie();
    }

    public void ZamowienieNoweClick(ActionEvent event) throws IOException{
        showZamowienieNowe();
    }

    public void raport1Click(ActionEvent event) throws IOException{
        showRaport1();
    }

    public void raport2Click(ActionEvent event) throws IOException{
        showRaport2();
    }

    public void raport3Click(ActionEvent event) throws IOException{
        showRaport3();
    }

    public void switchToRaportScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("RaportScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToEditScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("EditScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToViewScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("ViewScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRaport1Scene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("./RaportScenes/Raport1Scene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRaport2Scene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("./RaportScenes/Raport2Scene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRaport3Scene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("./RaportScenes/Raport3Scene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToKlienciViewScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("./ViewScenes/KlienciView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSprzedawcyViewScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("./ViewScenes/SprzedawcyView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSerwisanciViewScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("./ViewScenes/SerwisanciView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSamSerwViewScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("./ViewScenes/SamSerwView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToUslSerwViewScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("./ViewScenes/UslSerwView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToNowZamViewScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("./ViewScenes/NowZamView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToUzywSamViewScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("./ViewScenes/UzywSamView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToWypViewScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("./ViewScenes/WypView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToNapedViewScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("./ViewScenes/NapedView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToNowSamViewScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("./ViewScenes/NowSamView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void switchToKlienciEditScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("./EditScenes/KlienciEdit.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSprzedawcyEditScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("./EditScenes/SprzedawcyEdit.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSerwisanciEditScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("./EditScenes/SerwisanciEdit.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSamSerwEditScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("./EditScenes/SamSerwEdit.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToUslSerwEditScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("./EditScenes/UslSerwEdit.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToNowZamEditScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("./EditScenes/NowSamEdit.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToUzywSamEditScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("./EditScenes/UzywSamEdit.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToWypEditScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("./EditScenes/WypEdit.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToNapedEditScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("./EditScenes/NapedEdit.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void switchToNowSamEditScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("./EditScenes/NowSamEdit.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    // ----------- Pola tekstowe dla wszyskich scen do edytowania ----------

    //Klient

    @FXML
    private TextField klientImieTF;

    @FXML
    private TextField klientNazwiskoTF;

    @FXML
    private TextField klientTelefonTF;

    @FXML
    private TextField klientEmailTF;

    //Sprzedawca

    @FXML
    private TextField sprzedawcaImieTF;

    @FXML
    private TextField sprzedawcaNazwiskoTF;

    @FXML
    private TextField sprzedawcaTelefonTF;

    @FXML
    private TextField sprzedawcaEmailTF;

    //Serwisant

    @FXML
    private TextField serwisantImieTF;

    @FXML
    private TextField serwisantNazwiskoTF;

    @FXML
    private TextField serwisantTelefonTF;

    @FXML
    private TextField serwisantEmailTF;

    //Naped

    @FXML
    private TextField napedKodTF;

    @FXML
    private TextField napedMocTF;

    @FXML
    private TextField napedSkrzyniaTF;

    @FXML
    private TextField napedPojemnoscTF;

    @FXML
    private TextField napedMomentTF;

    //Samochód nowy

    @FXML
    private TextField samNowIdWypTF;

    @FXML
    private TextField samNowModelTF;

    @FXML
    private TextField samNowMarkaTF;

    //Zamowienie samochód nowy

    @FXML
    private TextField zamNowIdKlientTF;

    @FXML
    private TextField zamNowIdSprzedawcaTF;

    @FXML
    private TextField zamNowDataTF;

    @FXML
    private TextField zamNowCenaTF;

    @FXML
    private TextField zamNowIdSamNowTF;

    //Samochód Serwis

    @FXML
    private TextField samSerwisNrRejTF;

    @FXML
    private TextField samSerwisMarkaTF;

    @FXML
    private TextField samSerwisModelTF;

    @FXML
    private TextField samSerwisIdKlientTF;

    @FXML
    private TextField samSerwisIdUslugaTF;

    //Usluga Serwisowa

    @FXML
    private TextField uslSerwNazwaTF;

    @FXML
    private TextField uslSerwCzasTF;

    @FXML
    private TextField uslSerwDataTF;

    @FXML
    private TextField uslSerwIdSerwisantTF;


    //Samochód używany

    @FXML
    private TextField uzywSamIdNapedTF;

    @FXML
    private TextField uzywSamPrzebiegTF;

    @FXML
    private TextField uzywSamMarkaTF;

    @FXML
    private TextField uzywSamModelTF;

    @FXML
    private TextField uzywSamBezwypTF;

    @FXML
    private TextField uzywSamCenaTF;

    @FXML
    private TextField uzywSamRokTF;

    @FXML
    private TextField uzywSamNrWlascTF;

    @FXML
    private TextField uzywSamIdSprzedawcaTF;

    @FXML
    private TextField uzywSamIdKlientTF;


    //Wyposażenie

    @FXML
    private TextField wypIdNapedTF;

    @FXML
    private TextField wypWersjaTF;



    private void executeQuery(String query) {

        Connection connection = getConnection();
        Statement st;
        try{
            st = connection.createStatement();
            st.executeUpdate(query);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }


    public void addKlient(ActionEvent event) throws IOException{

        Klient klient = new Klient(Klient.id, klientImieTF.getCharacters().toString(), klientNazwiskoTF.getCharacters().toString(), klientTelefonTF.getCharacters().toString(), klientEmailTF.getCharacters().toString() );

        String query = "INSERT INTO projekt.klient VALUES("  + klient.getId_klient()  + ",'" +  klient.getImie() + "'," + "'" + klient.getNazwisko() + "'," + "'" + klient.getTelefon() + "'," + "'" + klient.getEmail() + "');";
        System.out.println(query);
        //executeQuery(query);

        //Klient.id++;
    }



    public void addSerwisant(ActionEvent event) throws IOException{

        Serwisant serwisant = new Serwisant(Serwisant.id, serwisantImieTF.getCharacters().toString(), serwisantNazwiskoTF.getCharacters().toString(), serwisantTelefonTF.getCharacters().toString(), serwisantEmailTF.getCharacters().toString() );

        String query = "INSERT INTO projekt.serwisant VALUES(" + serwisant.getId_serwisant() + ",'"  +  serwisant.getImie() + "'," + "'" + serwisant.getNazwisko() + "'," + "'" + serwisant.getTelefon() + "'," + "'" + serwisant.getEmail() + "');";
        System.out.println(query);
        //executeQuery(query);

        //Serwisant.id++;

    }

    public void addSprzedawca(ActionEvent event) throws IOException{

        Sprzedawca sprzedawca = new Sprzedawca(Sprzedawca.id, sprzedawcaImieTF.getCharacters().toString(), sprzedawcaNazwiskoTF.getCharacters().toString(), sprzedawcaTelefonTF.getCharacters().toString(), sprzedawcaEmailTF.getCharacters().toString() );

        String query = "INSERT INTO projekt.sprzedawca VALUES(" + sprzedawca.getId_sprzedawca() + ",'"  +  sprzedawca.getImie() + "'," + "'" + sprzedawca.getNazwisko() + "'," + "'" + sprzedawca.getTelefon() + "'," + "'" + sprzedawca.getEmail() + "\');";
        System.out.println(query);
        //executeQuery(query);

        //Sprzedawca.id++;


    }

    public void addNaped(ActionEvent event) throws IOException{

        Naped naped = new Naped(Naped.id, napedKodTF.getCharacters().toString(), Integer.parseInt(napedMocTF.getCharacters().toString()) , napedSkrzyniaTF.getCharacters().toString(), Integer.parseInt(napedPojemnoscTF.getCharacters().toString()), Integer.parseInt(napedMomentTF.getCharacters().toString()) );

        String query = "INSERT INTO projekt.naped VALUES(" + naped.getId_naped() + ",'"  +  naped.getKod_silnika() + "'," +  naped.getMoc()   + ",'" + naped.getSkrzynia_biegow() + "'," + naped.getPojemnosc() + "," + naped.getMoment_obrotowy() + ");";
        System.out.println(query);
        //executeQuery(query);

        //Naped.id++;

    }

    public void addNowZam(ActionEvent event) throws IOException{

        ZamowienieNowe zamowienieNowe = new ZamowienieNowe(ZamowienieNowe.id, Integer.parseInt(zamNowIdKlientTF.getCharacters().toString()), Integer.parseInt(zamNowIdSprzedawcaTF.getCharacters().toString()) , zamNowDataTF.getCharacters().toString(), Integer.parseInt(zamNowCenaTF.getCharacters().toString()));

        String query = "INSERT INTO projekt.zamowienie_nowe VALUES(" +  zamowienieNowe.getId_zam_nowe() + "," + zamowienieNowe.getId_klient()  + "," +  zamowienieNowe.getId_sprzedawca()   + ",'" + zamowienieNowe.getData_realizacji() + "'," + zamowienieNowe.getCena() + ");";
        System.out.println(query);
        //executeQuery(query);

        //ZamowienieNowe.id++;


    }

    public void addNowSam(ActionEvent event) throws IOException{

        SamochodNowy samochodNowy = new SamochodNowy(SamochodNowy.id, Integer.parseInt(samNowIdWypTF.getCharacters().toString()), samNowModelTF.getCharacters().toString() , samNowMarkaTF.getCharacters().toString());

        String query = "INSERT INTO projekt.samochod_nowy VALUES(" +  samochodNowy.getId_samochod_nowy() + "," + samochodNowy.getId_wyposazenie()  + ",'" +  samochodNowy.getModel()   + "','" + samochodNowy.getMarka() + "');";
        System.out.println(query);
        //executeQuery(query);

        //SamochodNowy.id++;

    }

    public void addSamSerwis(ActionEvent event) throws IOException{

        SamochodSerwis samochodSerwis = new SamochodSerwis(samSerwisNrRejTF.getCharacters().toString(), samSerwisMarkaTF.getCharacters().toString() , samSerwisModelTF.getCharacters().toString());

        String query = "INSERT INTO projekt.samochod_serwis VALUES(" + "'" +  samochodSerwis.getNr_rej() + "','" + samochodSerwis.getMarka()  + "','" +  samochodSerwis.getModel() + "');";
        String query1 = "INSERT INTO projekt.klient_serwis VALUES(" +  Integer.parseInt(samSerwisIdUslugaTF.getCharacters().toString()) + ",'" + samochodSerwis.getNr_rej()  + "'," +  Integer.parseInt(samSerwisIdKlientTF.getCharacters().toString()) + ");";

        System.out.println(query);
        System.out.println(query1);

        //executeQuery(query);
        //executeQuery(query1);

        //SamochodSerwis.id++;

    }

    public void addUslSerw(ActionEvent event) throws IOException{

        UslugaSerwis uslugaSerwis = new UslugaSerwis(UslugaSerwis.id, uslSerwNazwaTF.getCharacters().toString() , Float.parseFloat(uslSerwCzasTF.getCharacters().toString()), uslSerwDataTF.getCharacters().toString() );

        String query = "INSERT INTO projekt.serwis_usluga VALUES("  +  uslugaSerwis.getId_usluga() + ",'" + uslugaSerwis.getNazwa()  + "'," +  uslugaSerwis.getCzas_uslugi() + ",'" + uslugaSerwis.getData() + "');";
        String query1 = "INSERT INTO projekt.serwisant_serwis VALUES(" +  Integer.parseInt(uslSerwIdSerwisantTF.getCharacters().toString()) + "," + uslugaSerwis.getId_usluga()  + ");";

        System.out.println(query);
        System.out.println(query1);

        //executeQuery(query);
        //executeQuery(query1);

        //UslugaSerwis.id++;

    }

    public void addWyposazenie(ActionEvent event) throws IOException{

        Wyposazenie wyposazenie = new Wyposazenie(Wyposazenie.id, Integer.parseInt(wypIdNapedTF.getCharacters().toString()) ,  wypWersjaTF.getCharacters().toString() );

        String query = "INSERT INTO projekt.wyposazenie VALUES("  +  wyposazenie.getId_wyposazenie() + "," + wyposazenie.getId_naped()  + ",'" +  wyposazenie.getWersja_wyposazenia() + "');";

        System.out.println(query);

        //executeQuery(query);

        //Wyposazenie.id++;

    }

    public void addSamUzyw(ActionEvent event) throws IOException{

        SamochodUzywany samochodUzywany = new SamochodUzywany(SamochodUzywany.id, Integer.parseInt(uzywSamIdNapedTF.getCharacters().toString()) , Integer.parseInt(uzywSamPrzebiegTF.getCharacters().toString()), uzywSamMarkaTF.getCharacters().toString(), Boolean.parseBoolean(uzywSamBezwypTF.getCharacters().toString()), Integer.parseInt(uzywSamCenaTF.getCharacters().toString()), Integer.parseInt(uzywSamRokTF.getCharacters().toString()), Integer.parseInt(uzywSamNrWlascTF.getCharacters().toString()), uzywSamModelTF.getCharacters().toString()  );

        String query = "INSERT INTO projekt.samochod_uzywany VALUES("  +  samochodUzywany.getId_samochod_uzywany() + "," + samochodUzywany.getId_naped()  + "," +  samochodUzywany.getPrzebieg() + ",'" + samochodUzywany.getMarka() + "','" + samochodUzywany.getBezwypadkowosc() + "'," + samochodUzywany.getCena() + "," + samochodUzywany.getRok_produkcji() + ',' + samochodUzywany.getNr_wlasciciela() + ",'" + samochodUzywany.getModel() + "');";
        String query1 = "INSERT INTO projekt.zamowienie_uzywane VALUES(" +  samochodUzywany.getId_samochod_uzywany() + "," + Integer.parseInt(uzywSamIdSprzedawcaTF.getCharacters().toString())  + "," + Integer.parseInt(uzywSamIdKlientTF.getCharacters().toString()) +  ");";

        System.out.println(query);
        System.out.println(query1);

        //executeQuery(query);
        //executeQuery(query1);

        //SamochodUzywany.id++;

    }

    // ------------------


}
