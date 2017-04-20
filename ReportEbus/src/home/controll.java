package home;

import ebus.controller.Problem;
import ebus.model.Connect;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import addProblem.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class controll implements Initializable {
    @FXML
    private Button homeButton;
    @FXML
    private Button reportButton;
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
    @FXML
    private Button adminButton;
    
    public void handleHomeAction(ActionEvent event)throws IOException{
        System.out.println("Home");
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("HOME.fxml"));
        Parent root = (Parent) fxmlloader.load();
 
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        ((Node)(event.getSource())).getScene().getWindow().hide();
        stage.show();
    }
    
    public void handleReportAction(ActionEvent event)throws IOException{
        System.out.println(getClass().getResource("/addProblem/addProblemFXML.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/addProblem/addProblemFXML.fxml"));
 
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        ((Node)(event.getSource())).getScene().getWindow().hide();
        stage.show();
    }
    
    public void handleAdminAction(ActionEvent event)throws IOException{
        System.out.println(getClass().getResource("/admin/Admin.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/admin/Admin.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        ((Node)(event.getSource())).getScene().getWindow().hide();
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connect.setProblem();
        ObservableList<Problem> allProblem = Connect.getAllProblem();
        problemId.setCellValueFactory(new PropertyValueFactory<>("id"));
        problemStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        problemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        problemType.setCellValueFactory(new PropertyValueFactory<>("type"));
        action.setCellValueFactory(new PropertyValueFactory<>("detail"));        
        table.getItems().setAll(allProblem);
        
    }    
    
}
