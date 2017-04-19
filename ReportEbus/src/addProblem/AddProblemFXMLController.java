/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addProblem;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pat
 */
public class AddProblemFXMLController implements Initializable {

    @FXML
    private Button homeButton;
    @FXML
    private Button reportButton;
    @FXML
    private Button adminButton;
    @FXML
    private TextField probleamName;
    @FXML
    private TextArea problemDescription;
    @FXML
    private DatePicker dateSet;
    @FXML
    private Button Report;
    @FXML
    private ComboBox<String> typesBox;
    @FXML
    private CheckBox follow;
    @FXML
    private TextField mail;
    @FXML
    private Text textMail;
    @FXML
    private Text textAlert;
    @FXML
    public void handleHomeAction(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/home/HOME.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        ((Node)(event.getSource())).getScene().getWindow().hide();
        stage.show();
    }
    @FXML
    public void handleReportAction(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/addProblem/addProblemFXML.fxml"));
 
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        ((Node)(event.getSource())).getScene().getWindow().hide();
        stage.show();
    }
    @FXML
    public void handleAdminAction(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/admin/Admin.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        ((Node)(event.getSource())).getScene().getWindow().hide();
        stage.show();
    }
    @FXML
    public void handleSentAction(ActionEvent event)throws IOException{
        //System.out.println((String)typesBox.getValue());
        if(probleamName.getText()!=null && problemDescription.getText()!=null && dateSet.getValue()!=null
            && (String)typesBox.getValue()!=null ){
            
            String name = probleamName.getText();
            probleamName.clear();
            
            String detail = problemDescription.getText();
            problemDescription.clear();
            
            LocalDate localDate = dateSet.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date date = Date.from(instant);
            dateSet.getEditor().clear();
            
            String type = (String)typesBox.getValue();
            typesBox.getSelectionModel().clearSelection();
            
            String email = null;
            if(follow.isSelected()){    
                email = mail.getText();
                follow.setSelected(false);
                mail.setVisible(false);
                textMail.setVisible(false);
            }
            mail.clear();
            
        }
        else
            textAlert.setVisible(true);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        textAlert.setVisible(false);
        mail.setVisible(false);
        textMail.setVisible(false);
        typesBox.getItems().removeAll(typesBox.getItems());
        typesBox.getItems().addAll("ปัญหารถ", "ปัญหาคนขับ", "ปัญหาแอพ", "อื่นๆ");
        //typesBox.getSelectionModel().select("Option B");
    }    

    @FXML
    private void handleFollowAction(ActionEvent event) {
        System.out.println(follow.isSelected());
        if(follow.isSelected()){
            mail.setVisible(true);
            textMail.setVisible(true);
        }
        else{
            mail.setVisible(false);
            textMail.setVisible(false);
        }
    }
    
}
