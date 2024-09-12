package baseBallGame;

import baseBallGame.exception.BadInputException;

import java.util.ArrayList;
import java.util.Scanner;

public class GameStart {
    private int num = 0;
    private int replayCnt = 0;
    private String randomNumber = "";
    private final GameMethod game = new GameMethod();

    public boolean gameStart() throws Exception {
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (num == 0) {
                game.setRandomDigits(3);
                randomNumber = game.createRandomNumber();
                System.out.println("환영합니다 ! 원하시는 번호를 입력해주세요");
                System.out.print("0. 자리수 설정하기 1. 게임 시작하기  2. 게임 기록 보기  3. 종료하기 : ");
                String choiceNumber = sc.nextLine();
                switch (choiceNumber) {
                    case "0":
                        System.out.print("3, 4, 5자릿수 중에 골라주세요 ~ : ");
                        String userChoiceNumber = sc.nextLine();
                        randomNumber = game.userChoiceRandomSetting(userChoiceNumber);
                        if(randomNumber != ""){
                            game.setRandomDigits(randomNumber.length());
                        }
                        System.out.println("< 게임을 시작합니다 >");
                        System.out.println("중복 숫자 없는 "+userChoiceNumber+" 자릿수 맞추기 ! ");
                        break;
                    case "1":
                        System.out.println("< 게임을 시작합니다 >");
                        System.out.println("중복 숫자 없는 3자릿수 맞추기 !");
                        break;
                    case "2":
                        int num = 1;
                        System.out.println("---- 게임 기록 보기 ! -----");
                        ArrayList<Integer> result = new ArrayList<>();
                        ArrayList<Integer> cnt = new ArrayList<>();
                        result = game.getResult();
                        cnt = game.getReplayCnt();
                        for(int i = 0; i < result.size(); i++){
                            System.out.println(num +". "+result.get(i)+" / 도전 횟수 : "+cnt.get(i));
                            num++;
                        }
                        continue;
                    case "3":
                        game.resultClear();
                        System.out.println("< 숫자 야구 게임을 종료합니다 >");
                        System.out.println("또 이용해주세요 !");
                        return true;
                    default: throw new BadInputException("숫자를 0 ~ 4까지 다시 입력해주세요");
                }
                System.out.println(randomNumber);
                num++;
            }
            System.out.print("숫자를 입력하세요 : ");
            String userInput = sc.nextLine();
            if (game.filterNum(randomNumber,userInput)) {
                System.out.println("정답 입니다.");
                replayCnt++;
                game.setReplayCnt(replayCnt);
                replayCnt = 0;
                num = 0;
                break;
            }
            int strike = game.getStrike();
            int ball = game.getBall();
            if (strike > 0 && ball > 0) {
                System.out.println(strike + " 스트라이크 " + ball + " 볼");
                replayCnt++;
            } else if (strike > 0 && ball == 0) {
                System.out.println(strike + " 스트라이크");
                replayCnt++;
            } else if (strike == 0 && ball > 0) {
                System.out.println(ball + " 볼");
                replayCnt++;
            } else {
                System.out.println("아웃");
                replayCnt++;
            }
        }
        return false;
    }
}
