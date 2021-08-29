public class ArabicImpl implements DefaultOperationCalc {

    private int intResult;

    private final int firstNum;
    private final int secondNum;
    private final String operator;

    public ArabicImpl(int firstNum, String operator, int secondNum) {
        this.firstNum = firstNum;
        this.secondNum = secondNum;
        this.operator = operator;
    }


    @Override
    public void init() {

        if(operator.equals("+")) {
            intResult = plus(firstNum, secondNum);
        }
        if(operator.equals("-")) {
            intResult = minus(firstNum, secondNum);
        }
        if(operator.equals("*")) {
            intResult = umn(firstNum, secondNum);
        }
        if(operator.equals("/")){
            intResult = del(firstNum, secondNum);
        }
    }

    @Override
    public void print() {
        System.out.println(intResult);
    }

    @Override
    public int plus(int a, int b) {
        return a + b;
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
}
