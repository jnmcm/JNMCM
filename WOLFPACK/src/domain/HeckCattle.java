package domain;

public class HeckCattle extends Animal {

	public HeckCattle() {
		super();
		init();
	}

	public HeckCattle(int initPopulation, double growthRate, double carryingCapacity) {
		super(initPopulation, growthRate, carryingCapacity);
	}
	
	@Override
	public void init() {
		//here goes initial data of Heck Cattle
		//later it should just read it from the last column of a specific csv file
		this.setSpeciesName("Heck Cattle");
		this.setInitPopulation(280);
		this.setCurrentPopulation(getInitPopulation());
		this.setGrowthRate(0.2265741122);
		this.setCarryingCapacity(500);
	}
}
