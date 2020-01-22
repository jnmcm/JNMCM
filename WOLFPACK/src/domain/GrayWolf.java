package domain;

public class GrayWolf extends Animal {

	public GrayWolf() {
		super();
		this.setInitPopulation(0);
		init();
	}

	public GrayWolf(int initPopulation, double growthRate, double carryingCapacity) {
		super(initPopulation, growthRate, carryingCapacity);
	}
	
	@Override
	public void init() {
		this.setSpeciesName("Gray Wolf");
		this.setCurrentPopulation(getInitPopulation());
		this.setGrowthRate(0.11);
		this.setCarryingCapacity(5);
	}
	
}
