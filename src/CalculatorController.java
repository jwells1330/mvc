import java.awt.event.ActionEvent;

public class CalculatorController implements CalculatorControllerInterface {
  CalculatorModelInterface model;
  CalculatorView view;
  private boolean hasDecimal = false;
  private int math = -1;
  private String value1;
  private String value2;

  // constructor takes in a model and stores it, creates a new view and calls
  // init methods of that view
  public CalculatorController(CalculatorModelInterface model) {
    this.model = model;
    view = new CalculatorView(model, this);
    view.createArrayLists();
    view.createView();
  }

  // called when number button is pressed in view, checks which number button
  // was pressed, and puts that number
  // in the text field
  public void numberButtonPressed(ActionEvent action) {
    String actionString = action.getActionCommand();

    for (String number : view.getNumbersArray()) {
      if (number.compareToIgnoreCase(actionString) == 0) {
        if (view.getText().compareToIgnoreCase("0") == 0) {
          view.setText(number);
        } else {
          view.setText(view.getText() + number);
        }
      }
    }
  }

  // called when operation button pressed in view, checks which operation button
  // pressed and stores the correct
  // operation as an int based on index in the mathsArray
  public void mathsButtonPressed(ActionEvent action) {
    String actionString = action.getActionCommand();

    for (String mathString : view.getMathsArray()) {
      if (mathString.compareToIgnoreCase(actionString) == 0) {
        math = view.getMathsArray().indexOf(mathString);
        value1 = view.getText();
        view.setText("");
      }
    }
  }

  // called when decimal button pressed in view, checks if decimal already
  // exists and if not adds to text field
  public void addDecimal() {
    if (!hasDecimal) {
      hasDecimal = true;
      view.setText(view.getText() + ".");
    }
  }

  // called when clear button pressed in view, sets values and operation to
  // default and clears text field
  public void clear() {
    value1 = "" + 0;
    value2 = "" + 0;
    math = -1;
    view.setText("0");
  }

  // called when equals button pressed in view, checks if operation button
  // pressed and if so, calls doMaths method in model
  public void equals() {
    if (math != -1) {
      value2 = view.getText();
      model.doMaths(math, value1, value2);
    }
  }
}
