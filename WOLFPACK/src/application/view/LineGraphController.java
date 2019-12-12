package application.view;


//needed for init
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Slider;

public class LineGraphController implements Initializable{
	
	@FXML
    private Slider firstSlider;
	
	@FXML
    private Slider secondSlider;

	@FXML
    private Slider thirdSlider;
	
	@FXML
    private Slider fourthSlider;
	
	@FXML
    private LineChart<String, Number> lineChart;
	
	
	private Main mainApp;
	
	
//	Use this method to fill the line graph
	public void setGraphData() {
		
	}
	
//	Method not necessary at the moment
	public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
	}

	@Override
	//no idea how you guys would do it with the other method. this works for now.
	public void initialize(URL url, ResourceBundle rb) {
		lineChart.getData().clear();
		XYChart.Series<String, Number> deerData = new XYChart.Series<String, Number>();
		deerData.setName("Deer");
		deerData.getData().add(new XYChart.Data<String, Number>("2013", 2125));
		deerData.getData().add(new XYChart.Data<String, Number>("2014", 3150));
		deerData.getData().add(new XYChart.Data<String, Number>("2015", 3050));
		deerData.getData().add(new XYChart.Data<String, Number>("2016", 3400));
		deerData.getData().add(new XYChart.Data<String, Number>("2017", 3950));
		deerData.getData().add(new XYChart.Data<String, Number>("2018", 2320));
		deerData.getData().add(new XYChart.Data<String, Number>("2019", 1525));
		lineChart.getData().add(deerData);
		
		XYChart.Series<String, Number> horseData = new XYChart.Series<String, Number>();
		horseData.setName("Horses");
		horseData.getData().add(new XYChart.Data<String, Number>("2013", 900));
		horseData.getData().add(new XYChart.Data<String, Number>("2014", 1350));
		horseData.getData().add(new XYChart.Data<String, Number>("2015", 1075));
		horseData.getData().add(new XYChart.Data<String, Number>("2016", 975));
		horseData.getData().add(new XYChart.Data<String, Number>("2017", 1050));
		horseData.getData().add(new XYChart.Data<String, Number>("2018", 610));
		horseData.getData().add(new XYChart.Data<String, Number>("2019", 490));
		lineChart.getData().add(horseData);
		
		XYChart.Series<String, Number> cattleData = new XYChart.Series<String, Number>();
		cattleData.setName("Heck Cattle");
		cattleData.getData().add(new XYChart.Data<String, Number>("2013", 188));
		cattleData.getData().add(new XYChart.Data<String, Number>("2014", 250));
		cattleData.getData().add(new XYChart.Data<String, Number>("2015", 225));
		cattleData.getData().add(new XYChart.Data<String, Number>("2016", 180));
		cattleData.getData().add(new XYChart.Data<String, Number>("2017", 230));
		cattleData.getData().add(new XYChart.Data<String, Number>("2018", 200));
		cattleData.getData().add(new XYChart.Data<String, Number>("2019", 280));
		lineChart.getData().add(cattleData);
		
		XYChart.Series<String, Number> wolfData = new XYChart.Series<String, Number>();
		wolfData.setName("Wolves");
		wolfData.getData().add(new XYChart.Data<String, Number>("2013", 500));
		wolfData.getData().add(new XYChart.Data<String, Number>("2014", 500));
		wolfData.getData().add(new XYChart.Data<String, Number>("2015", 500));
		wolfData.getData().add(new XYChart.Data<String, Number>("2016", 500));
		wolfData.getData().add(new XYChart.Data<String, Number>("2017", 500));
		wolfData.getData().add(new XYChart.Data<String, Number>("2018", 500));
		wolfData.getData().add(new XYChart.Data<String, Number>("2019", 500));
		lineChart.getData().add(wolfData);
	}
	
	
	
	
	
}
