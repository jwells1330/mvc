
public class CalculatorController implements CalculatorControllerInterface{
	CalculatorModelInterface model;
	CalculatorView view;
	private boolean hasDecimal = false;
	private int math = -1;
	private String value1;
	private String value2;
	
	public CalculatorController(CalculatorModelInterface model){
		this.model = model;
		view = new CalculatorView(model, this);
		view.createArrayLists();
		view.createView();
	}
	
	public void buttonPressed(String actionString){
		//clear
		if(actionString.compareToIgnoreCase("clear") == 0){
			value1 = "" + 0;
			value2 = "" + 0;
			math = -1;
			view.setText("0");
		}
		//check for decimal
		if(actionString.compareToIgnoreCase(".") == 0){
			addDecimal();
		}
		//add numbers
		for(String number : view.getNumbersArray()){
			if(number.compareToIgnoreCase(actionString) == 0){
				if(view.getText().compareToIgnoreCase("0") == 0){
					view.setText(number);
				}else{
					view.setText(view.getText()+number);
				}
			}
		}
		for(String mathString : view.getMathsArray()){
			if(mathString.compareToIgnoreCase(actionString) == 0){
				System.out.println(mathString);
				math = view.getMathsArray().indexOf(mathString);
				System.out.println(math);
				value1 = view.getText();
				view.setText("");
			}
		}
		//equals
		if(actionString.compareToIgnoreCase("=") == 0){
			System.out.println(value1 + " math= " + math + " " + value2);
			if(math != -1){
				value2 = view.getText();
				System.out.println("Doing Math!");
				model.doMaths(math, value1, value2);
			}
		}
	}
	public void addDecimal(){
		if(!hasDecimal){
			hasDecimal = true;
			view.setText(view.getText()+".");
		}
	}
}
