package home;

import PopupUser.PopupUserController;
import ebus.model.Problem;
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
import ebus.model.ProblemImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableRow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HomeController implements Initializable {

    @FXML
    private Button homeButton;
    @FXML
    private Button reportButton;
    @FXML
    private TableView table;

    /*
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
     */
    @FXML
    private Button logoutButton;

    @FXML
    public void handleHomeAction(ActionEvent event) throws IOException {
        System.out.println("Home");
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Home.fxml"));
        Parent root = (Parent) fxmlloader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        ((Node) (event.getSource())).getScene().getWindow().hide();
        stage.show();
    }

    @FXML
    public void handleReportAction(ActionEvent event) throws IOException {
        System.out.println(getClass().getResource("/addProblem/addProblemFXML.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/addProblem/addProblemFXML.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        ((Node) (event.getSource())).getScene().getWindow().hide();
        stage.show();
    }

    @FXML
    private void handleLogoutAction(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/login/Login.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        ((Node) (event.getSource())).getScene().getWindow().hide();
        stage.show();
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Connect.setProblem();
        TableColumn problemNoCol = new TableColumn<>("No.");
        problemNoCol.setCellValueFactory(new PropertyValueFactory<>("problemNo"));
        problemNoCol.setPrefWidth(45.0);
        table.getColumns().add(problemNoCol);

        TableColumn problemStatusCol = new TableColumn<>("Status");
        problemStatusCol.setCellValueFactory(new PropertyValueFactory<>("problemStatus"));
        problemStatusCol.setPrefWidth(75.0);
        table.getColumns().add(problemStatusCol);

        TableColumn problemTopicCol = new TableColumn<>("Topic");
        problemTopicCol.setCellValueFactory(new PropertyValueFactory<>("problemTopic"));
        problemTopicCol.setPrefWidth(349.0);
        table.getColumns().add(problemTopicCol);

        TableColumn problemTypeCol = new TableColumn<>("Type");
        problemTypeCol.setCellValueFactory(new PropertyValueFactory<>("problemType"));
        problemTypeCol.setPrefWidth(100.0);
        table.getColumns().add(problemTypeCol);

        TableColumn problemImageVoteCol = new TableColumn<>("Vote");
        problemImageVoteCol.setCellValueFactory(new PropertyValueFactory<>("problemButtonVote"));
        problemImageVoteCol.setPrefWidth(77.0);
        table.getColumns().add(problemImageVoteCol);

        table.setItems(Connect.getAllProblem());

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
                    PopupUserController.setProblem((ProblemImage) row.getItem());
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
    }
}
