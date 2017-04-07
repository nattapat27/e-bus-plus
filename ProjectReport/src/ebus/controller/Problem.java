package ebus.controller;
import java.util.Date;
public class Problem {
    private String name,detail,status,type;
    private Date date;
    public String getName() {
        return name;
    }
    public String getDetail() {
        return detail;
    }
    public String getStatus() {
        return status;
    }
    public String getType() {
        return type;
    }
    public Date getDate() {
        return date;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String toString() {
        return "name=" + name + "\ndetail=" + detail + "\nstatus=" + status + "\ntype=" + type + "\ndate=" + date ;
    }
}
