package edu.csc413.calculator.operators;



import edu.csc413.calculator.evaluator.Operand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Operator {
    // The Operator class should contain an instance of a HashMap
    // This map will use keys as the tokens we're interested in,
    // and values will be instances of the Operators.
    // ALL subclasses of operator MUST be in their own file.
    // Example:
    // Where does this declaration go? What should its access level be?
    // Class or instance variable? Is this the right declaration?
    // HashMap operators = new HashMap();
    // operators.put( "+", new AddOperator() );
    // operators.put( "-", new SubtractOperator() );

    private static HashMap<String, Operator> operators;

    static {
        operators = new HashMap();
        // operators.put( "#", new InitOperator() ); // this is not needed
        operators.put( "+", new AddOperator() );
        operators.put( "-", new SubtractOperator() );
        operators.put( "*", new MultiplyOperator() );
        operators.put( "/", new DivideOperator() );
        operators.put( "^", new PowerOperator() );
        operators.put( "(", new OpenParenthesis() );
        operators.put( ")", new CloseParenthesis() );
    }

    public abstract int priority();
    public abstract Operand execute(Operand op1, Operand op2 );


    /**
     * determines if a given token is a valid operator.
     * please do your best to avoid static checks
     * for example token.equals("+") and so on.
     * Think about what happens if we add more operators.
     */
    public static boolean check( String token ) {
        if(operators.containsKey(token)) {
            return true;
        } else {
            return false;
        }
    }


    public static Operator getOperator(String token){
        return operators.get(token);
    }

    //returns keys for for use in delimiting or other..
    public static String getKeys() {
        String keys = "";
        List<String> list = new ArrayList<>(operators.keySet());
        for (int i = 0; i < list.size(); i++)
            keys = keys + list.get(i);

        // a space is added for delimiting purposes
        keys = keys + " ";
        // System.out.println(keys); // TEST to make sure getKeys() worked
        return keys;
    }
}
