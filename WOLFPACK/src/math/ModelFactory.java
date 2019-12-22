package math;

public class ModelFactory {
	
//	creates a model based on selection
	private static IModels model;
	
	public static IModels getModel() {
		return model;
	}
	
	public static void setModel(String chosenModel) {
		switch(chosenModel) {
		case "Exponential Model":
			model = new ExponentialModel();
			break;
		case "Logistic Model":
			model = new LogisticModel();
			break;
		case "Lotka Volterra Model":
			model = new LotkaVolterraModel();
			break;
		}
		
	}
}
