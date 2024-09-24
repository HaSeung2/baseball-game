package baseBallGame.refactory;

import java.util.List;

public class OutputHandler {

    public static void welcome() {
        System.out.println("환영합니다 !");
    }

    public static void userChoiceMsg() {
        System.out.println("0. 자리수 설정하기 1. 바로 게임 시작 2. 게임 기록보기 3. 종료");
    }

    public static void startGameMsg(int numberDigits){
        System.out.println("< 게임을 시작합니다 >");
        System.out.println("중복 숫자 없는 " + numberDigits + " 자릿수 맞추기!");
    }

    public static void congratsMsg(){
        System.out.println("정답 입니다.");
    }

    public static void endGameMsg() {
        System.out.println("<게임을 종료합니다>");
        System.out.println("안녕히 가세요.");
    }

    public static void historyView(List<Integer> baseballResult, List<Integer> gameCnt) {
        for(int i = 0; i < baseballResult.size(); i++){
            System.out.println(i+1+". "+baseballResult.get(i)+" / 시도 횟수 : "+gameCnt.get(i));
        }
    }
}
