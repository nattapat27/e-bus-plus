package ebusController;

import ebus.controller.Problem;
import ebus.model.Connect;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
