package PopupUser;

import ebus.model.Connect;
import ebus.model.ProblemImage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
public class PopupUserController implements Initializable {
    private static ProblemImage problem;

    public static ProblemImage getProblem() {
        return problem;
    }

    public static void setProblem(ProblemImage problem) {
        PopupUserController.problem = problem;
    }
    @FXML
    private TextField detail;
    @FXML
    private TextField topic;
    @FXML
    private TextField date;
    @FXML
    private TextField user;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        detail.setText(problem.getProblemDetail());
        detail.setDisable(true);
        topic.setText(problem.getProblemTopic());
        topic.setDisable(true);
        date.setText(problem.getProblemDate().toString());
        date.setDisable(true);
        System.out.println(problem.getProblemTopic());
        System.out.println(Connect.positionProblem(problem.getProblemTopic()));
        System.out.println(Connect.whoOwn(Connect.positionProblem(problem.getProblemTopic())));
        user.setText(Connect.whoOwn(Connect.positionProblem(problem.getProblemTopic())));
    }    
    
}
