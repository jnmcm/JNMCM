package math;

import data.Park;
import domain.Animal;
import domain.GrayWolf;

public class LotkaVolterraModel implements IModels {
	Animal deer;
	Animal wolf;
	Animal cattle;
	Animal horse;
	
	double N1;
	double N2;
	double N3;
	
	double K1;
	double K2;
	double K3;
	
	double G1;
	double G2;
	double G3;
	
	int predatorPop;
	
	double a23;
	double a32;
	
	private double captureEfficiency;
	
	private void initVars() {
		wolf = Park.instance().getWolf();
		deer = Park.instance().getDeer();
		cattle = Park.instance().getCattle();
		horse = Park.instance().getHorse();
		
		N1 = deer.getCurrentPopulation();
		N2 = cattle.getCurrentPopulation();
		N3 = horse.getCurrentPopulation();
		
		K1 = deer.getCarryingCapacity();
		K2 = cattle.getCarryingCapacity();
		K3 = horse.getCarryingCapacity();
		
		G1 = deer.getGrowthRate();
		G2 = cattle.getGrowthRate();
		G3 = horse.getGrowthRate();
		
		captureEfficiency = 0.0005;
		predatorPop = wolf.getCurrentPopulation();
		a23 = ((0.86*0.75)+(0.12*0.02)+(0.02*0.23))/((0.75*0.75)+(0.02*0.02)+(0.23*0.23));
		a32 = ((0.86*0.75)+(0.12*0.02)+(0.02*0.23))/((0.86*0.86)+(0.12*0.12)+(0.02*0.02));
	}
	
	@Override
	public int getCalculatedPopulation(Animal animal) {
		initVars(); 
		double nextPop = 0;
		
		if (animal.getSpeciesName().equalsIgnoreCase("Red Deer")) {
				
			double calculation = G1 * N1 * (1 - (N1 / K1)) - captureEfficiency * N1 * predatorPop;
			nextPop = calculation + N1;
//			deer.setCurrentPopulation((int) Math.round(nextPop));

		} else if (animal.getSpeciesName().equalsIgnoreCase("Heck Cattle")) {
			
			double calculation = G2 * N2 * ((K2 - N2 - a23 * N3)/K2)- captureEfficiency * N2 * predatorPop;
			nextPop = calculation + N2;
//			cattle.setCurrentPopulation((int) Math.round(nextPop));
			
		} else if (animal.getSpeciesName().equalsIgnoreCase("Konik Horse")) {

			double calculation = G3 * N3 * ((K3 - N3 - a32 * N2)/K3) - captureEfficiency * N3 * predatorPop;
			nextPop = calculation + N3;
//			horse.setCurrentPopulation((int) Math.round(nextPop));
		} 
		animal.setCurrentPopulation((int) nextPop);
		return (int) nextPop;

	}

}
