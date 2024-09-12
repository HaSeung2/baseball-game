package baseBallGame;

import baseBallGame.exception.BadInputException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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

    public ArrayList<Integer> getResult(){
        return result;
    }

    public void resultClear(){
        result.clear();
    }

    // 자리수를 따로 설정하지 않고 실행했을때 기본적인 3자리수 랜덤 난수 생성
    public String createRandomNumber(){
        HashSet <String> numbers = new HashSet<>();
        String finalNumber = "";
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

    public String userChoiceRandomSetting(String userChoiceNumber) throws Exception{
        String finalNumber = "";
        int userNumber = Integer.parseInt(userChoiceNumber);
        if(Pattern.matches(numberReg, userChoiceNumber) && (userNumber <= 5 && userNumber >= 3)){
            HashSet <String> numbers = new HashSet<>();
            setRandomDigits(userNumber);
            while(true){
                numbers.add(ThreadLocalRandom.current().nextInt(1,10)+"");
                if(numbers.size() == userNumber){
                    break;
                }
            }
            Iterator <String> iter = numbers.iterator();
            for(int i = 0; i < numbers.size(); i++){
                finalNumber += iter.next();
            }
            return finalNumber;
        }
        throw new BadInputException("3, 4, 5 중에서만 입력 했는지");
    }

    public boolean filterNum(String randomNumber,String userInput) throws Exception{
        List <String> userInputList = new ArrayList<>();
        List <String> randomList = new ArrayList<>();
        int strikeCount = 0;
        int ballCount = 0;

        if(Pattern.matches(numberReg, userInput) && duplicateCheck(userInput)){
            for(int i = 0; i < userInput.length(); i++){
                  userInputList.add(userInput.substring(i, i+1));
                  randomList.add(randomNumber.substring(i, i+1));
            }
            strikeCount =  strikeCnt(randomList,userInputList);
            ballCount = ballCnt(randomList,userInputList);
            if(strikeCount < randomDigits){
                setStrike(strikeCount);
               if(ballCount >= 0){
                   ballCount -= strikeCount;
                   setBall(ballCount);
               }
            }
            else{
                setResult(Integer.parseInt(userInput));
                return true;
            }
        }
        else{
            throw new BadInputException("알맞게 입력했는지");
        }
        return false;
    }

    public boolean duplicateCheck(String userInput) {
        HashSet <String> numbers = new HashSet<>();
        for(int i = 0; i < userInput.length(); i++){
            numbers.add(userInput.substring(i, i+1));
        }
        return numbers.size() == randomDigits;
    }

    public int strikeCnt(List<String> randomNumber, List<String>userInputList){
        int strike = 0;
        for(int i = 0; i < randomNumber.size(); i++){
            if(randomNumber.get(i).equals(userInputList.get(i))){
                strike++;
            }
        }
        return strike;
    }

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
