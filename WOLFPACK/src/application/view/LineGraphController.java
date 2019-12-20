package application.view;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import application.Main;
import data.AnimalMap;
import domain.Animal;
import domain.HeckCattle;
import domain.KonikHorse;
import domain.RedDeer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import math.ExponentialModel;

public class LineGraphController implements Initializable{
	
	private Animal redDeer = new RedDeer();
	private Animal konikHorse = new KonikHorse();
	private Animal heckCattle = new HeckCattle();
	
	@FXML
    private Slider firstSlider;
	
	@FXML
    private Slider secondSlider;

	@FXML
    private Slider thirdSlider;
	
	@FXML
    private Slider fourthSlider;
	
	@FXML
    private LineChart<Number, Number> lineChart;
	
	@FXML
	private Label nbWolves;
	
	@FXML
	private Label yearLabel;
	
	@FXML
	private NumberAxis xAxis;
	
	@FXML
	private NumberAxis yAxis;
	
	private Main mainApp;
	
	private XYChart.Series<Number, Number> deerData;
	private XYChart.Series<Number, Number> horseData;
	private XYChart.Series<Number, Number> cattleData;
	private int year = 2019;
	
//	Use this method to fill the line graph
	public XYChart.Series<Number, Number> wolfData() {
		XYChart.Series<Number, Number> wolfData = new XYChart.Series<Number, Number>();
		wolfData.setName("Gray Wolves");
		wolfData.getData().add(new XYChart.Data<Number, Number>(2013, Integer.valueOf(nbWolves.getText())));
		wolfData.getData().add(new XYChart.Data<Number, Number>(2014, Integer.valueOf(nbWolves.getText())));
		wolfData.getData().add(new XYChart.Data<Number, Number>(2015, Integer.valueOf(nbWolves.getText())));
		wolfData.getData().add(new XYChart.Data<Number, Number>(2016, Integer.valueOf(nbWolves.getText())));
		wolfData.getData().add(new XYChart.Data<Number, Number>(2017, Integer.valueOf(nbWolves.getText())));
		wolfData.getData().add(new XYChart.Data<Number, Number>(2018, Integer.valueOf(nbWolves.getText())));
		wolfData.getData().add(new XYChart.Data<Number, Number>(2019, Integer.valueOf(nbWolves.getText())));
		return wolfData;
	}
	
	public void updateDeer(int year) {
		new ExponentialModel(redDeer, year);
		deerData.setData(AnimalMap.instance().getData(redDeer));
	}
	
	public void updateCattle(int year) {
		new ExponentialModel(heckCattle, year);
		cattleData.setData(AnimalMap.instance().getData(heckCattle));
	}
	
	public void updateHorse(int year) {
		new ExponentialModel(konikHorse, year);
		horseData.setData(AnimalMap.instance().getData(konikHorse));
	}

//	Method not necessary at the moment
	public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		fillData();

		firstSlider.valueProperty().addListener(new ChangeListener<Number>() {


			@Override
			public void changed(
					ObservableValue<? extends Number> observableValue, 
					Number oldValue, 
					Number newValue) { 
				
				nbWolves.textProperty().setValue(String.valueOf(newValue.intValue()));

//				Allows the graph to change just when the click in the slider is dropped
				firstSlider.setOnMouseReleased(new EventHandler<MouseEvent>() { 
					@Override
					public void handle(MouseEvent event) {
						
//						Draw the graph with the new parameters specified in the slider
						lineChart.getData().set(1, wolfData());
					};      
				});
			}

		});
		
		secondSlider.valueProperty().addListener(new ChangeListener<Number>() {


			@Override
			public void changed(
					ObservableValue<? extends Number> observableValue, 
					Number oldValue, 
					Number newValue) { 
				
				yearLabel.textProperty().setValue(String.valueOf(newValue.intValue()));

//				Allows the graph to change just when the click in the slider is dropped
				secondSlider.setOnMouseReleased(new EventHandler<MouseEvent>() { 
					@Override
					public void handle(MouseEvent event) {
						year = 2019;
						fillData();
//						Draw the graph with the new parameters specified in the slider
						for (int y=2020; y<=newValue.intValue(); y++) {
							year = y;
							updateDeer(year);
							updateCattle(year);
							updateHorse(year);
						}
					};
				});
			}

		});	
	}
	
	public void fillData() {
		AnimalMap.instance().delete();
		redDeer.init();
		konikHorse.init();
		heckCattle.init();
		
		AnimalMap.instance().addAnimal(redDeer);
		AnimalMap.instance().addAnimal(konikHorse);
		AnimalMap.instance().addAnimal(heckCattle);
		
		final CSVParser parser;
		final String path = "historicalData.csv";
		
		try {
			final Reader reader = new BufferedReader(new FileReader(getClass().getResource(path).getPath()));
			parser = new CSVParser(reader, CSVFormat.DEFAULT
					.withFirstRecordAsHeader()
					.withIgnoreHeaderCase()
					.withTrim());
			for (CSVRecord record:parser) {
				int year = Integer.valueOf(record.get("Year"));
				int deerPop = Integer.valueOf(record.get("Red Deer"));
				int horsePop = Integer.valueOf(record.get("Konik Horses"));
				int cattlePop = Integer.valueOf(record.get("Heck Cattle"));
				
				AnimalMap.instance().setData(redDeer, year, deerPop);
				AnimalMap.instance().setData(konikHorse, year, horsePop);
				AnimalMap.instance().setData(heckCattle, year, cattlePop);
				
			}
		} catch (IOException e) { e.printStackTrace(); }
		
		lineChart.getData().clear();
	
		xAxis.setAutoRanging(false);
		xAxis.setLowerBound(2013);
		xAxis.setUpperBound((int) secondSlider.getValue());
		xAxis.setTickUnit(1);
		
		nbWolves.setText("0");
		
		deerData = new XYChart.Series<Number, Number>();
		deerData.setName("Red Deer");
		deerData.setData(AnimalMap.instance().getData(redDeer));
		lineChart.getData().add(deerData);
		
		horseData = new XYChart.Series<Number, Number>();
		horseData.setName("Konik Horses");
		horseData.setData(AnimalMap.instance().getData(konikHorse));
		lineChart.getData().add(horseData);
		
		cattleData = new XYChart.Series<Number, Number>();
		cattleData.setName("Heck Cattle");
		cattleData.setData(AnimalMap.instance().getData(heckCattle));
		lineChart.getData().add(cattleData);
		

		lineChart.getData().add(wolfData());
	}

}
