package math;

import domain.Animal;

public class LogisticModel implements IModels {
	
	private double carryingCapacity;
	private double growthRate;
	private int population;
	
	public LogisticModel() {}

//	please fill this with formula and return a population
//	see ExponentialModel to know how it works
	@Override
	public int getCalculatedPopulation(Animal animal) {
		this.carryingCapacity = animal.getCarryingCapacity();
		this.population = animal.getCurrentPopulation();
		this.growthRate = animal.getGrowthRate();
		 
//		  Formula:        ___________k___________          k=carrying capacity     ;    rt=growthRate
//							1+[(k-N0)/N0]*e^-rt			   N0=initial population size
		
		
		
//		exponentialCalculation not used directly on logistic model
//		int exponentialCalculation = getCalculatedPopulation(animal);
		
//		
//		Logistic calculation
		int nextPop = (int)( carryingCapacity/(1+((carryingCapacity-population)/population)*Math.exp(growthRate*-1)) );
		
		animal.setCurrentPopulation(nextPop);
		return nextPop;
	}

}
