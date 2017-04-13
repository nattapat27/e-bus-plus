/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportingebusproject;

import ebus.controller.Problem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ebus.model.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
/**
 *
 * @author User
 */
public class ReportingEbusProject extends Application {
    
    @FXML private TableColumn proId;
    
    @Override
    public void start(Stage stage) throws Exception {
        Connect.setProblem();
        Parent root = FXMLLoader.load(getClass().getResource("forms/FXMLHome.fxml"));
        ObservableList<Problem> allProblem = Connect.getAllProblem();
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
