package domain;

public class GrayWolf extends Animal {

	public GrayWolf() {
		super();
		this.setInitPopulation(0);
		init();
	}

	public GrayWolf(int initPopulation, double growthRate, double carryingCapacity) {
		super(initPopulation, growthRate, carryingCapacity);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void init() {
		this.setSpeciesName("Gray Wolf");
		this.setCurrentPopulation(getInitPopulation());
		this.setGrowthRate(0.4);
		this.setCarryingCapacity(200);
	}
	
}
