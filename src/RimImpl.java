import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RimImpl implements DefaultOperationCalc {

    private final String firstOperand;
    private final String secondOperand;
    private final String operator;
    private int intResult;
    private String strResult;

    public RimImpl(String firstOperand, String operator, String secondOperand){
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.operator = operator;
    }
    @Override
    public void init() throws CalcException {

        int firstNum = parseToArabic(firstOperand);
        int secondNum = parseToArabic(secondOperand);

        if(operator.equals("+")) intResult = plus(firstNum, secondNum);
        if(operator.equals("-")) intResult = minus(firstNum, secondNum);
        if(operator.equals("*")) intResult = umn(firstNum, secondNum);
        if(operator.equals("/")) intResult = del(firstNum, secondNum);

        strResult = toRim(intResult);

    }

    @Override
    public int plus(int a, int b) {
        return a+b;
    }

    @Override
    public int minus(int a, int b) {
        return a-b;
    }

    @Override
    public int del(int a, int b) {
        return a/b;
    }

    @Override
    public int umn(int a, int b) {
        return a*b;
    }

    private int parseToArabic(String operand) {
        int arabicNumber = -1;

        Map<String, Integer> mapRim = new HashMap<>();
        mapRim.put("I",1);
        mapRim.put("II",2);
        mapRim.put("III",3);
        mapRim.put("IV",4);
        mapRim.put("V",5);
        mapRim.put("VI",6);
        mapRim.put("VII",7);
        mapRim.put("VIII",8);
        mapRim.put("IX",9);
        mapRim.put("X",10);

        for(Map.Entry<String,Integer> p : mapRim.entrySet()){
            if(p.getKey().equals(operand)) arabicNumber = p.getValue();
        }

        return arabicNumber;
    }

    private String toRim(int intRez) throws CalcException {

        if ((intRez <= 0) || (intRez > 100)) {
            throw new CalcException("Полученное значение не в диапазоне (0,100)");
        }

        List<RImEnum> romanNumerals = RImEnum.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((intRez > 0) && (i < romanNumerals.size())) {
            RImEnum currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= intRez) {
                sb.append(currentSymbol.name());
                intRez -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    @Override
    public void print() {
        System.out.println(strResult);
    }
}
