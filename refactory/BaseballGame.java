package baseBallGame.refactory;

import java.util.List;

public class BaseballGame {
    private final InputCheck inputCheck = new InputCheck();
    private final GameHistory gameHistory = new GameHistory();

    public boolean play(int digits) {
        final CreateRandomNumber createRandomNumber = new CreateRandomNumber(digits);
        String randomNum =  createRandomNumber.generateRandomNumber();
        System.out.println(randomNum);
        int strike;
        int ball;
        int gameCnt = 0;
        int num = 0;

       while(true){
           if(num == 0){
               OutputHandler.startGameMsg(randomNum.length());
           }
           num++;
           String input = InputHandler.input("숫자를 입력해주세요 : ");

           if(!inputCheck.isValidInput(input,randomNum.length())) continue;

           gameCnt++;
           strike = countStrikes(input, randomNum);
           ball = countBalls(input, randomNum);

           if(strike == randomNum.length()){
                OutputHandler.congratsMsg();
                gameHistory.setGameCnt(gameCnt);
                gameHistory.setBaseBallResult(Integer.parseInt(input));
                break;
           }
           else if(strike > 0 || ball > 0){
               System.out.println(strike +" 스트라이크 "+ ball +" 볼!");
           }
           else{
               System.out.println("아웃입니다.");
           }
       }
       return false;
    }

    private int countStrikes(String input, String randomNum) {
        int strike = 0;
        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) == randomNum.charAt(i)){
                strike++;
            }
        }
        return strike;
    }

    private int countBalls(String input, String randomNum) {
        int ball = 0;
        for(int i = 0; i < input.length(); i++){
            for(int j = 0; j < randomNum.length(); j++){
                if(i != j && input.charAt(i) == randomNum.charAt(j)){
                    ball++;
                }
            }
        }
        return ball;
    }

    public void baseballGameHistory() {
        List <Integer> baseballResult = gameHistory.getBaseBallResult();
        List <Integer> gameCnt = gameHistory.getGameCnt();
        OutputHandler.historyView(baseballResult,gameCnt);
    }
}
