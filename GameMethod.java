package baseBallGame;

import baseBallGame.exception.BadInputException;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

public class GameMethod{
    private final ArrayList<Integer> result = new ArrayList<>();
    private final ArrayList<Integer> replayCnt = new ArrayList<>();
    private static final String numberReg = "^[0-9]*$";
    private int strike;
    private int ball;
    private int randomDigits;

    public void setStrike(int strike){
        this.strike = strike;
    }

    public void setReplayCnt(int replayCnt){
        this.replayCnt.add(replayCnt);
    }

    public void setBall(int ball){
        this.ball = ball;
    }

    public void setRandomDigits(int randomDigits){
        this.randomDigits = randomDigits;
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
    public void setResult(int result){
        this.result.add(result);
    }

    public ArrayList <Integer> getResult(){
        return result;
    }

    public void resultClear(){
        result.clear();
    }

    // 자리수를 따로 설정하지 않고 실행했을때 기본적인 3자리수 랜덤 난수 생성
    public String createRandomNumber(){
        HashSet <String> numbers = new HashSet<>();
        String finalNumber = "";

        // Set 컬렉션에는 중복값이 저장되지 않는다.
        // 그래서 while문으로 돌리면서
        // if문으로 Set의 길이가 유저가 입력한 자릿수와 일치한다면
        // break로 while문 탈출.
        while(true){
            numbers.add(ThreadLocalRandom.current().nextInt(1,10)+"");
            if(numbers.size() == 3){
                break;
            }
        }
        Iterator <String> iter = numbers.iterator();
        for(int i = 0; i < numbers.size(); i++){
            finalNumber += iter.next();
        }
        return finalNumber;
    }

    // 유저가 자릿수를 입력했을 때 그 입력한 값을 받아서 그 값만큼의 자릿수를 가진 랜덤 숫자 생성하는 메서드
    public String createChoiceRandomNumber(String userChoiceNumber) throws Exception{
        String finalNumber = "";
        int userNumber = Integer.parseInt(userChoiceNumber);
        if(Pattern.matches(numberReg, userChoiceNumber) && (userNumber <= 5 && userNumber >= 3)){
            setRandomDigits(userNumber);
            // 셔플을 이용하여 랜덤 숫자 생성
            List <String> num = new ArrayList<>();
            for(int i = 1; i < 10; i++){
                num.add(i+"");
            }
            Collections.shuffle(num);
            num = num.subList(0,userNumber);
            for(String shuNum : num){
                finalNumber += shuNum;
            }
            return finalNumber;
        }
        throw new BadInputException("3, 4, 5 중에서만 입력 했는지");
    }

    // 랜덤숫자와 유저가 입력한 숫자를 비교하여
    // 정답인지 확인하는 메서드
    public boolean filterNum(String randomNumber,String userInput) throws Exception{
        List <String> userInputList = new ArrayList<>();
        List <String> randomList = new ArrayList<>();
        int strikeCount = 0;
        int ballCount = 0;

        // 유저가 입력한 숫자가 숫자로만 이루어져있는지, 또 랜덤 숫자의 자릿수와 같은지
        // 판별 후 맞다면 각각 스트라이크의 수와 볼의 수를 count해주는 메서드 실행.
        if(Pattern.matches(numberReg, userInput) && duplicateCheck(userInput)){
            for(int i = 0; i < userInput.length(); i++){
                  userInputList.add(userInput.substring(i, i+1));
                  randomList.add(randomNumber.substring(i, i+1));
            }
            strikeCount =  strikeCnt(randomList,userInputList);
            ballCount = ballCnt(randomList,userInputList);
            // 스트라이크의 갯수가 자릿수보다 작다면 정답을 맞추지 못했다는 것을 의미.
            if(strikeCount < randomDigits){
                // 스트라이크의 갯수를 저장.
                setStrike(strikeCount);
               if(ballCount >= 0){
                   // 볼 카운트는 이중 for문으로 돌면서 하나하나 다 검사를 하기 때문에
                   // 볼카운트 안에는 스트라이크 카운트도 같이 들어있다.
                   // 그래서 볼 카운트에 스트라이크 갯수를 빼면서 대입한다.
                   ballCount -= strikeCount;
                   // 그 볼 갯수를 저장.
                   setBall(ballCount);
               }
            }
            else{
                // 정답이라면 만들어둔 리스트의 저장 해주면서 true 리턴.
                setResult(Integer.parseInt(userInput));
                return true;
            }
        }
        else{
            throw new BadInputException("알맞게 입력했는지");
        }
        return false;
    }

    // 유저가 입력한 숫자의 자릿수가 중복 숫자가 없는지와 랜덤 숫자의 자릿수와 일치한지
    // 판별하는 메서드
    public boolean duplicateCheck(String userInput) {
        HashSet <String> numbers = new HashSet<>();
        // set에는 중복값이 들어가면 자동으로 삭제가된다.
        // 그러므로 입력받은 값중에 중복값이 있는지 확인하기 위하여
        // set 컬렉션에 저장을하고나서
        // 컬렉션의 저장된 데이터들의 배열 길이가 랜덤 숫자의 자릿수와 일치하면
        // 중복값이 없고 자릿수의  맞는 수를 입력한것이기 떄문에
        // true리턴해준다.
        // 아니라면 false가 리턴.
        for(int i = 0; i < userInput.length(); i++){
            numbers.add(userInput.substring(i, i+1));
        }
        return numbers.size() == randomDigits;
    }

    // 스트라이크 갯수 구하는 메서드
    public int strikeCnt(List<String> randomNumber, List<String>userInputList){
        int strike = 0;
        for(int i = 0; i < randomNumber.size(); i++){
            if(randomNumber.get(i).equals(userInputList.get(i))){
                strike++;
            }
        }
        return strike;
    }

    // 볼 갯수 구하는 메서드
    public int ballCnt(List<String> randomNumber, List<String>userInputList){
        int ball = 0;
        for(int i = 0; i < randomNumber.size(); i++){
            for(int j = 0; j < userInputList.size(); j++){
                if(randomNumber.get(i).equals(userInputList.get(j))){
                    ball++;
                }
            }
        }
        return ball;
    }
}
