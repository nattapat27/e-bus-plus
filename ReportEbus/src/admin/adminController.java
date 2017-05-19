/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import PopupUser.PopupUserController;
import ebus.model.Connect;
import ebus.model.ProblemAdmin;
import ebus.model.ProblemImage;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    private TableView<ProblemAdmin> table;
    /*@FXML
    private TableColumn<ProblemAdmin, String> problemId;
    @FXML
    private TableColumn<ProblemAdmin, String> problemStatus;
    @FXML
    private TableColumn<ProblemAdmin, String> problemName;
    @FXML
    private TableColumn<ProblemAdmin, String> problemType;
    @FXML
    private TableColumn<ProblemAdmin, String> action;
    */
    @FXML
    public void handleLogoutAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/login/Login.fxml"));

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
        Connect.setProblemAdmin();
        ObservableList<ProblemAdmin> allProblemAdmin = Connect.getAllProblemAdmin();
        
        TableColumn problemNoCol = new TableColumn<>("No.");
        problemNoCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        problemNoCol.setPrefWidth(45.0);
        table.getColumns().add(problemNoCol);

        TableColumn problemStatusCol = new TableColumn<>("Status");
        problemStatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        problemStatusCol.setPrefWidth(125.0);
        table.getColumns().add(problemStatusCol);

        TableColumn problemTopicCol = new TableColumn<>("Topic");
        problemTopicCol.setCellValueFactory(new PropertyValueFactory<>("topic"));
        problemTopicCol.setPrefWidth(349.0);
        table.getColumns().add(problemTopicCol);

        TableColumn problemTypeCol = new TableColumn<>("Type");
        problemTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        problemTypeCol.setPrefWidth(100.0);
        table.getColumns().add(problemTypeCol);

        TableColumn problemImageVoteCol = new TableColumn<>("Date");
        problemImageVoteCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        problemImageVoteCol.setPrefWidth(77.0);
        table.getColumns().add(problemImageVoteCol);

        table.setItems(Connect.getAllProblemAdmin());
        
        table.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (t.getClickCount() == 2 && t.isPrimaryButtonDown()) {
                    Node node = ((Node) t.getTarget()).getParent();
                    TableRow row;
                    if (node instanceof TableRow) {
                        row = (TableRow) node;
                    } else {
                        // clicking on text part
                        row = (TableRow) node.getParent();
                    }
                    ProblemAdmin pa = (ProblemAdmin) row.getItem();
                    PopupUserController.setProblem(new ProblemImage(""+pa.getId(), "", pa.getTopic(), pa.getType(), null));
                    PopupUserController.getProblem().setProblemDate(pa.getDate());
                    PopupUserController.getProblem().setProblemDetail(pa.getDetail());
                    FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/PopupUser/PopupUser.fxml"));
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
            }
        });
        /*Connect.setProblem();
        ObservableList<ProblemImage> allProblem = FXCollections.observableArrayList(Connect.getAllProblem());

        problemId.setCellValueFactory(new PropertyValueFactory<>("id"));
        problemStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        problemStatus.setCellFactory(ComboBoxTableCell.forTableColumn(statuschoice));
        problemStatus.setOnEditCommit(new EventHandler<CellEditEvent<ProblemImage, String>>(){
        @Override
        public void handle(CellEditEvent<Problem, String> t){
        ((Problem) t.getTableView().getItems().get(t.getTablePosition().getRow())).setStatus(t.getNewValue());
        }
        });
        problemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        problemType.setCellValueFactory(new PropertyValueFactory<>("type"));
        action.setCellValueFactory(new PropertyValueFactory<>("detail"));
        table.getItems().setAll(allProblem);*/
    }

}
