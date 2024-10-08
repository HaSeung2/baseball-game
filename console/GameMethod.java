package baseBallGame.console;

import baseBallGame.console.exception.BadInputException;
import baseBallGame.console.reg.Reg;

import java.util.*;

public class GameMethod{
    private final ArrayList<Integer> result = new ArrayList<>();
    private final ArrayList<Integer> replayCnt = new ArrayList<>();
    private final Reg reg = new Reg();
    private int strike;
    private int ball;
    private int randomDigits;

    public void setReplayCnt(int replayCnt){
        this.replayCnt.add(replayCnt);
    }

    public ArrayList <Integer> getReplayCnt(){
        return replayCnt;
    }

    public int getStrike(){
        return strike;
    }

    public int getBall(){
        return ball;
    }

    public ArrayList <Integer> getResult(){
        return result;
    }

    public int getRandomDigits(){
        return randomDigits;
    }

    public void resultClear(){
        result.clear();
    }

//    hashSet과 Iterator을 이용한 난수 생성
//    public String createRandomNumber(String userChoiceNumber) throws Exception{
//        String createRandomNum = "";
//        HashSet <String> hs = new HashSet<>();
//        if(reg.checkIngNumber(userChoiceNumber)){
//            int number = Integer.parseInt(userChoiceNumber);
//            if(number >= 3 && number <= 5){
//                this.randomDigits = number;
//                while(true){
//                    int num = ThreadLocalRandom.current().nextInt(1,10);
//                    hs.add(String.valueOf(num));
//                    if(hs.size() == number){
//                        break;
//                    }
//                }
//                Iterator <String> it = hs.iterator();
//                while(it.hasNext()){
//                    createRandomNum += it.next();
//                }
//                return createRandomNum;
//            }
//        }
//        throw new BadInputException("3, 4, 5중에 입력 했나요 ?");
//    }

    // 유저가 자릿수를 입력했을 때 그 입력한 값을 받아서 3 ~ 5까지의 숫자가 맞는지 확인 후
    // 맞다면 true, 아니라면 false 리턴
    public boolean checkingNumber(String userChoiceNumber) throws Exception{
        String randomNumber = "";
        if(reg.checkIngNumber(userChoiceNumber)){
            int userNumber = Integer.parseInt(userChoiceNumber);
            if(userNumber >= 3 && userNumber <= 5){
                return true;
            }
        }
        throw new BadInputException("3, 4, 5 중에서만 입력 했는지");
    }

    //랜덤 숫자 생성 메서드
    public String createRandomNumber(String userChoiceNumber){
        StringBuilder randomNumber = new StringBuilder();
        int userNumber = Integer.parseInt(userChoiceNumber);
        this.randomDigits = userNumber;

        List <String> num = new ArrayList<>();
        // Collections.shuffle로 배열의 값을 랜덤으로 뒤 섞은 후
        // subList를 이용하여 0번째부터 유저가 입력한 자릿수만큼의 수를 잘라내어 다시 num에 대입
        // 그 후 for문을 돌리면서 randomNumber에 하나하나 쌓음.
        for(int i = 1; i < 10; i++){
            num.add(i+"");
        }

        Collections.shuffle(num);

        num = num.subList(0,userNumber);

        for(String item : num){
            randomNumber.append(item);
        }
        return randomNumber.toString();
    }

    // 랜덤숫자와 유저가 입력한 숫자를 비교하여
    // 정답인지 확인하는 메서드
    public boolean filterNum(String userInputNumber) throws Exception{
        // 유저가 입력한 숫자가 숫자로만 이루어져있는지, 또 랜덤 숫자의 자릿수와 같은지
        // 메서드로 확인 후  맞다면 true 아니라면 예외처리
        if(reg.checkIngNumber(userInputNumber) && reg.duplicateCheck(userInputNumber, getRandomDigits())){
                return true;
        }
        else{
            throw new BadInputException("알맞게 입력했는지");
        }
    }

    // 볼과 스트라이크의 갯수를 세고 그 값을 저장 후
    // 스트라이크의 갯수가 랜덤 숫자의 자릿수와 같다면 정답을 맞춘 것이므로
    // true를 리턴해준다.
    // 아니라면 false 리턴 후 start 클래스에서 처리
    public boolean ballAndStrikeCheck(String randomNumber,String userInputNumber){
        List <String> userInputList = new ArrayList<>();
        List <String> randomList = new ArrayList<>();
        int strikeCount = 0;
        int ballCount= 0;

        for(int i = 0; i < userInputNumber.length(); i++){
            userInputList.add(userInputNumber.substring(i, i+1));
            randomList.add(randomNumber.substring(i, i+1));
        }

        for(int i = 0; i < randomList.size(); i++){
            for(int j = 0; j < userInputList.size(); j++){
                // 랜덤 숫자 배열의 각방마다 유저가 입력한 값을 하나 씩 넣은 배열의 0번부터 자릿수길이만큼 돌면서
                // 일치하는게 있다면
                if(randomList.get(i).equals(userInputList.get(j))){
                    //이 if문을 들어와서 그 일치하는 값이 자릿수까지도 일치하는지 확인후
                    if(randomList.get(i).equals(userInputList.get(i))){
                        // 자릿수도 일치하면 스트라이크 값 증가
                        strikeCount++;
                    }
                    // 아니라면 볼 값 증가
                    else{
                        ballCount++;
                    }
                }
            }
        }
        // 스트라이크의 갯수가 자릿수보다 작다면 정답을 맞추지 못했다는 것을 의미.
        if(strikeCount < randomDigits){
            // 스트라이크의 갯수를 저장.
            this.strike = strikeCount;
            // 볼 갯수 저장
            this.ball = ballCount;
        }
        else{
            // 정답이라면 만들어둔 리스트의 저장 해주면서 true 리턴.
            this.result.add(Integer.parseInt(userInputNumber));
            return true;
        }
        return false;
    }
}
