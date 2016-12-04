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

  // constructor takes in modelInterface and ControllerInterface objects and
  // stores them, registers as observer of model
  public CalculatorView(CalculatorModelInterface model, CalculatorControllerInterface controller) {
    this.model = model;
    this.controller = controller;
    model.registerObserver((CalculatorObserver) this);
  }

  // creates numbers and maths arrayLists for future use
  public void createArrayLists() {
    for (int i = 0; i < 10; i++) {
      numbers.add("" + i);
    }
    maths.add("+");
    maths.add("-");
    maths.add("*");
    maths.add("/");
  }

  // creates view
  public void createView() {
    // creates frame, sets size, color, and close operation
    JFrame frame = new JFrame();
    frame.setSize(400, 400);
    frame.getContentPane().setLayout(new BorderLayout());
    frame.getContentPane().setBackground(Color.LIGHT_GRAY);
    frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    // creates textField
    numberField = new JTextField();
    numberField.setText("0");
    numberField.setVisible(true);
    frame.add(numberField, BorderLayout.NORTH);
    
    JPanel panel = new JPanel();
    // adds first 13 buttons with operation buttons at positions 4, 8, 12
    for (int i = 9; i > -1; i--) {
      if (i == 6) {
        JButton button = new JButton(maths.get(0));
        button.addActionListener(this);
        panel.add(button);
      } else if (i == 3) {
        JButton button = new JButton(maths.get(1));
        button.addActionListener(this);
        panel.add(button);
      } else if (i == 0) {
        JButton button = new JButton(maths.get(2));
        button.addActionListener(this);
        panel.add(button);
      }
      //adds numbers and action listener that calls controller.numberButtonPressed
      JButton button = new JButton("" + numbers.get(i));
      button.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
          controller.numberButtonPressed(e);
        }
      });

      panel.add(button);
    }

    //adds '.' button and action listener calls controller.addDecmial
    JButton button = new JButton(".");
    button.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        controller.addDecimal();
      }
    });
    panel.add(button);

  //adds '=' button and action listener calls controller.equals
    button = new JButton("=");
    button.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        controller.equals();
      }
    });
    panel.add(button);

  //adds last operation button and action listener
    button = new JButton(maths.get(3));
    button.addActionListener(this);
    panel.add(button);

    //sets layout of number and operations panel and adds to frame
    panel.setLayout(new GridLayout(4, 4));
    frame.add(panel);

    //creates clear button panel, adds 'clear' button and action listener calls controller.clear, adds clear button panel
    //to frame at bottom
    JPanel clearPanel = new JPanel();
    button = new JButton("Clear");
    button.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        controller.clear();
      }
    });
    clearPanel.add(button);
    frame.add(clearPanel, BorderLayout.SOUTH);

    //sets frame to visible
    frame.setVisible(true);
  }

  //Get numbersArray
  public ArrayList<String> getNumbersArray() {
    return numbers;
  }
  //Get mathsArray
  public ArrayList<String> getMathsArray() {
    return maths;
  }
  //Get text in numberField 
  public String getText() {
    return numberField.getText();
  }

  // Set text in numberField
  public void setText(String text) {
    numberField.setText(text);
  }

  //observer update method
  @Override
  public void update(String value) {
    numberField.setText(value);
  }

  //action listener for operation buttons, calls controller.mathsButtonPressed
  @Override
  public void actionPerformed(ActionEvent action) {
    controller.mathsButtonPressed(action);
  }
}
