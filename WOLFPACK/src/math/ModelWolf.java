package math;

import domain.Animal;

public class ModelWolf implements IModels {

	private int population;
	private double birth;
	private double death;
	
	@Override
	public int getCalculatedPopulation(Animal animal) {
		this.population = animal.getCurrentPopulation();
		System.out.println(population);
		this.death = 0.18;
		this.birth = 0.29;
		int newpop = (int) Math.round(population*birth-population*death+population);
		animal.setCurrentPopulation(newpop);
		System.out.println(newpop);
		return newpop;
	}

}
