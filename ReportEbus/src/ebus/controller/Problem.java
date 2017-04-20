package ebus.controller;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
public class Problem {
    private final SimpleStringProperty  name,detail,status,type;
    private Date date;
    public Problem(){
        this(null,null,null,null,null);
    }
    public Problem(String name,String detail,String status,String type,Date date) {
        this.name = new SimpleStringProperty(name);
        this.detail = new SimpleStringProperty(detail);
        this.status = new SimpleStringProperty(status);
        this.type = new SimpleStringProperty(type);
        this.date = date;
    }
    public String getName() {
        return name.get();
    }
    public String getDetail() {
        return detail.get();
    }
    public String getStatus() {
        return status.get();
    }
    public String getType() {
        return type.get();
    }
    public Date getDate() {
        return date;
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public void setDetail(String detail) {
        this.detail.set(detail);
    }
    public void setStatus(String status) {
        this.status.set(status);
    }
    public void setType(String type) {
        this.type.set(type);
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String toString() {
        return "name=" + name.get() + "\ndetail=" + detail.get() + "\nstatus=" + status.get() + "\ntype=" + type.get() + "\ndate=" + date ;
    }
}
