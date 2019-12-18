package data;

public class HeckCattle extends Animal {

	public HeckCattle() {
		super();
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
		//growth rate = (present-past)/past
		this.setGrowthRate(0.0968);
		this.setCarryingCapacity(300);
	}
}
