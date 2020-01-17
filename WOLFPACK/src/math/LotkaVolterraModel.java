package math;

import data.Park;
import domain.Animal;
import domain.GrayWolf;

public class LotkaVolterraModel extends CompetitionModel implements IModels {
	//Use the formula sheet I sent you @Carlos
	//Check LotkaVolterraModel class for example
	//we need capture efficiency, for now, you can use random data I guess
	
	private double captureEfficiency;
	
	Animal wolf = Park.instance().getWolf();
	Animal deer = Park.instance().getDeer();
	Animal cattle = Park.instance().getCattle();
	Animal horse = Park.instance().getHorse();
	
	double N1 = deer.getCurrentPopulation();
	double N2 = cattle.getCurrentPopulation();
	double N3 = horse.getCurrentPopulation();
	
	double K1 = deer.getCarryingCapacity();
	double K2 = cattle.getCarryingCapacity();
	double K3 = horse.getCarryingCapacity();
	
	double G1 = deer.getGrowthRate();
	double G2 = cattle.getGrowthRate();
	double G3 = horse.getGrowthRate();
	
	@Override
	public int getCalculatedPopulation(Animal animal) {

		this.captureEfficiency = 0.0005;
		int predatorPop = wolf.getCurrentPopulation();
		double a23 = ((0.86*0.75)+(0.12*0.02)+(0.02*0.23))/((0.75*0.75)+(0.02*0.02)+(0.23*0.23));
		double a32 = ((0.86*0.75)+(0.12*0.02)+(0.02*0.23))/((0.86*0.86)+(0.12*0.12)+(0.02*0.02));
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
