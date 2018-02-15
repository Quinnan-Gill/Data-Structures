package cs445.a2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * This class uses two stacks to evaluate an infix arithmetic expression from an
 * InputStream. It should not create a full postfix expression along the way; it
 * should convert and evaluate in a pipelined fashion, in a single pass.
 */
public class InfixExpressionEvaluator {
    // Tokenizer to break up our input into tokens
    StreamTokenizer tokenizer;

    // Stacks for operators (for converting to postfix) and operands (for
    // evaluating)
    StackInterface<Character> operatorStack;
    StackInterface<Double> operandStack;

    private boolean[] justPushed;

    /**
     * Initializes the evaluator to read an infix expression from an input
     * stream.
     * @param input the input stream from which to read the expression
     */
    public InfixExpressionEvaluator(InputStream input) {
        // Initialize the tokenizer to read from the given InputStream
        tokenizer = new StreamTokenizer(new BufferedReader(
                        new InputStreamReader(input)));
        // StreamTokenizer likes to consider - and / to have special meaning.
        // Tell it that these are regular characters, so that they can be parsed
        // as operators
        tokenizer.ordinaryChar('-');
        tokenizer.ordinaryChar('/');

        //Checks for legitimate input streams
        justPushed = new boolean[4];
        //0. operandJustPushed = false;
        //1. operatorJustPushed = false;
        //2. closedBracketJustPushed = false;
        //3. openBracketJustPushed = false;
        // Allow the tokenizer to recognize end-of-line, which marks the end of
        // the expression
        tokenizer.eolIsSignificant(true);

        // Initialize the stacks
        operatorStack = new ArrayStack<Character>();
        operandStack = new ArrayStack<Double>();
    }

