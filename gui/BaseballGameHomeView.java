package baseBallGame.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BaseballGameHomeView extends JFrame implements ActionListener {
    private final JLabel titleLb = new JLabel();
    private final JPanel titleLbPl = new JPanel();
    private final JPanel btnPl = new JPanel();

    private final JButton [] btn= {
            new JButton("1. 난이도 설정하기"),
            new JButton("2. 바로 시작하기"),
            new JButton("3. 게임 기록 보기"),
            new JButton("4. exit")
    };

    private ArrayList<String> gameResultLog = new ArrayList<>();
    private int number;

    // 처음 시작할 때 호출하는 생성자.
    public BaseballGameHomeView() {
        openView();
    }

    // 게임 플레이 후 시작화면으로 넘어올 때 호출하는 생성자
    public BaseballGameHomeView(ArrayList<String>gameResultLog, int number) {
        this.number = number;
        this.gameResultLog = gameResultLog;
        openView();
    }

    // gui 세팅
    public void openView(){
        setTitle("Base ball game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridLayout(2,1,30,30));

        titleLb.setText("숫자 야구 게임에 오신걸 환영합니다 ~ !");
        titleLb.setFont(new Font("맑은 고딕", Font.BOLD, 40));

        for(int i = 0; i < btn.length; i++) {
            btnPl.add(btn[i]);
            btn[i].addActionListener(this);
        }

        titleLbPl.add(titleLb);
        titleLbPl.setSize(300, 300);
        add(titleLbPl);
        add(btnPl);
        setVisible(true);
    }

    // 버튼 클릭 이벤트
    public void actionPerformed(ActionEvent e) {
        int num = 1;
        for(int i = 0; i < btn.length; i++) {
            if(e.getSource() == btn[i]) {
                switch(num) {
                    case 1:
                        // 난이도 선택 버튼 클릭 시
                        if(gameResultLog.isEmpty()) {
                            // 리스트가 비어있다면 새로운 리스트 객체를 전달
                            new DifficultyLevelView(new ArrayList<>());
                            this.dispose();
                            break;
                        }
                            new DifficultyLevelView(gameResultLog);
                            this.dispose();
                            break;
                    case 2:
                        // 바로 시작하기 버튼 클릭 시
                        if(gameResultLog.isEmpty()) {
                            // 리스트가 비어있다면 새로운 리스트 객체를 전달
                            new GameStartView(3,new ArrayList<>());
                            this.dispose();
                            break;
                        }
                        new GameStartView(number, gameResultLog);
                        this.dispose();
                        break;
                    case 3:
                        // 게임 기록 보기 버튼 클릭 시
                        if(gameResultLog.isEmpty()) {
                            // 리스트가 비어있다면 새로운 리스트 객체를 전달
                            new GameRecordView(new ArrayList<>(),3);
                            this.dispose();
                            break;
                        }
                        new GameRecordView(gameResultLog,number);
                        this.dispose();
                        break;
                    case 4:
                        //exit 버튼 클릭시 종료.
                        System.exit(0);
                    default: break;
                }
            }
            num++;
        }
    }

    public static void main(String[] args) {
        new BaseballGameHomeView();
    }
}
