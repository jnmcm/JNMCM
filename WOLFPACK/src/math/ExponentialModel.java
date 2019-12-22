package math;

import data.AnimalMap;
import domain.Animal;

public class ExponentialModel {
	
	public ExponentialModel(Animal animal, int year) {
		try {
			int population = animal.getCurrentPopulation();
			int nextPop =  (int) (population*Math.exp(animal.getGrowthRate()));
			AnimalMap.instance().setData(animal, year, nextPop);
			animal.setCurrentPopulation(nextPop);
			
		}
		catch(Exception e) {
			System.out.println("Something went wrong in the exponential model");
		}
			
	}
}
