package PopupUser;

import addProblem.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RunPopupUser extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("PopupUser.fxml"));
        
        Scene scene = new Scene(root,400,300);
        
        stage.setTitle("Report e-bus+");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        System.out.println("hello");
        launch(args);
    }
    
}
