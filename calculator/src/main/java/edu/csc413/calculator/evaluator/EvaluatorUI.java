package edu.csc413.calculator.evaluator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EvaluatorUI extends JFrame implements ActionListener {

    private TextField txField = new TextField();
    private Panel buttonPanel = new Panel();
    private String input = "";
    Evaluator evaluator = new Evaluator();
    // total of 20 buttons on the calculator,
    // numbered from left to right, top to bottom
    // bText[] array contains the text for corresponding buttons
    private static final String[] bText = {
        "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3",
        "*", "0", "^", "=", "/", "(", ")", "C", "CE"
    };

    /**
     * C  is for clear, clears entire expression
     * CE is for clear expression, clears last entry up until the last operator.
     */
    private Button[] buttons = new Button[bText.length];

    public static void main(String argv[]) {
        EvaluatorUI calc = new EvaluatorUI();
    }

    public EvaluatorUI() {
        setLayout(new BorderLayout());
        this.txField.setPreferredSize(new Dimension(600, 50));
        this.txField.setFont(new Font("Courier", Font.BOLD, 28));

        add(txField, BorderLayout.NORTH);
        txField.setEditable(false);

        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(5, 4));

        //create 20 buttons with corresponding text in bText[] array
        Button bt;
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            bt = new Button(bText[i]);
            bt.setFont(new Font("Courier", Font.BOLD, 28));
            buttons[i] = bt;
        }

        //add buttons to button panel
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            buttonPanel.add(buttons[i]);
        }

        //set up buttons to listen for mouse input
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            buttons[i].addActionListener(this);
        }

        setTitle("Calculator");
        setSize(400, 400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        switch(e.getActionCommand()) {
            case "0":
                txField.setText(input = input + "0");
                break;
            case "1":
                txField.setText(input = input + "1");
                break;
            case "2":
                txField.setText(input = input + "2");
                break;
            case "3":
                txField.setText(input = input + "3");
                break;
            case "4":
                txField.setText(input = input + "4");
                break;
            case "5":
                txField.setText(input = input + "5");
                break;
            case "6":
                txField.setText(input = input + "6");
                break;
            case "7":
                txField.setText(input = input + "7");
                break;
            case "8":
                txField.setText(input = input + "8");
                break;
            case "9":
                txField.setText(input = input + "9");
                break;
            case "+":
                txField.setText(input = input + "+");
                break;
            case "-":
                txField.setText(input = input + "-");
                break;
            case "*":
                txField.setText(input = input + "*");
                break;
            case "/":
                txField.setText(input = input + "/");
                break;
            case "^":
                txField.setText(input = input + "^");
                break;
            case "(":
                txField.setText(input = input + "(");
                break;
            case ")":
                txField.setText(input = input + ")");
                break;
            case "CE":
                if(input.length() > 0) {
                    txField.setText(input = input.substring(0, input.length() - 1));
                }
                break;
            case "C":
                txField.setText(input = "");
                break;
            case "=":
                txField.setText(String.valueOf(evaluator.eval(input)));
                break;
        }

    }
}
