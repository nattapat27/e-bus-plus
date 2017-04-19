/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class adminController implements Initializable {

    @FXML
    private Button graphButtton;
    @FXML
    private Button logoutButton;
    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> problemId;
    @FXML
    private TableColumn<?, ?> problemStatus;
    @FXML
    private TableColumn<?, ?> problemName;
    @FXML
    private TableColumn<?, ?> problemType;
    @FXML
    private TableColumn<?, ?> action;
    
    public void handleLogoutAction(ActionEvent event)throws IOException{
        System.out.println(getClass().getResource("/home/HOME.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/home/HOME.fxml"));
 
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        ((Node)(event.getSource())).getScene().getWindow().hide();
        stage.show();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
