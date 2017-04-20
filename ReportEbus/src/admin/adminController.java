/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import ebus.controller.Problem;
import ebus.model.Connect;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView<Problem> table;
    @FXML
    private TableColumn<Problem, String> problemId;
    @FXML
    private TableColumn<Problem, String> problemStatus;
    @FXML
    private TableColumn<Problem, String> problemName;
    @FXML
    private TableColumn<Problem, String> problemType;
    @FXML
    private TableColumn<Problem, String> action;
    
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
        Connect.setProblem();
        System.out.println(">>>"+new PropertyValueFactory("status"));
        ObservableList<Problem> allProblem = Connect.getAllProblem();
        problemId.setCellValueFactory(new PropertyValueFactory<>("id"));
        problemStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        problemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        problemType.setCellValueFactory(new PropertyValueFactory<>("type"));
        action.setCellValueFactory(new PropertyValueFactory<>("detail"));        
        table.getItems().setAll(allProblem);
    }    
    
}
