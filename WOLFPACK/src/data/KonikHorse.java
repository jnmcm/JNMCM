package data;

public class KonikHorse extends Animal {

	public KonikHorse() {
		super();
	}

	public KonikHorse(int initPopulation, double growthRate, double carryingCapacity) {
		super(initPopulation, growthRate, carryingCapacity);
	}
	
	@Override
	public void init() {
		//here goes initial data of Konik Horses
		//later it should just read it from the last column of a specific csv file
		this.setSpeciesName("Konik Horses");
		this.setInitPopulation(490);
		this.setGrowthRate(-0.0559);
		this.setCarryingCapacity(400);
	}
}
