package baseBallGame.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DifficultyLevelView extends JFrame implements ActionListener {
    JPanel titleLbPl = new JPanel();
    JPanel btnPl = new JPanel();
    JPanel userInputLbPl = new JPanel();
    JLabel titleLb, userInputLb;
    JButton [] btn = {
            new JButton("3"),
            new JButton("4"),
            new JButton("5")
    };
    JButton clearBtn = new JButton("지우기");
    JButton nothingBtn = new JButton("");
    JButton okBtn = new JButton("확인");

    private final ArrayList<String> gameResultLog = new ArrayList<>();

    public DifficultyLevelView(ArrayList <String> gameResultLog){
       this.gameResultLog.addAll(gameResultLog);
       openView();
    }

    // 보여지는 화면
    public void openView(){
        setTitle("Base ball game");
        setSize(800,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3,1));

        for(int i = 0; i < btn.length; i++){
            btnPl.add(btn[i]);
            btn[i].addActionListener(this);
        }

        btnPl.add(clearBtn);
        btnPl.add(nothingBtn);
        btnPl.add(okBtn);
        btnPl.setLayout(new GridLayout(2,3,10,30));

        titleLb = new JLabel("난이도는 3 자릿수부터 5 자릿수까지 고르실 수 있습니다");
        userInputLb = new JLabel("");

        titleLb.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        userInputLb.setFont(new Font("맑은 고딕", Font.BOLD, 100));

        titleLbPl.add(titleLb);
        userInputLbPl.add(userInputLb);

        add(titleLbPl);
        add(userInputLbPl);
        add(btnPl);

        // 지우기 버튼 클릭 시 이벤트
        clearBtn.addActionListener(e->{
            userInputLb.setText("");
        });

        // 확인 버튼 클릭 시 이벤트
        okBtn.addActionListener(e->{
            if(gameResultLog.isEmpty()){
                new GameStartView(Integer.parseInt(userInputLb.getText()),new ArrayList<>());
                this.dispose();
                return;
            }
            new GameStartView(Integer.parseInt(userInputLb.getText()),gameResultLog);
            this.dispose();
        });
        setVisible(true);
    }

    // 3 ~ 5번까지의 버튼 클릭 시 이벤트
    public void actionPerformed(ActionEvent e){
        int num = 3;
        for(int i = 0; i < btn.length; i++){
            if(e.getSource() == btn[i]){
                userInputLb.setText(num+"");
            }
            num++;
        }
    }
}
