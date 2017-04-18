package view;


import ebus.controller.Problem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ebus.model.*;
import javafx.collections.ObservableList;
/**
 *
 * @author User
 */
public class ReportingEbusProject extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Connect.setProblem();
        Parent root = FXMLLoader.load(getClass().getResource("FXML/HOME.fxml"));
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

