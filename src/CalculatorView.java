import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorView extends JFrame implements CalculatorObserver, ActionListener {
	CalculatorModelInterface model;
	CalculatorControllerInterface controller;
	
	private ArrayList<String> numbers = new ArrayList<String>();
	private ArrayList<String> maths = new ArrayList<String>();
	
	
	private JTextField numberField;
	
	public CalculatorView(CalculatorModelInterface model, CalculatorControllerInterface controller){
		this.model = model;
		this.controller = controller;
		model.registerObserver((CalculatorObserver) this);
	}
	
	public void createArrayLists(){
		for(int i = 0; i<10; i++){
			numbers.add("" + i);
		}
		maths.add("+");
		maths.add("-");
		maths.add("*");
		maths.add("/");
	}
	
	public void createView(){
		JFrame frame = new JFrame();
		frame.setSize(400, 400);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		numberField = new JTextField();
		numberField.setText("0");
		numberField.setVisible(true);
		frame.add(numberField, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		for(int i = 9; i > -1; i--){
			if(i==6){
				JButton button = new JButton(maths.get(0));
				button.addActionListener(this);
				panel.add(button);
			}else if(i==3){
				JButton button = new JButton(maths.get(1));
				button.addActionListener(this);
				panel.add(button);
			}else if(i==0){
				JButton button = new JButton(maths.get(2));
				button.addActionListener(this);
				panel.add(button);
			}else{
			}
				JButton button = new JButton("" + numbers.get(i));
				button.addActionListener(this);
				panel.add(button);
			}
		
		JButton button = new JButton(".");
		button.addActionListener(this);
		panel.add(button);
		
		button = new JButton("=");
		button.addActionListener(this);
		panel.add(button);
		
		button = new JButton(maths.get(3));
		button.addActionListener(this);
		panel.add(button);
		
		panel.setLayout(new GridLayout(4, 4));
		frame.add(panel);
		
		JPanel clearPanel = new JPanel();
		button = new JButton("Clear");
		button.addActionListener(this);
		clearPanel.add(button);
		frame.add(clearPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}

	
	
	//getters
	public ArrayList<String> getNumbersArray(){
		return numbers;
	}
	public ArrayList<String> getMathsArray(){
		return maths;
	}
	public String getText(){
		return numberField.getText();
	}
	
	
	//setters
	public void setText(String text){
		numberField.setText(text);
	}
	
	
	
	@Override
	public void update(String value) {
		numberField.setText(value);
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		String actionString = action.getActionCommand();
		controller.buttonPressed(actionString);		
	}
}
