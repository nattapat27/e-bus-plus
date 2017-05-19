package ebus.model;

import java.io.IOException;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class ProblemAdmin {
    private String topic;
    private ComboBox<String> status = new ComboBox<>();
    private int id;
    private String detail;
    private String type;
    private Date date;
    private String word;
    private String own;
    private int numOfVote;

    public int getNumOfVote() {
        return numOfVote;
    }

    public void setNumOfVote(int numOfVote) {
        this.numOfVote = numOfVote;
    }
    

    public void setOwn(String own) {
        this.own = own;
    }

    public String getOwn() {
        return own;
    }
    public ProblemAdmin(String topic, int status, int id, String detail, String type, Date date,String own) {
        this.topic = topic;
        word = "ปัญหา "+topic+"\nได้ ";
        ObservableList<String> options = FXCollections.observableArrayList( 
                "ได้รับเรื่องแล้ว", 
                "รอดำเนินการ",
                "กำลังดำเนินการ", 
                "เสร็จสิ้น"
        );
        for(int i=0;i<options.size();i++){
            this.status.getItems().add(options.get(i));
        }
//        this.status.setItems(options);
        this.status.setValue(options.get(status));
        this.status.setOnAction((event) -> {
            System.out.println("CheckBox Action"+this.status.getValue());
            word+=this.status.getValue();
            int problemId = Connect.positionProblem(this.topic);
            String userVote[] = Connect.getUserVoteProblem(problemId);
            try{
                SendFileEmail.send(userVote, "ผลการติดตามปัญหา", word);
            }catch(IOException e){
                e.printStackTrace();
            }
            Connect.changeStatus(this.status.getSelectionModel().getSelectedIndex()+1, problemId);
            if(this.status.getValue().equals("เสร็จสิ้น"))
                Connect.deleteProblem(problemId);
            /*
            sent email
            change db
            if(ok)
            delete db
            */
        });
        this.id = id;
        this.detail = detail;
        this.type = type;
        this.date = date;
        this.own = own;
    }

    
    
    public String getTopic() {
        return topic;
    }

    public ComboBox<String> getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public String getDetail() {
        return detail;
    }

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setStatus(ComboBox<String> status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
