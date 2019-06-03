package edu.csc413.calculator.evaluator;



import edu.csc413.calculator.operators.*;

import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {
  private Stack<Operand> operandStack;
  private Stack<Operator> operatorStack;
  private StringTokenizer tokenizer;
  private static final String DELIMITERS = Operator.getKeys();

  public Evaluator() {
    operandStack = new Stack<>();
    operatorStack = new Stack<>();
  }

  public int eval( String expression ) {
    String token;

    // The 3rd argument is true to indicate that the delimiters should be used
    // as tokens, too. But, we'll need to remember to filter out spaces.
    this.tokenizer = new StringTokenizer( expression, DELIMITERS, true );

    // initialize operator stack - necessary with operator priority schema
    // the priority of any operator in the operator stack other than
    // the usual mathematical operators - "+-*/" - should be less than the priority
    // of the usual operators
    Operator initOP = new InitOperator();
    operatorStack.push(initOP);


    while ( this.tokenizer.hasMoreTokens() ) {
      // filter out spaces
      if ( !( token = this.tokenizer.nextToken() ).equals( " " )) {

        // check if token is an operand
        if ( Operand.check( token )) {
          operandStack.push( new Operand( token ));
        } else {
          // token is not operand
          if (!Operator.check(token)) {
            System.out.println("*****invalid token******");
            throw new RuntimeException("*****invalid token******");
          }

          // TODO Operator is abstract - these two lines will need to be fixed:
          // The Operator class should contain an instance of a HashMap,
          // and values will be instances of the Operators.  See Operator class
          // skeleton for an example.
          Operator newOperator = Operator.getOperator(token);

          while (operatorStack.peek().priority() >= newOperator.priority()
                  && !(newOperator instanceof OpenParenthesis)
                  && !(newOperator instanceof CloseParenthesis)) {

            // note that when we eval the expression 1 - 2 we will
            // push the 1 then the 2 and then do the subtraction operation
            // This means that the first number to be popped is the
            // second operand, not the first operand - see the following code
            process();
          }

          if (newOperator instanceof CloseParenthesis) {

            //While (the top item is not a left parenthesis)
            while (!(operatorStack.peek() instanceof OpenParenthesis)) {
              process();
            }
            //discard OpenParenthesis
            operatorStack.pop();

          } else {
            operatorStack.push(newOperator);
          }

        }
      }
    }

    // Control gets here when we've picked up all of the tokens; you must add
    // code to complete the evaluation - consider how the code given here
    // will evaluate the expression 1+2*3
    // When we have no more tokens to scan, the operand stack will contain 1 2
    // and the operator stack will have + * with 2 and * on the top;
    // In order to complete the evaluation we must empty the stacks (except
    // the init operator on the operator stack); that is, we should keep
    // evaluating the operator stack until it only contains the init operator;
    // Suggestion: create a method that takes an operator as argument and
    // then executes the while loop.
    while(!(operatorStack.peek() instanceof InitOperator )) {
      process();
    }

    return operandStack.pop().getValue();
  }

  // processes operators w/ 2 operands
  private void process() {
    Operand op2 = operandStack.pop();
    Operand op1 = operandStack.pop();
    operandStack.push( operatorStack.pop().execute( op1, op2 ) );
  }

}
