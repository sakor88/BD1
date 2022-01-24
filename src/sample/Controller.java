package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {


    public void switchToRaportScene(ActionEvent event) throws IOException{
        Stage stage;
        Scene scene;
        Parent root = FXMLLoader.load(getClass().getResource("RaportScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToEditScene(ActionEvent event) throws IOException{
        Stage stage;
        Scene scene;
        Parent root = FXMLLoader.load(getClass().getResource("EditScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToViewScene(ActionEvent event) throws IOException{
        Stage stage;
        Scene scene;
        Parent root = FXMLLoader.load(getClass().getResource("ViewScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainScene(ActionEvent event) throws IOException{
        Stage stage;
        Scene scene;
        Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRaport1Scene(ActionEvent event) throws IOException{
        Stage stage;
        Scene scene;
        Parent root = FXMLLoader.load(getClass().getResource("./RaportScenes/Raport1Scene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRaport2Scene(ActionEvent event) throws IOException{
        Stage stage;
        Scene scene;
        Parent root = FXMLLoader.load(getClass().getResource("./RaportScenes/Raport2Scene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRaport3Scene(ActionEvent event) throws IOException{
        Stage stage;
        Scene scene;
        Parent root = FXMLLoader.load(getClass().getResource("./RaportScenes/Raport3Scene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
