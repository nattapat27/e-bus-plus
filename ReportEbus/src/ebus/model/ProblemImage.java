package ebus.model;

import java.sql.Date;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProblemImage {
    private String problemNo;
    private String problemStatus;
    private String problemTopic;
    private String problemType;
    private String problemDetail;
    private Button problemButtonVote;
    private Date problemDate;

    public ProblemImage(String problemNo, String problemStatus, String problemTopic, String problemType, Button problemButtonVote) {
        this.problemNo = problemNo;
        this.problemStatus = problemStatus;
        this.problemTopic = problemTopic;
        this.problemType = problemType;
        if(problemButtonVote!=null){
            this.problemButtonVote = problemButtonVote;
            this.problemButtonVote.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/IconLike.png"))));
            this.problemButtonVote.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    int problemIdAction = Connect.positionProblem(getProblemTopic());
                    if(Connect.ckVote(problemIdAction, Connect.getUser())){
                        //Connect.Vote();
                    }
                }
            });
        }
    }

    public String getProblemNo() {
        return problemNo;
    }

    public void setProblemNo(String problemNo) {
        this.problemNo = problemNo;
    }

    public String getProblemStatus() {
        return problemStatus;
    }

    public void setProblemStatus(String problemStatus) {
        this.problemStatus = problemStatus;
    }

    public String getProblemTopic() {
        return problemTopic;
    }

    public void setProblemTopic(String problemTopic) {
        this.problemTopic = problemTopic;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public Button getProblemButtonVote() {
        return problemButtonVote;
    }

    public void setProblemButtonVote(Button problemButtonVote) {
        this.problemButtonVote = problemButtonVote;
    }

    public String getProblemDetail() {
        return problemDetail;
    }

    public void setProblemDetail(String problemDetail) {
        this.problemDetail = problemDetail;
    }

    public Date getProblemDate() {
        return problemDate;
    }

    public void setProblemDate(Date problemDate) {
        this.problemDate = problemDate;
    }
    
    
}
