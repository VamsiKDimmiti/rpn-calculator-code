import java.text.DecimalFormat;
import java.util.*;

public class RPNCalculator {

    public static void main(String args[]) throws Exception {
        final Scanner scr = new Scanner(System.in);
        String input;
        Stack<String> resultStack = new Stack();
        while (true) {
            input = scr.nextLine();
            DecimalFormat decimalFormat = new DecimalFormat("#####.##########");
            List<String> inputList = Arrays.asList(input.split(" "));
            Double res;
            String element;
            for (int id = 0; id < inputList.size(); id++) {
                element = inputList.get(id);
                try {
                    if (element.equals("undo")) {
                        resultStack.pop();
                    } else if (element.equals("clear")) {
                        resultStack.clear();
                    } else {
                        if (element.equals("+")) {
                            if (resultStack.size() > 1)
                                res = Double.parseDouble(resultStack.pop()) + Double.parseDouble(resultStack.pop());
                            else
                                throw new Exception("");
                        } else if (element.equals("*")) {
                            if (resultStack.size() > 1)
                                res = Double.parseDouble(resultStack.pop()) * Double.parseDouble(resultStack.pop());
                            else
                                throw new Exception("");
                        } else if (element.equals("-")) {
                            if (resultStack.size() > 1) {
                                Double lowVal = Double.parseDouble(resultStack.pop());
                                res = Double.parseDouble(resultStack.pop()) - lowVal;
                            } else
                                throw new Exception("");
                        } else if (element.equals("/")) {
                            if (resultStack.size() > 1) {
                                Double denom = Double.parseDouble(resultStack.pop());
                                res = Double.parseDouble(resultStack.pop()) / denom;
                            } else
                                throw new Exception("");
                        } else if (element.equals("sqrt")) {
                            res = Math.sqrt(Double.parseDouble(resultStack.pop()));
                        } else {
                            res = Double.parseDouble(element);
                        }
                        resultStack.push(decimalFormat.format(res));
                    }
                } catch (Exception e) {
                    System.out.println("operator "+element+" (position: "+ (2 * id + 1)+"): insufficient parameters" );
                    break;
                }
            }
            final String s = Arrays.toString(resultStack.toArray());
            final String result = s.substring(1, (s.length() - 1));
            System.out.println("stack:" + result.replace(",",""));
        }
    }
}

