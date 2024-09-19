package baseBallGame.console.reg;

import java.util.HashSet;
import java.util.regex.Pattern;

public class Reg {
    private static final String numberReg = "^[0-9]*$";

    public boolean checkIngNumber(String number){
        return Pattern.matches(numberReg, number);
    }

    // 유저가 입력한 숫자의 자릿수가 중복 숫자가 없는지와 랜덤 숫자의 자릿수와 일치한지
    // 판별하는 메서드
    public boolean duplicateCheck(String userInputNumber, int randomDigits) {
        HashSet<String> numbers = new HashSet<>();
        // set에는 중복값이 들어가면 자동으로 삭제가된다.
        // 그러므로 입력받은 값중에 중복값이 있는지 확인하기 위하여
        // set 컬렉션에 저장을하고나서
        // 컬렉션의 저장된 데이터들의 배열 길이가 랜덤 숫자의 자릿수와 일치하면
        // 중복값이 없고 자릿수의  맞는 수를 입력한것이기 떄문에
        // true리턴해준다.
        // 아니라면 false가 리턴.
        for(int i = 0; i < userInputNumber.length(); i++){
            numbers.add(userInputNumber.substring(i, i+1));
        }
        return numbers.size() == randomDigits;
    }
}
