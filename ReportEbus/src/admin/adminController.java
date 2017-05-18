/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import ebus.model.Problem;
import ebus.model.Connect;
import home.HomeController;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
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

    @FXML
    public void handleLogoutAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/home/Home.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        ((Node) (event.getSource())).getScene().getWindow().hide();
        stage.show();
    }
    
    @FXML
    private void handleGraphAction(ActionEvent event) {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/Graph/Graph.fxml"));
        Parent root;
        try {
            root = (Parent) fxmlloader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> statuschoice = FXCollections.observableArrayList(
                new String("รอดำเนินการ"),
                new String("ได้รับเรื่องแล้ว"),
                new String("กำลังดำเนินการ"),
                new String("เสร็จสิ้น")      
        );
        
        Connect.setProblem();
        System.out.println(">>>" + new PropertyValueFactory("status"));
        //ObservableList<Problem> allProblem = Connect.getAllProblem();
        ObservableList<Problem> allProblem = FXCollections.observableArrayList(
                new Problem("ปัญหา1", "", "รอดำเนินการ", "ปัญหารถ", new Date()),
                new Problem("ปัญหา2", "", "กำลังดำเนินการ", "ปัญหาคนขับ", new Date()),
                new Problem("ปัญหา3", "", "ดำเนินการแล้ว", "ปัญหาแอพ", new Date()),
                new Problem("ปัญหา4", "", "รอดำเนินการ", "อื่น ๆ", new Date()),
                new Problem("ปัญหา5", "", "ดำเนินการแล้ว", "ปัญหาคนขับ", new Date())
        );

        problemId.setCellValueFactory(new PropertyValueFactory<>("id"));
        problemStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        problemStatus.setCellFactory(ComboBoxTableCell.forTableColumn(statuschoice));
        problemStatus.setOnEditCommit(new EventHandler<CellEditEvent<Problem, String>>(){
            @Override
            public void handle(CellEditEvent<Problem, String> t){
               ((Problem) t.getTableView().getItems().get(t.getTablePosition().getRow())).setStatus(t.getNewValue());
            }
        });
        problemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        problemType.setCellValueFactory(new PropertyValueFactory<>("type"));
        action.setCellValueFactory(new PropertyValueFactory<>("detail"));
        table.getItems().setAll(allProblem);
    }

}
