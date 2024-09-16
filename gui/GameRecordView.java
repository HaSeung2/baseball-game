package baseBallGame.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameRecordView extends JFrame implements ActionListener {
   private final JLabel titleLb,gameRecordViewLb;
   private final JPanel titlePl, recodePl, btnPl;
   private final JButton levelChoiceBtn, homeBtn, gameStartBtn;
   private final Font font = new Font("맑은고딕", Font.BOLD, 30);

    private final ArrayList <String> gameResultLog = new ArrayList <>();
    private final int number;

    public GameRecordView(ArrayList <String> gameResultLog, int number) {
        this.gameResultLog.addAll(gameResultLog);
        this.number = number;

        setTitle("게임 기록");
        setSize(800,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,1));
        setLocationRelativeTo(null);

        levelChoiceBtn = new JButton("난이도 설정하러 가기");
        homeBtn = new JButton("시작 화면");
        gameStartBtn = new JButton("바로 게임 시작");

        JButton [] btn = {
                levelChoiceBtn,
                homeBtn,
                gameStartBtn
        };

        titleLb = new JLabel("게임 기록 보기");
        gameRecordViewLb = new JLabel("");

        titlePl = new JPanel();
        recodePl = new JPanel();
        btnPl = new JPanel(new GridLayout(1,3));
        
        titleLb.setFont(font);
        gameRecordViewLb.setFont(font);
        btnPl.setFont(font);

        gameRecordViewLb.setLayout(new FlowLayout(FlowLayout.LEFT));

        titlePl.add(titleLb);
        recodePl.add(gameRecordViewLb);
        btnPl.add(levelChoiceBtn);
        btnPl.add(homeBtn);
        btnPl.add(gameStartBtn);

        add(titlePl);
        add(recodePl);
        add(btnPl);

        for (JButton jButton : btn) {
            jButton.addActionListener(this);
        }
        if(gameResultLog.isEmpty()){
            gameRecordViewLb.setText("저장된 게임 기록이 없습니다 !");
            gameRecordViewLb.setFont(new Font("맑은 고딕",Font.BOLD,50));

        }
        else{
            for(int i=0; i < gameResultLog.size();i++){
                gameRecordViewLb.setText(gameRecordViewLb.getText()+" "+gameResultLog.get(i));
            }
        }
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == levelChoiceBtn){
            if(this.gameResultLog.isEmpty()){
                new DifficultyLevelView(new ArrayList<>());
                this.dispose();
                return;
            }
            new DifficultyLevelView(this.gameResultLog);
            this.dispose();
        }
        else if(e.getSource() == homeBtn){
            if(this.gameResultLog.isEmpty()){
                new BaseballGameHomeView();
                this.dispose();
                return;
            }
            new BaseballGameHomeView(this.gameResultLog, number);
            this.dispose();
        }
        else{
            if(this.gameResultLog.isEmpty()){
                new GameStartView(number,new ArrayList<>());
                this.dispose();
                return;
            }
            new GameStartView(number,this.gameResultLog);
            this.dispose();
        }
    }
}
