package application.view;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import application.Main;
import data.Park;
import domain.Animal;
import domain.GrayWolf;
import domain.HeckCattle;
import domain.KonikHorse;
import domain.RedDeer;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import math.ModelFactory;
import math.ModelWolf;
import util.ClassNameCleaner;
import util.PackageClassLoader;

public class LineGraphController implements Initializable{
	
	private Animal redDeer = new RedDeer();
	private Animal konikHorse = new KonikHorse();
	private Animal heckCattle = new HeckCattle();
	private Animal grayWolf = new GrayWolf();
	
	@FXML
    private Slider firstSlider;
	
	@FXML
    private Slider secondSlider;
	
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
	
	@FXML
	private MenuButton modelBox;
	
	@FXML
	private RadioButton yesToViewWolves;
	
	@FXML
	private RadioButton noToViewWolves;
	
	@FXML
	private RadioButton realtimeMode;
	
	@FXML
	private RadioButton simulationMode;
	
	private Main mainApp;
	
	private XYChart.Series<Number, Number> deerData;
	private XYChart.Series<Number, Number> horseData;
	private XYChart.Series<Number, Number> cattleData;
	private XYChart.Series<Number, Number> wolfData;
	private int year = 2019;
	
	
    private ScheduledExecutorService scheduledExecutorService;
	
//	Uses selected model to populate animalMap and set on chart
	public void updateDeer(int year) {
		int nextPop = ModelFactory.getModel().getCalculatedPopulation(redDeer);
		Park.instance().setData(redDeer, year, nextPop);
		deerData.setData(Park.instance().getDataAsDataPoints(redDeer));
		
	}
	
	public void updateCattle(int year) {		
		int nextPop = ModelFactory.getModel().getCalculatedPopulation(heckCattle);
		Park.instance().setData(heckCattle, year, nextPop);
		cattleData.setData(Park.instance().getDataAsDataPoints(heckCattle));
	}
	
	public void updateHorse(int year) {
		int nextPop = ModelFactory.getModel().getCalculatedPopulation(konikHorse);
		Park.instance().setData(konikHorse, year, nextPop);
		horseData.setData(Park.instance().getDataAsDataPoints(konikHorse));
	}
	
	public void updateWolf(int year) {
		int nextPop = new ModelWolf().getCalculatedPopulation(grayWolf);
		Park.instance().setData(grayWolf, year, nextPop);
		wolfData.setData(Park.instance().getDataAsDataPoints(grayWolf));
	}

//	Method not necessary at the moment
	public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		//First Model (before selection) is exponential
		ModelFactory.setModel("Exponential Model");
		fillModelBox();
		fillData();
		
		
		nbWolves.setText("0");
		
//		gets MenuItems from model drop down menu and changes the model
//		depending on the selection
		ObservableList<MenuItem> menuItems = modelBox.getItems();
		for (MenuItem m: menuItems) {
			m.setOnAction(e -> {
//				text on the model drop down box matches the selection
				modelBox.setText(m.getText());
				ModelFactory.setModel(m.getText());
			});
		}

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
						if (yesToViewWolves.isSelected()) {
							wolfData.getData().clear();
							grayWolf.setInitPopulation(newValue.intValue());
							grayWolf.init();
							fillData();
							
						}
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
						fillData();
						
//						Draw the graph with the new parameters specified in the slider
						for (int y=2020; y<=newValue.intValue(); y++) {
							year = y;
							updateDeer(year);
							updateCattle(year);
							updateHorse(year);
							updateWolf(year);
							
						}
					};
				});
			}

		});	
		
		final ToggleGroup group1 = yesToViewWolves.getToggleGroup();
		group1.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
		    public void changed(ObservableValue<? extends Toggle> ov,
		        Toggle old_toggle, Toggle new_toggle) {
		            if (group1.getSelectedToggle() == yesToViewWolves) {
		            	lineChart.getData().add(wolfData);
		            } else {
		            	lineChart.getData().remove(3);
		            }
		        }
		});
		
		final ToggleGroup group2 = realtimeMode.getToggleGroup();
		group2.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
		    public void changed(ObservableValue<? extends Toggle> ov,
		        Toggle old_toggle, Toggle new_toggle) {
		            if (group2.getSelectedToggle() == realtimeMode) {
		            	fillData();
		            	secondSlider.setValue(2019);
		            	secondSlider.setDisable(true);
		            	firstSlider.setDisable(true);
//		            	noToViewWolves.setDisable(true);
		            	noToViewWolves.setVisible(false);
		            	yesToViewWolves.setDisable(true);
		            	RealtimeData();
		            } else {
		            	try {
							Thread.currentThread().interrupt();
							scheduledExecutorService.shutdown();
						} catch (Exception e) {
							e.printStackTrace();
						}
		            	secondSlider.setDisable(false);
		            	firstSlider.setDisable(false);
		            	noToViewWolves.setDisable(false);
		            	yesToViewWolves.setDisable(false);
		            	fillData();
		            }
		        }
		});
	}

