package application.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import data.Park;
import domain.Animal;
import javafx.collections.ObservableList;
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
	private MenuButton byYear;
	
	@FXML
	private TableView table;
	
	@FXML
	private TableColumn firstCol;
	
	@FXML
	private TableColumn secondCol;
	
	//for Carlos: can you fill the TableView with the class Park? 

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Set<Animal> allAnimals = Park.instance().getAnimals();
		for (Animal a:allAnimals) {
			MenuItem m = new MenuItem(a.getSpeciesName());
			bySpecies.getItems().add(m);
		}
		
		SortedSet<Integer> allYears = new TreeSet<>();
		for (Number data: Park.instance().getDataForAnimal(Park.instance().getDeer()).keySet()) {
			allYears.add(data.intValue());
		}
		for (int y:allYears) {
			MenuItem m = new MenuItem(String.valueOf(y));
			byYear.getItems().add(m);
		}
		
		for (MenuItem menu:bySpecies.getItems()) {
			menu.setOnAction(e -> {
				addDataForAnimal(menu.getText());
				bySpecies.setText(menu.getText());
			});
		}
	}
	
	public void addDataForAnimal(String animal) {
		TreeMap<Number, Number> data;
		if (animal=="Red Deer") {
			data = Park.instance().getDataForAnimal(Park.instance().getDeer());
		} else if (animal=="Konik Horse") {
			data = Park.instance().getDataForAnimal(Park.instance().getHorse());
		} else if (animal=="Heck Cattle") {
			data = Park.instance().getDataForAnimal(Park.instance().getCattle());
		} else if (animal=="Gray Wolf") {
			data = Park.instance().getDataForAnimal(Park.instance().getWolf());
		}
		firstCol.setText("Year");
		secondCol.setText("Population");
	}
}
