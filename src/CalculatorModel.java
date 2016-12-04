import java.util.ArrayList;

public class CalculatorModel implements CalculatorModelInterface {
  ArrayList<CalculatorObserver> calcObservers = new ArrayList<CalculatorObserver>();
  private String answer;

  // blank constructor
  public CalculatorModel() {
  }

  // adds observer for observer pattern
  @Override
  public void registerObserver(CalculatorObserver o) {
    calcObservers.add(o);
  }

  // removes observer for observer pattern
  @Override
  public void removeObserver(CalculatorObserver o) {
    if (calcObservers.contains(o)) {
      calcObservers.remove(o);
    }
  }

  // notifies observers for observer pattern (calls update method on observers)
  @Override
  public void notifyObservers() {
    for (CalculatorObserver o : calcObservers) {
      o.update(answer);
    }
  }

  // does calculation
  @Override
  public void doMaths(int mathType, String value1, String value2) {
    double mathsAnswer;
    if (mathType == 0) {
      mathsAnswer = Double.parseDouble(value1) + Double.parseDouble(value2);
    } else if (mathType == 1) {
      mathsAnswer = Double.parseDouble(value1) - Double.parseDouble(value2);
    } else if (mathType == 2) {
      mathsAnswer = Double.parseDouble(value1) * Double.parseDouble(value2);
    } else {
      mathsAnswer = Double.parseDouble(value1) / Double.parseDouble(value2);
    }
    answer = "" + mathsAnswer;
    notifyObservers();
  }

}
