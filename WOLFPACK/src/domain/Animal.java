package domain;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Animal implements Comparable<Animal>{
	private String speciesName;
	private int initPopulation;
	private int currentPopulation;
	private double growthRate;
	private double carryingCapacity;
	
	public Animal() {}
	
	public Animal(int initPopulation, double growthRate, double carryingCapacity) {
		super();
		this.initPopulation = initPopulation;
		this.growthRate = growthRate;
		this.carryingCapacity = carryingCapacity;
	}

	public String getSpeciesName() {
		return speciesName;
	}

	public void setSpeciesName(String speciesName) {
		this.speciesName = speciesName;
	}

	public int getInitPopulation() {
		return initPopulation;
	}

	public void setInitPopulation(int initPopulation) {
		this.initPopulation = initPopulation;
	}

	public int getCurrentPopulation() {
		return currentPopulation;
	}

	public void setCurrentPopulation(int currentPopulation) {
		this.currentPopulation = currentPopulation;
	}

	public double getGrowthRate() {
		return growthRate;
	}

	public void setGrowthRate(double growthRate) {
		this.growthRate = growthRate;
	}

	public double getCarryingCapacity() {
		return carryingCapacity;
	}

	public void setCarryingCapacity(double carryingCapacity) {
		this.carryingCapacity = carryingCapacity;
	}
	
	public StringProperty nameProperty() {
		StringProperty nameProperty = new SimpleStringProperty();
		nameProperty.set(speciesName);
		return nameProperty;
	}
	
	public IntegerProperty currentPopulationProperty() {
		IntegerProperty currentPopulationProperty = new SimpleIntegerProperty();
		currentPopulationProperty.set(currentPopulation);
		return currentPopulationProperty;
	}
	
	public DoubleProperty growthRateProperty() {
		DoubleProperty growthRateProperty = new SimpleDoubleProperty();
		growthRateProperty.set(growthRate);
		return growthRateProperty;
	}
	
	public DoubleProperty carryCapProperty() {
		DoubleProperty carryCapProperty = new SimpleDoubleProperty();
		carryCapProperty.set(carryingCapacity);
		return carryCapProperty;
	}
	
	public void init() {}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		if (speciesName == null) {
			if (other.speciesName != null)
				return false;
		} else if (!speciesName.equals(other.speciesName))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Animal other) {
		return this.speciesName.compareTo(other.speciesName);
	}
}
