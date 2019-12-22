package math;

import data.AnimalMap;
import domain.Animal;

public class ExponentialModel implements IModels{
	
	private int population;
	private double growthRate;
	
	public ExponentialModel() {}
	
//	returns a new population based on the latest
	@Override
	public int getCalculatedPopulation(Animal animal) {
		this.population = animal.getCurrentPopulation();
		this.growthRate = animal.getGrowthRate();
		
		int nextPop = (int) (population*Math.exp(growthRate));
		
		animal.setCurrentPopulation(nextPop);
		
		return nextPop;
	}
}
