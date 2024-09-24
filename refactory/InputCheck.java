package baseBallGame.refactory;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class InputCheck {
    private static final String reg = "^[0-9]*$";

    public boolean numCheck(String input){
        return Pattern.matches(reg,input);
    }

    public boolean duplicateCheck(String input){
        Set<Character> set = new HashSet<>();

        for(char c : input.toCharArray()){
           if(!set.add(c)) return false;
        }
        return true;
    }

    public boolean digitsCheck(String input, int length) {
        return input.length() == length;
    }

    public boolean isValidInput(String input, int length){
        if (!numCheck(input)) {
            System.out.println("숫자는 1부터 9까지 중복되지 않는 숫자를 입력하셔야 합니다.");
            return false;
        }
        if (!duplicateCheck(input)) {
            System.out.println("중복된 숫자가 있습니다. 다시 입력해주세요!");
            return false;
        }
        if(!digitsCheck(input,length)){
            System.out.println("자릿수를 확인해 주세요.");
            return false;
        }
        return true;
    }

}
