/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author giuseppe.depietro
 */
public class MainApp extends Application{
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Calcolatrice");

        inizializza();
        
    }
    
     public static void main(String[] args) {
        launch(args);
    }

    private void inizializza() {
        
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("mainGui.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            
            
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
