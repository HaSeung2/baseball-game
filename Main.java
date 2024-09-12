package baseBallGame;

public class Main {
    public static void main(String[] args){
        GameStart game = new GameStart();
        boolean flag = false;
        while(!flag){
            try{
               flag = game.gameStart();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
