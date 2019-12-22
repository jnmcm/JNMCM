package data;

import java.util.Set;
import java.util.TreeMap;

import domain.Animal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart.Data;

public class AnimalMap {
	
//	map that keeps track of all data for each animal
//	now it only does it for the expo model, maybe later it will also involve models
//	otherwise we can just clear it every time
	
	private static AnimalMap instance = null;
	
//	map with a map (ex: {Deer={2013=250, 2014=310 ...}, Horse={2013=...}}
	private static TreeMap<Animal, TreeMap<Number, Number>> animalMap;
	
	//private static IModel model;
	
	private AnimalMap() {
		animalMap = new TreeMap<Animal, TreeMap<Number, Number>>();
	}
	
	public static AnimalMap instance() {
		if (instance==null) {
			instance = new AnimalMap();
		}
		return instance;
	}

//	add animals to the animalMap
	public void addAnimal(Animal animal) {
		animalMap.put(animal, new TreeMap<Number,Number>());
	}
	
//	set data for specific animal
	public void setData(Animal animal, Number year, Number population) {
		animalMap.get(animal).put(year, population);
	}
	
//	return all animals in the map
	public Set<Animal> getAnimals() {
		return animalMap.keySet();
	}
	
//	return all data for specific animal as a list of data points
//	for easier implementation into chart
	public ObservableList<Data<Number, Number>> getData(Animal animal) {
		ObservableList<Data<Number,Number>> animalList = FXCollections.observableArrayList();
		for (int i=(int)animalMap.get(animal).firstKey(); i<=(int)animalMap.get(animal).lastKey(); i++) {
			Data<Number,Number> data = new Data<Number,Number>();
			data.setXValue(i);
			data.setYValue(animalMap.get(animal).get(i));
			animalList.add(data);
		}
		return animalList;
	}
	
//	clear the map
	public void delete() {
		instance = null;
	}
}
