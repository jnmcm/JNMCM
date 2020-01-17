package math;

import data.Park;
import domain.Animal;
import domain.RedDeer;

public class CompetitionModel implements IModels {
	
	//1=deer, 2=cattle, 3=horse
	
	// r = rate of growth
	private double r1;
	private double r2;
	private double r3;
	
	//N = init population
	private double N1;
	private double N2;
	private double N3;
	
	//K = carrying capacity
	private double K1;
	private double K2;
	private double K3;
	
	//a = competition coefficient
	private double a12;
	private double a13;
	private double a21;
	private double a23;
	private double a31;
	private double a32;
	
	public CompetitionModel() {
	}
	
	private void initVars() {
		Animal d = Park.instance().getDeer();
		Animal c = Park.instance().getCattle();
		Animal h = Park.instance().getHorse();
		
		r1 = d.getGrowthRate();
		r2 = c.getGrowthRate();
		r3 = h.getGrowthRate();
		
		N1 = d.getCurrentPopulation();
		N2 = c.getCurrentPopulation();
		N3 = h.getCurrentPopulation();
		
		K1 = d.getCarryingCapacity();
		K2 = d.getCarryingCapacity();
		K3 = d.getCarryingCapacity();
		
		//calculates using competition coefficient formula as follows:
		//					  SIGMA(pih*pjh)
		//				aij = -----------
		//					   SIGMA(pih)^2
		a12 = ((0.28*0.75)+(0.34*0.02)+(0.38*0.23))/((0.28*0.28)+(0.34*0.34)+(0.38*0.38));
		a13 = ((0.28*0.86)+(0.34*0.12)+(0.38*0.02))/((0.28*0.28)+(0.34*0.34)+(0.38*0.38));
		a21 = ((0.28*0.75)+(0.34*0.02)+(0.38*0.23))/((0.75*0.75)+(0.02*0.02)+(0.23*0.23));
		a23 = ((0.86*0.75)+(0.12*0.02)+(0.02*0.23))/((0.75*0.75)+(0.02*0.02)+(0.23*0.23));
		a31 = ((0.28*0.86)+(0.34*0.12)+(0.38*0.02))/((0.86*0.86)+(0.12*0.12)+(0.02*0.02));
		a32 = ((0.86*0.75)+(0.12*0.02)+(0.02*0.23))/((0.86*0.86)+(0.12*0.12)+(0.02*0.02));
	}
	
	@Override
	public int getCalculatedPopulation(Animal animal) {
		initVars();
		double nextpop = 0;
		//formula:
		//					  	  (Ki - Ni - aij*Nj - aik*Nk)
		//			Ni + ri*Ni * ------------------------------
		//			`							Ki
		if (animal.getSpeciesName().equalsIgnoreCase("Red Deer")) {
			nextpop = N1 + (r1 * N1 * (K1 - N1 - a12*N2 - a13*N3) / K1);
		} else if (animal.getSpeciesName().equalsIgnoreCase("Heck Cattle")) {
			nextpop = N2 + (r2 * N2 * (K2 - N2 - a21*N1 - a23*N3) / K2);
		} else if (animal.getSpeciesName().equalsIgnoreCase("Konik Horse")) {
			nextpop = N3 + (r3 * N3 * (K3 - N3 - a31*N1 - a32*N2) / K3);
		}
		animal.setCurrentPopulation((int) Math.round(nextpop));
		return (int) Math.round(nextpop);
	}

}

