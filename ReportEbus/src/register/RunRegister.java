package register;

import home.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RunRegister extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        
        Scene scene = new Scene(root,800,600);
        
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        System.out.println("hello");
        launch(args);
    }
    
}
