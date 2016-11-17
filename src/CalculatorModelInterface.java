
public interface CalculatorModelInterface {

	public void doMaths(int mathType, String value1, String value2);
	
	void registerObserver(CalculatorObserver o);
	
	void removeObserver(CalculatorObserver o);
	
	void notifyObservers();
	
}
