package domain;

public class RedDeer extends Animal {
	public RedDeer() {
		super();
		init();
	}

	public RedDeer(int initPopulation, double growthRate, double carryingCapacity) {
		super(initPopulation, growthRate, carryingCapacity);
	}

	@Override
	public void init() {
		//here goes initial data of Red Deer 
		//later it should just read it from the last column of a specific csv file
		this.setSpeciesName("Red Deer");
		this.setInitPopulation(1525);
		this.setCurrentPopulation(getInitPopulation());
		this.setGrowthRate(0.4);
		this.setCarryingCapacity(3000);
	}
}
