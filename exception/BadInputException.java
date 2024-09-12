package baseBallGame.exception;

public class BadInputException extends Exception{
    public BadInputException(String e) {
        super(e+"를 다시 확인해주세요");
    }
}
