package domain;

public class KonikHorse extends Animal {

	public KonikHorse() {
		super();
		init();
	}

	public KonikHorse(int initPopulation, double growthRate, double carryingCapacity) {
		super(initPopulation, growthRate, carryingCapacity);
	}
	
	@Override
	public void init() {
		//here goes initial data of Konik Horses
		//later it should just read it from the last column of a specific csv file
		this.setSpeciesName("Konik Horse");
		this.setInitPopulation(490);
		this.setCurrentPopulation(getInitPopulation());
		this.setGrowthRate(0.3301);
		this.setCarryingCapacity(800);
	}
}
