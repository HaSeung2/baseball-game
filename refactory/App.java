package baseBallGame.refactory;

public class App {
    public static void main(String[] args) throws Exception {
        BaseballGame game = new BaseballGame();
        InputCheck check = new InputCheck();

        boolean flag = false;
        while(!flag){
            OutputHandler.welcome();
            OutputHandler.userChoiceMsg();
            String input = InputHandler.input("숫자를 입력 : ");
            switch (input){
                case "0" :
                    String userInputDigits = InputHandler.input("3 ~ 5 까지 자릿 수를 입력해주세요 : ");
                    flag = inputCheck(userInputDigits,game,check);
                    break;
                case "1" :
                    flag = game.play(3);
                    break;
                case "2" :
                    game.baseballGameHistory();
                    continue;
                case "3" :
                    OutputHandler.endGameMsg();
                    return;
                default :
                    System.out.println("알맞은 숫자만 입력");
            }
        }
    }

    private static boolean inputCheck(String input, BaseballGame game, InputCheck check){
        if(check.numCheck(input) && !(input.isEmpty())){
            int digits = Integer.parseInt(input);
            if(digits >= 3 && digits <= 5){
                return game.play(digits);
            }
            else{
                System.out.println("3부터 5까지의 숫자만 입력해주세요.");
            }
        }
        else{
            System.out.println("숫자만 입력해주세요.");
        }
            return false;
    }
}
