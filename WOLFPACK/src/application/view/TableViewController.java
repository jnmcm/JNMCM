package application.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import data.Park;
import domain.Animal;
import domain.HeckCattle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TableViewController implements Initializable{
	@FXML
	private MenuButton bySpecies;
	
	@FXML
	private TableView<Number> table = new TableView<Number>();
	
	@FXML
	private TableColumn<Number, String> firstCol = new TableColumn<>("key");
	
	@FXML
	private TableColumn<Number, String> secondCol = new TableColumn<>("value");
	
	TreeMap<Number, Number> data;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Set<Animal> allAnimals = Park.instance().getAnimals();
		for (Animal a:allAnimals) {
			MenuItem m = new MenuItem(a.getSpeciesName());
			bySpecies.getItems().add(m);
		}
		
		for (MenuItem menu:bySpecies.getItems()) {
			menu.setOnAction(e -> {
				addDataForAnimal(menu.getText());
				bySpecies.setText(menu.getText());
			});
		}
		
		addDataForAnimal("Red Deer");
		addDataForAnimal("Konik Horse");
		addDataForAnimal("Heck Cattle");
		addDataForAnimal("Gray Wolf");
	}
	
	public void fillTable() {
		table.getItems().clear();
        table.getItems().addAll(data.keySet());
        firstCol.setCellValueFactory(cd -> new SimpleStringProperty((cd.getValue()).toString()));	        
        secondCol.setCellValueFactory(cd -> new SimpleStringProperty((data.get(cd.getValue()).toString())));
	}
	
	public void addDataForAnimal(String animal) {

		if (animal=="Red Deer") {
			bySpecies.getItems().get(3).setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			    public void handle(ActionEvent t) {
			    	data = Park.instance().getDataForAnimal(Park.instance().getDeer());
			        bySpecies.setText(bySpecies.getItems().get(3).getText());
			        fillTable();
			    }
			    });
		} else if (animal=="Konik Horse") {
			bySpecies.getItems().get(2).setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			    public void handle(ActionEvent t) {
			    	data = Park.instance().getDataForAnimal(Park.instance().getHorse());
			        bySpecies.setText(bySpecies.getItems().get(2).getText());
			        fillTable();
			    }
			    });
		} else if (animal=="Heck Cattle") {
			
			bySpecies.getItems().get(1).setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			    public void handle(ActionEvent t) {
			    	data = Park.instance().getDataForAnimal(Park.instance().getCattle());
			        bySpecies.setText(bySpecies.getItems().get(1).getText());
			        fillTable();
			    }
			    });
		} else if (animal=="Gray Wolf") {
			bySpecies.getItems().get(0).setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			    public void handle(ActionEvent t) {
			    	data = Park.instance().getDataForAnimal(Park.instance().getWolf());
			        bySpecies.setText(bySpecies.getItems().get(0).getText());
			        fillTable();
			    }
			    });
		}
		firstCol.setText("Year");
		secondCol.setText("Population");
	}
}