package math;

import domain.Animal;

public class ModelWolf implements IModels {

	private int population;
	private double birth;
	private double death;
	
	@Override
	public int getCalculatedPopulation(Animal animal) {
		this.population = animal.getCurrentPopulation();
		this.death = 0.18;
		this.birth = animal.getGrowthRate() + this.death;
		int newpop = (int) Math.round(population*birth-population*death+population);
		animal.setCurrentPopulation(newpop);
		return newpop;
	}

}