//	model box is filled using a class loader that selects all models in the package
//	math, as long as it ends with the word Model (therefore ignoring IModels and the
//	factory)
	public void fillModelBox() {
		try {
			new PackageClassLoader();
			Class[] classes = PackageClassLoader.getClasses("math");
			for (Class c: classes) {
				String cc = new ClassNameCleaner().cleanName(c);
				if (cc.endsWith("Model")) {
//					creates new MenuItems for every model
					MenuItem m = new MenuItem(cc);
					modelBox.getItems().add(m);
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	public void fillData() {
		Park.instance().delete();
		redDeer.init();
		konikHorse.init();
		heckCattle.init();
		grayWolf.init();
		
//		add animals to the animalMap
		Park.instance().addAnimal(redDeer);
		Park.instance().addAnimal(konikHorse);
		Park.instance().addAnimal(heckCattle);
		Park.instance().addAnimal(grayWolf);
	
		final CSVParser parser;
		final String path = "historicalData.csv";
		
//		reads the csv file historicalData.csv with first line as a header
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
				
//				for every record read, add data to the map with the year and the matching population
				Park.instance().setData(redDeer, year, deerPop);
				Park.instance().setData(konikHorse, year, horsePop);
				Park.instance().setData(heckCattle, year, cattlePop);
				Park.instance().setData(grayWolf, year, grayWolf.getInitPopulation());
				
			}
		} catch (IOException e) { e.printStackTrace(); }
		
		
		lineChart.getData().clear();
		
//		get data from the animalMap
		deerData = new XYChart.Series<Number, Number>();
		deerData.setName("Red Deer");
		deerData.setData(Park.instance().getDataAsDataPoints(redDeer));
		lineChart.getData().add(deerData);
		
		horseData = new XYChart.Series<Number, Number>();
		horseData.setName("Konik Horses");
		horseData.setData(Park.instance().getDataAsDataPoints(konikHorse));
		lineChart.getData().add(horseData);
		
		cattleData = new XYChart.Series<Number, Number>();
		cattleData.setName("Heck Cattle");
		cattleData.setData(Park.instance().getDataAsDataPoints(heckCattle));
		lineChart.getData().add(cattleData);
		
		wolfData = new XYChart.Series<Number, Number>();
		wolfData.setName("Gray Wolves");
		wolfData.setData(Park.instance().getDataAsDataPoints(grayWolf));
		lineChart.getData().add(wolfData);
		
	}
	
	public void RealtimeData() {
		final AtomicInteger y = new AtomicInteger(2019);
        // setup a scheduled executor to periodically put data into the chart
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> {

            // Update the chart
            Platform.runLater(() -> {
                updateDeer(y.incrementAndGet());
                updateHorse(y.get());
                updateCattle(y.get());
                if (yesToViewWolves.isSelected()) {
                    updateWolf(y.get());
                }
            });
        }, 0, 1, TimeUnit.SECONDS);
	}
}

