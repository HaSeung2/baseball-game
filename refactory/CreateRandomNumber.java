package baseBallGame.refactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CreateRandomNumber {
    private final int digits;

    public CreateRandomNumber(){
        this(3); // 기본적으로 3자리 숫자 생성
    }

    public CreateRandomNumber(int digits){
        if(digits < 3 || digits > 5){
            throw new IllegalArgumentException("3 ~ 5만 입력.");
        }
        this.digits = digits;
    }

    public String generateRandomNumber() {
        List<Integer> list = new ArrayList<>();

        for(int i = 1; i < 10; i++){
            list.add(i);
        }

        Collections.shuffle(list);

        return list.stream()
                .limit(digits)
                .map(String::valueOf)  // Integer를 String으로 변환
                .collect(Collectors.joining(""));  // 문자열로 결합
    }
}