    /**
     * Parses and evaluates the expression read from the provided input stream,
     * then returns the resulting value
     * @return the value of the infix expression that was parsed
     */
    public Double evaluate() throws ExpressionError {
        // Get the first token. If an IO exception occurs, replace it with a
        // runtime exception, causing an immediate crash.
        try {
            tokenizer.nextToken();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Continue processing tokens until we find end-of-line
        while (tokenizer.ttype != StreamTokenizer.TT_EOL) {
            // Consider possible token types
            switch (tokenizer.ttype) {
                case StreamTokenizer.TT_NUMBER:
                    // If the token is a number, process it as a double-valued
                    // operand
                    handleOperand((double)tokenizer.nval);
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                case '^':
                    // If the token is any of the above characters, process it
                    // is an operator
                    handleOperator((char)tokenizer.ttype);
                    break;
                case '(':
                case '<':
                    // If the token is open bracket, process it as such. Forms
                    // of bracket are interchangeable but must nest properly.
                    // System.out.println("Handling open bracket");
                    handleOpenBracket((char)tokenizer.ttype);
                    break;
                case ')':
                case '>':
                    // If the token is close bracket, process it as such. Forms
                    // of bracket are interchangeable but must nest properly.
                    handleCloseBracket((char)tokenizer.ttype);
                    break;
                case StreamTokenizer.TT_WORD:
                    // If the token is a "word", throw an expression error
                    switch(StreamTokenizer.TT_WORD) {
                      case "tan":
                      case "sin":
                      case "cos":
                        handleUnary((String)StreamTokenizer.TT_WORD);

                      default:
                        throw new ExpressionError("Unrecognized token: " +
                                    tokenizer.sval);
                    }
                default:
                    // If the token is any other type or value, throw an
                    // expression error
                    throw new ExpressionError("Unrecognized token: " +
                                    String.valueOf((char)tokenizer.ttype));
            }

            // Read the next token, again converting any potential IO exception
            try {
                tokenizer.nextToken();
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
        }

        // Almost done now, but we may have to process remaining operators in
        // the operators stack
        handleRemainingOperators();

        // Return the result of the evaluation
        double result = operandStack.pop();
        if (!operandStack.isEmpty()) {
          String extraChar = "";
          while(!operandStack.isEmpty()) {
            extraChar += " " + operandStack.pop();
          }
          throw new ExpressionError("Invalid expression there was extra character(s):" + extraChar);
        } else {
          return result;
        }
    }

    /**
     * This method is called when the evaluator encounters an operand. It
     * manipulates operatorStack and/or operandStack to process the operand
     * according to the Infix-to-Postfix and Postfix-evaluation algorithms.
     * @param operand the operand token that was encountered
     */
    void handleOperand(double operand) {
        // System.out.println("Handling operand: " + operand);
        if (/*!operandJustPushed*/ justPushed[0]) {
          throw new ExpressionError("Operand followed by another operand");
        } else if (/*!closedBracketJustPushed*/ justPushed[2]) {
          throw new ExpressionError("Closed Bracket followed by operand");
        } else {
          operandStack.push(operand);
          justPushed = new boolean[4];
          justPushed[0] = true;
        }

    }

    /**
     * This method is called when the evaluator encounters an operator. It
     * manipulates operatorStack and/or operandStack to process the operator
     * according to the Infix-to-Postfix and Postfix-evaluation algorithms.
     * @param operator the operator token that was encountered
     */
    void handleOperator(char operator) {
        // System.out.println("Handling operator: " + operator);
        if(/*!operatorJustPushed*/ justPushed[1]) {
          throw new ExpressionError("Operator followed by another operator");
        } else if(/*!openBracket*/ justPushed[3]) {
          throw new ExpressionError("Open bracket followed by operator");
        } else {
          operatorStack.push(operator);
          justPushed = new boolean[4];
          justPushed[1] = true;
        }
    }

    /**
     * This method is called when the evaluator encounters an open bracket. It
     * manipulates operatorStack and/or operandStack to process the open bracket
     * according to the Infix-to-Postfix and Postfix-evaluation algorithms.
     * @param openBracket the open bracket token that was encountered
     */
    void handleOpenBracket(char openBracket) {
        // System.out.println("Handling open bracket: " + openBracket);
        if(/*!operandJustPushed*/ justPushed[0]) {
          throw new ExpressionError("Operand followed by a bracket");
        }else if (/*!closedBracketJustPushed*/ justPushed[2]) {
          throw new ExpressionError("Closed bracket followed by open bracket");
        }else {
          operatorStack.push(openBracket);
          justPushed = new boolean[4];
          justPushed[3] = true;
        }
    }

    /**
     * This method is called when the evaluator encounters a close bracket. It
     * manipulates operatorStack and/or operandStack to process the close
     * bracket according to the Infix-to-Postfix and Postfix-evaluation
     * algorithms.
     * @param closeBracket the close bracket token that was encountered
     */
    void handleCloseBracket(char closeBracket) {
      if (/*!operatorJustPushed*/ justPushed[1]) {
        throw new ExpressionError("Operator followed by a closed bracket");
      }else if (/*openBracketJustPushed*/ justPushed[3]) {
        throw new ExpressionError("Open bracket followed by a closed bracket");
      }else {
        if(closeBracket == '>') {
          closeBracketHelper('<', '(');
        }else {
          closeBracketHelper('(', '<');
        }
        justPushed = new boolean[4];
        justPushed[2] = true;
      }
    }

    void handleUnary(String str) {
      if (str.equals("tan")) {
        operatorStack.push('t');
      }else if(str.equals("cos")) {
        operatorStack.push('c');
      }else if (str.equals("sin")) {
        operatorStack.push('s');
      }
    }
    /**
     * When a close bracket is encountered pop everything until
     * the open bracket is found. Then pop the open bracket
     *
     *@param openPair The open bracket that must be encountered
     */
    private void closeBracketHelper(char openPair, char avoid) {
      // int counter = 0;
      while(operatorStack.peek() != openPair) {
        // counter++;
        // System.out.println("Did it how many times " + counter);
        // System.out.println("What's next " + operatorStack.peek());
        if(operatorStack.peek() == avoid) {
          throw new ExpressionError("Mismatched of brackets");
        }
        stage();
      }
      operatorStack.pop();
      String testUnary = "tcs";
      if(testUnary.indexOf(operatorStack.peek()) != -1) {
        unary(operatorStack.pop());
      }
    }
    /**
     * This method is called when the evaluator encounters the end of an
     * expression. It manipulates operatorStack and/or operandStack to process
     * the operators that remain on the stack, according to the Infix-to-Postfix
     * and Postfix-evaluation algorithms.
     */
    void handleRemainingOperators() {
        // System.out.println("Handling the left over variables");
        while(!(operatorStack.isEmpty() || operandStack.isEmpty())) {
          stage();
        }

    }

    /**
     * This method is an intermediate for to stage the characters
     * to be evaluated when it comes to un popping the stack.
     */
    private void stage(){
      double operand1;
      double operand2;
      if(!operandStack.isEmpty())
        operand2 = operandStack.pop();
      else
        throw new ExpressionError("Invalid number of operators: ");
      if(!operandStack.isEmpty())
        operand1 = operandStack.pop();
      else
        throw new ExpressionError("Invalid number of operators");
      char operator = operatorStack.pop();
      operandStack.push(operation(operand1, operand2, operator));

    }

    private void unary(char type) {
      double result = 0;
      if(type == 't') {
        result = Math.tan(operandStack.pop());
      }else if(type == 'c') {
        result = Math.cos(operandStack.pop());
      }else {
        result = Math.sin(operandStack.pop());
      }
      operandStack.push(result);
    }

    /**
     * This method does the binary operation on with the two values
     * passed in.
     *
     *@param value1 left side of the equation
     *@param value2 right side of the equation
     *@param op the operation being performed
     *@return A double value which is the result of the operation
     */
    private double operation(double value1, double value2, char op){
      // System.out.println(value1 + "" + op + value2);
      if (op == '+') {

        return value1 + value2;
      } else if (op == '-') {
        return value1 - value2;
      } else if (op == '/') {
        if (value2 == 0)
          throw new ExpressionError("Divide by zero error");
        return value1 / value2;
      } else if (op == '*'){
        return value1 * value2;
      } else {
        return Math.pow(value1, value2);
      }
    }

    /**
     * Creates an InfixExpressionEvaluator object to read from System.in, then
     * evaluates its input and prints the result.
     * @param args not used
     */
    public static void main(String[] args) {
        System.out.println("Infix expression:");
        InfixExpressionEvaluator evaluator =
                        new InfixExpressionEvaluator(System.in);
        Double value = null;
        try {
            value = evaluator.evaluate();
        } catch (ExpressionError e) {
            System.out.println("ExpressionError: " + e.getMessage());
        }
        if (value != null) {
            System.out.println(value);
        } else {
            System.out.println("Evaluator returned null");
        }
    }

}
