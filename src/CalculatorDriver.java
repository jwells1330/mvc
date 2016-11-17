
public class CalculatorDriver {
	
	public static void main(String[] args){
		CalculatorModelInterface model = new CalculatorModel();
		CalculatorControllerInterface controller = new CalculatorController(model);
	}
}
