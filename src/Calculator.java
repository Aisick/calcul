import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Calculator {

    public void init() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String inputStr = reader.readLine();
            //inputStr = inputStr.replaceAll("\\s+", "");

            DefaultOperationCalc defaultOperationCalc = parseInputUserOnOperator(inputStr);
            defaultOperationCalc.init();
            defaultOperationCalc.print();
        } catch (IOException | CalcException e) {
            System.err.println(e + " : " + e.getMessage());
            System.exit(0);
        }
    }

    private DefaultOperationCalc parseInputUserOnOperator(String inputStr) throws CalcException {

        int index = 0;
        int positionOperator = 0;
        String operator = null;

        String[] operators = {"+", "-", "*", "/"};
        char[] symbols = inputStr.toCharArray();

        for (String op : operators) {
            for (int position = 0; position < inputStr.length(); position++) {
                String symbol = String.valueOf(symbols[position]);
                if (op.equals(symbol)) {
                    positionOperator ++;
                    index = position;
                    operator = symbol;
                }
            }
        }

        if (positionOperator == 1 && index != 0) {
            return parseInputUserOnOperand(inputStr, index, operator);
        }
        else throw new CalcException("Отсутствует оператор или операторов больше одного.");
    }


    private DefaultOperationCalc parseInputUserOnOperand(String str, int index, String operator) throws CalcException {
        String firstOperand;
        String secondOperand;
        StringBuilder stringBuilder = new StringBuilder(str);
        firstOperand = stringBuilder.substring(0, index);
        secondOperand = stringBuilder.substring(index+1);

        return validation(firstOperand,operator,secondOperand);
    }

    private DefaultOperationCalc validation(String firstOperand, String operator, String secondOperand) throws CalcException {
        boolean isRim;
        boolean isArabic;

        isArabic = provArabic(firstOperand,secondOperand);

        if (isArabic) {
            return new ArabicImpl(Integer.parseInt(firstOperand),operator,Integer.parseInt(secondOperand));
        }
        else {
            isRim = provRim(firstOperand,secondOperand);
        }

        if (isRim){
            return new RimImpl(firstOperand,operator,secondOperand);
        }
        else throw new CalcException("Операнды не соответствуют условию.");

    }

    private boolean provArabic(String firstOperand, String secondOperand) {
        int firstNum;
        int secondNum;

        if(isDigit(firstOperand) && isDigit(secondOperand)) {
            firstNum = Integer.parseInt(firstOperand);
            secondNum = Integer.parseInt(secondOperand);
            return 0 < firstNum && firstNum <= 10 && 0 < secondNum && secondNum <= 10;
        }
        else {
            return false;
        }
    }

    private boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean provRim(String firstOperand, String secondOperand) {
        int countRim = 0;
        List<String> listRim = List.of("I","II","III",
                "IV","V","VI","VII","VIII","IX","X");
        for (String str : listRim) {
            if (str.equals(firstOperand)){
                countRim ++;
            }
        }
        for (String str : listRim) {
            if (str.equals(secondOperand)){
                countRim ++;
            }
        }
        return countRim == 2;

    }
}
