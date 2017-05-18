/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;

import ebus.model.Connect;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Pat
 */
public class RegisterController implements Initializable {

    @FXML
    private Button Enter;
    @FXML
    private TextField Name;
    @FXML
    private TextField Surname;
    @FXML
    private PasswordField password;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextField userId;
    @FXML
    private Text alert;
    @FXML
    private Text alertdata;

    @FXML
    private void handleEnterAction(ActionEvent event) throws IOException{
        System.out.println("Action");
        String userId = this.userId.getText();
        String password = this.password.getText();
        String email = this.email.getText();
        String name = this.Name.getText();
        String surname = this.Surname.getText();
        String phone = this.phone.getText();
        System.out.println("<"+userId+","+password+","+email+","+name+","+surname+","+phone+">");
        if((!userId.equals(""))&&(!password.equals(""))&&(!email.equals(""))&&(!name.equals(""))&&(!surname.equals(""))&&(!phone.equals(""))){
            if(Connect.ckUser(userId)){
                Connect.addUser(userId, password, email, name, surname, phone);
            }
            else{
                alert.setVisible(true);
                alertdata.setVisible(false);
            }
        }
        else{
            alert.setVisible(false);
            alertdata.setVisible(true);
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        alert.setVisible(false);
        alertdata.setVisible(false);
    }    
    
}
