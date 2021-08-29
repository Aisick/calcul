public class CalcException extends Exception {

    private String ex = "Ошибка введенных данных";

    public CalcException() {
    }

    public CalcException(String message) {
        super(message);
    }

    public CalcException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String toString(){
        return "calc.MyException[" + ex + "]";
    }
}
