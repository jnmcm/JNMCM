package application.view;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Slider;

public class LineGraphController {
	
	@FXML
    private Slider firstSlider;
	
	@FXML
    private Slider secondSlider;

	@FXML
    private Slider thirdSlider;
	
	@FXML
    private Slider fourthSlider;
	
	@FXML
    private LineChart lineChart;
	
	
	private Main mainApp;
	
	
//	Use this method to fill the line graph
	public void setGraphData() {
		
	}
	
//	Method not necessary at the moment
	public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
	}
}
