package ebus.model;

import javafx.scene.image.ImageView;

public class ProblemImage {
    private String problemNo;
    private String problemStatus;
    private String problemTopic;
    private String problemType;
    private ImageView problemImageVote;

    public ProblemImage(String problemNo, String problemStatus, String problemTopic, String problemType, ImageView problemImageVote) {
        this.problemNo = problemNo;
        this.problemStatus = problemStatus;
        this.problemTopic = problemTopic;
        this.problemType = problemType;
        this.problemImageVote = problemImageVote;
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

    public ImageView getProblemImageVote() {
        return problemImageVote;
    }

    public void setProblemImageVote(ImageView problemImageVote) {
        this.problemImageVote = problemImageVote;
    }
    
    
}
