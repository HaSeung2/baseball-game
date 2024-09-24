package baseBallGame.refactory;

import java.util.Scanner;

public class InputHandler {

    public static String input(String msg) {
        Scanner sc = new Scanner(System.in);
        System.out.print(msg);
        return sc.nextLine();
    }
}
