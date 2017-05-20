/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import ebus.model.Login;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class LoginController implements Initializable {

    @FXML
    private TextField user;
    @FXML
    private TextField password;
    @FXML
    private Button loginButtton;
    @FXML
    private Button registerButton;
    @FXML
    private Text alretText;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        alretText.setVisible(false);
    }    

    @FXML
    private void handleLoginAction(ActionEvent event) throws IOException{
        String username = user.getText();
        String pass = password.getText();
        password.clear();
        int ck = Login.cheak(username, pass);
        if(ck==0){//wrong
            alretText.setVisible(true);
        }
        else if(ck<0){//admin
            Parent root = FXMLLoader.load(getClass().getResource("/admin/Admin.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Report e-bus+");
            stage.getIcons().add(new Image("/images/ProblemImage.png"));
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            ((Node)(event.getSource())).getScene().getWindow().hide();
            stage.show();
        }
        else{//user
            Parent root = FXMLLoader.load(getClass().getResource("/home/Home.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Report e-bus+");
            stage.getIcons().add(new Image("/images/ProblemImage.png"));
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            ((Node)(event.getSource())).getScene().getWindow().hide();
            stage.show();
        }
    }

    @FXML
    private void handleRegisterAction(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/register/Register.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Report e-bus+");
        stage.getIcons().add(new Image("/images/ProblemImage.png"));
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        ((Node)(event.getSource())).getScene().getWindow().hide();
        stage.show();
    }

    
}
