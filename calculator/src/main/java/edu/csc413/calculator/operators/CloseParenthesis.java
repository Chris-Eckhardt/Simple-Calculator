package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class CloseParenthesis extends Operator {

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {
        return null; //new Operand(op1.getValue() + op2.getValue() ); //this was to help my previous version of eval()

    }

}
