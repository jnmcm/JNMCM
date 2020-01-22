package application.view;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;

import data.Park;
import domain.Animal;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class BarChartController implements Initializable {
	@FXML
	private BarChart barChart;
	
	@FXML
	private MenuButton yearsBox;

	int y = 2019;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fillBox();
		yearsBox.setText(String.valueOf(y));
		
		ObservableList<MenuItem> menuItems = yearsBox.getItems();
		for (MenuItem m: menuItems) {
			m.setOnAction(e -> {
//				text on the model drop down box matches the selection
				yearsBox.setText(m.getText());
				y = Integer.valueOf(m.getText());
				fillChart();
			});
		}
		
		fillChart();
	}
	
	public void fillBox() {
		Set<Number> years = Park.instance().getDataForAnimal(Park.instance().getCattle()).keySet();
		for (Number y: years) {
//				creates new MenuItems for every year
			MenuItem m = new MenuItem(String.valueOf(y));
			yearsBox.getItems().add(m);
		}
	}
	
	public void fillChart() {
		XYChart.Series<String, Number> target = new XYChart.Series<>();
		XYChart.Series<String, Number> yearly = new XYChart.Series<>();
        barChart.getData().clear();
        
		target.setName("Target Population");
		target.getData().add(new XYChart.Data<>("Heck Cattle",200));
		target.getData().add(new XYChart.Data<>("Konik Horse",450));
		target.getData().add(new XYChart.Data<>("Red Deer",490));
		
		barChart.getData().add(target);
		
		yearly.setName("Yearly Population");
		TreeMap<Animal, Number> yearTree = Park.instance().getDataForYear(y);
		for (Map.Entry<Animal, Number> entry : yearTree.entrySet()) {
	        String animal = entry.getKey().getSpeciesName();
	        Number population = entry.getValue();
	        if (!animal.equalsIgnoreCase("Gray Wolf")) {
	        	XYChart.Data<String, Number> d = new XYChart.Data<>(animal, population);
		        yearly.getData().add(d);
	        }
	    }
		barChart.getData().add(yearly);
	}
	
}
