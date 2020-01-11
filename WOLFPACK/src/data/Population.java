package data;

import java.util.Set;
import java.util.TreeMap;


public class Population {
	private TreeMap<Number, Number> popMap;
	
	public Population() {
		super();
		this.popMap = new TreeMap<>();
	}

	public Population(TreeMap<Number, Number> popMap) {
		super();
		this.popMap = popMap;
	}
	
	public void addPopulation(Number a, Number b) {
		this.popMap.put(a, b);
	}
	
	public Number getPopulationForYear(Number a) {
		return this.popMap.get(a);
	}

	public TreeMap<Number, Number> getAllPopulations() {
		return this.popMap;
	}
	
	public Set<Number> getAllYears() {
		return this.popMap.keySet();
	}

	public Number firstKey() {
		return popMap.firstKey();
	}
	
	public Number lastKey() {
		return popMap.lastKey();
	}
	
	
	
}
