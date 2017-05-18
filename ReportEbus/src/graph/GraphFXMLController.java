/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import ebus.model.Connect;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author User
 */
public class GraphFXMLController implements Initializable {

    @FXML
    private PieChart graphProblem;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO "ปัญหารถ", "ปัญหาคนขับ", "ปัญหาแอพ", "อื่นๆ"
        int type[] = Connect.countProblem();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
            new PieChart.Data("ปัญหารถ", type[0]),
            new PieChart.Data("ปัญหาคนขับ", type[1]),
            new PieChart.Data("ปัญหาแอพ", type[2]),
            new PieChart.Data("อื่นๆ", type[3])
        );
        graphProblem.setData(pieChartData);
        graphProblem.setLegendSide(Side.LEFT);
    }    
    
}
