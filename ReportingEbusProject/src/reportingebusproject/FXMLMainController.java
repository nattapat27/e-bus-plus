/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportingebusproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class FXMLMainController implements Initializable {

    @FXML
    private ComboBox<String> problemTypes;
    
    @FXML
    private void handleHomeAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("forms/FXMLHome.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Home");
        stage.setResizable(false);
        ((Node)(event.getSource())).getScene().getWindow().hide();
        stage.show();
        System.out.println("Home");
    }

    @FXML
    private void handleProblemAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("forms/FXMLProblem.fxml"));
        Parent root = (Parent) fxmlloader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Problem");
        stage.setResizable(false);
        ((Node)(event.getSource())).getScene().getWindow().hide();
        stage.show();
    }

    @FXML
    private void handleContactUsAction(ActionEvent event) {

    }

    @FXML
    private void handleAdminAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("forms/FXMLAdmin.fxml"));
        Parent root = (Parent) fxmlloader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Admin");
        stage.setResizable(false);
        ((Node)(event.getSource())).getScene().getWindow().hide();
        stage.show();

    }
    
    @FXML
    private void handleLogoutAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("forms/FXMLHome.fxml"));
        Parent root = (Parent) fxmlloader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        ((Node)(event.getSource())).getScene().getWindow().hide();
        stage.show();

    }
    
    @FXML
    private void handleReportAction(ActionEvent event){
        System.out.println(problemTypes.getValue());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}
