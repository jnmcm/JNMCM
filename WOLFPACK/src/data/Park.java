package data;

import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import domain.Animal;
import domain.GrayWolf;
import domain.HeckCattle;
import domain.KonikHorse;
import domain.RedDeer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.chart.XYChart.Data;
// 
// 

public class Park {
	
//	map that keeps track of all data for each animal
//	now it only does it for the expo model, maybe later it will also involve models
//	otherwise we can just clear it every time
	
	private static Park instance = null;
	
//	map with a map (ex: {Deer={2013=250, 2014=310 ...}, Horse={2013=...}}
	private static TreeMap<Animal, Population> animalMap;
	
	//private static IModel model;
	
	private Park() {
		animalMap = new TreeMap<Animal, Population>();
	}
	
	public static Park instance() {
		if (instance==null) {
			instance = new Park();
		}
		return instance;
	}

//	add animals to the animalMap
	public void addAnimal(Animal animal) {
		animalMap.put(animal, new Population());
	}
	
//	set data for specific animal
	public void setData(Animal animal, Number year, Number population) {
		animalMap.get(animal).addPopulation(year, population);
	}
	
	public void setAllData(TreeMap<Animal, Population> map) {
		animalMap.putAll(map);
	}
	
//	return all animals in the map
	public Set<Animal> getAnimals() {
		return animalMap.keySet();
	}
	
	public TreeMap<Number, Number> getDataForAnimal(Animal animal) {
		return animalMap.get(animal).getAllPopulations();
	}
	
	public TreeMap<Animal, Number> getDataForYear(Number y) {
		TreeMap<Animal, Number> all = new TreeMap<>();
		for (Animal a: animalMap.keySet()) {
			all.put(a, animalMap.get(a).getPopulationForYear(y));
		}
		return all;
	}
	
	public TreeMap<Animal, TreeMap<Number, Number>> getAllData() {
		TreeMap<Animal, TreeMap<Number, Number>> all = new TreeMap<>();
		for (Animal a: animalMap.keySet()) {
			all.put(a, animalMap.get(a).getAllPopulations());
		}
		return all;
	}
	
	
	
	public RedDeer getDeer() {
		for (Animal a:animalMap.keySet()) {
			if (a.getClass() == RedDeer.class) {
				return (RedDeer) a;
			}
		}
		return null;
	}
	
	public KonikHorse getHorse() {
		for (Animal a:animalMap.keySet()) {
			if (a.getClass() == KonikHorse.class) {
				return (KonikHorse) a;
			}
		}
		return null;
	}
	
	public HeckCattle getCattle() {
		for (Animal a:animalMap.keySet()) {
			if (a.getClass() == HeckCattle.class) {
				return (HeckCattle) a;
			}
		}
		return null;
	}
	
	public GrayWolf getWolf() {
		for (Animal a:animalMap.keySet()) {
			if (a.getClass() == GrayWolf.class) {
				return (GrayWolf) a;
			}
		}
		return null;
	}
	
	
//	return all data for specific animal as a list of data points
//	for easier implementation into chart
	public ObservableList<Data<Number, Number>> getDataAsDataPoints(Animal animal) {
		ObservableList<Data<Number,Number>> animalList = FXCollections.observableArrayList();
		for (int i=(int)animalMap.get(animal).firstKey(); i<=(int)animalMap.get(animal).lastKey(); i++) {
			Data<Number,Number> data = new Data<Number,Number>();
			data.setXValue(i);
			data.setYValue(animalMap.get(animal).getPopulationForYear(i));
			animalList.add(data);
		}
		return animalList;
	}
	
//	clear the map
	public void delete() {
		instance = null;
	}
	
}
