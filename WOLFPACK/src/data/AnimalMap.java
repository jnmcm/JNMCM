package data;

import java.util.Set;
import java.util.TreeMap;

import domain.Animal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart.Data;

public class AnimalMap {
	private static AnimalMap instance = null;
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
	
	public void addAnimal(Animal animal) {
		animalMap.put(animal, new TreeMap<Number,Number>());
	}
	
	public void setData(Animal animal, Number year, Number population) {
		animalMap.get(animal).put(year, population);
	}
	
	public Set<Animal> getAnimals() {
		return animalMap.keySet();
	}
	
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
	
	public void delete() {
		instance = null;
	}
}
