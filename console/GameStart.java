package baseBallGame.console;

import baseBallGame.console.exception.BadInputException;

import java.util.ArrayList;
import java.util.Scanner;

public class GameStart {
    private int num = 0;
    private int replayCnt = 0;
    private String randomNumber = "";
    private String userChoiceDigits;
    private final GameMethod game = new GameMethod();

    public boolean gameStart() throws Exception {
        Scanner sc = new Scanner(System.in);
        while (true) {
            // 이 선택지들은 맨 처음과 문제를 맞췄을 때만 보여주기 위해
            // 이 메서드가 시작할때와 정답을 맞췄을 때에만 num을 0으로 재 할당 시켜준다.
            // 정상적으로 입력을 잘 하여 게임이 시작되면 num을 num++;로 증가 시켜줘서
            // 문제를 맞추기 전까지는 이 if문 안의 코드들은 실행되지 않는다.
            if (num == 0) {
                System.out.println("환영합니다 ! 원하시는 번호를 입력해주세요");
                System.out.print("0. 자리수 설정하기 1. 게임 시작하기  2. 게임 기록 보기  3. 종료하기 : ");
                String choiceNumber = sc.nextLine();

                switch (choiceNumber) {
                    // 0을 입력하면 3,4,5를 입력하라는 출력 문구와 함께
                    // 입력 받은 숫자를  createChoiceRandomNumber() 메서드를 호출하며 전달.
                    case "0":
                        System.out.print("3, 4, 5자릿수 중에 골라주세요 ~ : ");
                        userChoiceDigits = sc.nextLine();
                        // 3, 4, 5 중에 입력을 잘 했다면 randomNumber에 만들어온 난수가 잘 담겨있을 것이기 때문에
                        // if문으로 빈 문자열인지 확인하고 아니라면 난수의 길이를 랜덤 숫자의 자릿수를 저장해두는
                        // randomDigits에 set.
                        if(game.checkingNumber(userChoiceDigits)){
                            randomNumber = game.createRandomNumber(userChoiceDigits);
                        }
                        break;
                    case "1":
                        randomNumber = game.createRandomNumber("3");
                        userChoiceDigits = "3";
                        break;
                    case "2":
                        int resultNumber = 1;
                        System.out.println("---- 게임 기록 보기 ! -----");
                        // 게임 결과를 저장해둔 result 리스트를 가져옴
                        ArrayList<Integer> result  = game.getResult();
                        // 매 게임마다 도전 횟수를 카운트해서 저장해둔 cnt 리스트를 가져옴.
                        ArrayList<Integer> cnt =  game.getReplayCnt();
                        // for문을 돌면서 출력해준다.
                        for(int i = 0; i < result.size(); i++){
                            System.out.println(resultNumber +". "+result.get(i)+" / 도전 횟수 : "+cnt.get(i));
                            resultNumber++;
                        }
                        continue;
                    case "3":
                        // 게임 결과들을 저장해두었던 리스트를 비워 주면서 게임 종료
                        game.resultClear();
                        System.out.println("< 숫자 야구 게임을 종료합니다 >");
                        System.out.println("또 이용해주세요 !");
                        return true;
                        // 0 ~ 4 이외에 것들을 입력하면 예외처리
                    default: throw new BadInputException("숫자를 0 ~ 3까지 다시 입력해주세요");
                }
                // 테스트하기위해 만들어진 랜덤 숫자가 뭔지 sout으로 출력
                System.out.println("< 게임을 시작합니다 >");
                System.out.println("중복 숫자 없는 "+userChoiceDigits+" 자릿수 맞추기 ! ");
                System.out.println(randomNumber);
                num++;
            }
            System.out.print("숫자를 입력하세요 : ");
            String userInput = sc.nextLine();
            if(userInput.contains("0")){
                System.out.println("0은 입력하시면 안돼요 ~");
                continue;
            }
            // 정답을 맞췄을 때만 true를 리턴 하게 해뒀기 때문에
            // 이 if문 안에 들어간다면 정답을 맞췄을 때 뿐이다.
            if (game.filterNum(userInput)) {
                if(game.ballAndStrikeCheck(randomNumber,userInput)){
                    System.out.println("정답 입니다.");
                    replayCnt++;
                    game.setReplayCnt(replayCnt);
                    // 맨 위에 if문으로 다시 들어가게 하기 위해 num의 값을 0으로 초기화,
                    // 도전 횟수를 카운트해둔 replayCnt도 0으로 초기화해준다.
                    replayCnt = 0;
                    num = 0;
                    break;
                }
            }
            // 정답을 못 맞췄다면
            // 스트라이크 갯수와 볼 갯수를 가져와서
            // 출력문으로 출력
            int strike = game.getStrike();
            int ball = game.getBall();
            if (strike > 0 || ball > 0) {
                System.out.println(strike + " 스트라이크 " + ball + " 볼");
                // 아무것도 맞추지 못했다면 아웃을 출력
            } else {
                System.out.println("아웃");
            }
            replayCnt++;
        }
        return false;
    }
}
