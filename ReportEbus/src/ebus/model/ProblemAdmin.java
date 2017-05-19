package ebus.model;

import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ProblemAdmin {
    private String topic;
    private ComboBox<String> status;
    private int id;
    private String detail;
    private String type;
    private Date date;

    public ProblemAdmin(String topic, int status, int id, String detail, String type, Date date) {
        this.topic = topic;
        ObservableList<String> options = FXCollections.observableArrayList(
                "รอดำเนินการ", 
                "ได้รับเรื่องแล้ว", 
                "กำลังดำเนินการ", 
                "เสร็จสิ้น"
        );
        this.status.setItems(options);
        this.status.setValue(options.get(status));
        this.id = id;
        this.detail = detail;
        this.type = type;
        this.date = date;
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
