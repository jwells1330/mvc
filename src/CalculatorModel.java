import java.util.ArrayList;

public class CalculatorModel implements CalculatorModelInterface {
	ArrayList<CalculatorObserver> calcObservers = new ArrayList<CalculatorObserver>();
	private String answer;

	public CalculatorModel() {
	}
	
	@Override
	public void registerObserver(CalculatorObserver o) {
		calcObservers.add(o);
	}
	@Override
	public void removeObserver(CalculatorObserver o) {
		if(calcObservers.contains(o)){
			calcObservers.remove(o);
		}
	}
	@Override
	public void notifyObservers() {
		for(CalculatorObserver o : calcObservers){
			o.update(answer);
		}
	}	

	@Override
	public void doMaths(int mathType, String value1, String value2) {
		double mathsAnswer;
		if(mathType == 0){
			mathsAnswer = Double.parseDouble(value1) + Double.parseDouble(value2);
		}else if(mathType == 1){
			mathsAnswer = Double.parseDouble(value1) - Double.parseDouble(value2);
		}else if(mathType == 2){
			mathsAnswer = Double.parseDouble(value1) * Double.parseDouble(value2);
		}else{
			mathsAnswer = Double.parseDouble(value1) / Double.parseDouble(value2);
		}
		answer = "" + mathsAnswer;
		System.out.println(answer);
		notifyObservers();
	}

}
