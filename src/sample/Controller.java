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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.DBClasses.*;

import java.sql.*;

import java.io.IOException;

public class Controller {

    private Parent root;
    private Scene scene;
    private Stage stage;

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
    private TableColumn<SamochodSerwis,Integer> colSamochodSerwisNrRej ;

    @FXML
    private TableColumn<SamochodSerwis,String> colSamochodSerwisModel;

    @FXML
    private TableColumn<SamochodSerwis,String> colSamochodSerwisMarka;


    // SamochodUzywany  --------

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
    private TableColumn<UslugaSerwis,Integer> colUslugaSerwisId;

    @FXML
    private TableColumn<UslugaSerwis,String> colUslugaSerwisNazwa;

    @FXML
    private TableColumn<UslugaSerwis,Float> colUslugaSerwisCzas;

    @FXML
    private TableColumn<UslugaSerwis,String> colUslugaSerwisData;


    // Wyposazenie --------

    @FXML
    private TableColumn<Wyposazenie,Integer> colWyposazenieId;

    @FXML
    private TableColumn<Wyposazenie,Integer> colWyposazenieIdNaped;

    @FXML
    private TableColumn<Wyposazenie,String> colWyposazenieWersja;

    // ZamowienieNowe --------

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

    // -------------- Wyświetlanie Klient ---------------

    public ObservableList<Klient> getKlientList(){
        ObservableList<Klient> klientList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM projekt.klient";
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

    public ObservableList<SamochodNowy> getSamochodNowyList(){
        ObservableList<SamochodNowy> SamochodNowyList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM projekt.samochod_nowy";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            SamochodNowy SamochodNowy;
            while(rs.next()) {
                SamochodNowy = new SamochodNowy(rs.getInt("id_samochod_nowy"), rs.getInt("id_wyposazenie"), rs.getString("model"), rs.getString("marka"));
                SamochodNowyList.add(SamochodNowy);
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
        //showSamochodSerwis();
    }

    public void SamochodUzywanyClick(ActionEvent event) throws IOException{
        //showSamochodUzywany();
    }

    public void SerwisantClick(ActionEvent event) throws IOException{
        //showSerwisant();
    }

    public void SprzedawcaClick(ActionEvent event) throws IOException{
        //showSprzedawca();
    }

    public void UslugaSerwisClick(ActionEvent event) throws IOException{
        //showUslugaSerwis();
    }

    public void WyposazenieClick(ActionEvent event) throws IOException{
        //showWyposazenie();
    }

    public void ZamowienieNoweClick(ActionEvent event) throws IOException{
        //showZamowienieNowe();
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
        root = FXMLLoader.load(getClass().getResource("./ViewScenes/NowSamView.fxml"));
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

    // ------------------


}
