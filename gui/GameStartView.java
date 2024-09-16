package baseBallGame.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class GameStartView extends JFrame implements ActionListener {
    private JPanel titleLbPl, userInputViewLbPl, strikeAndBallLbPl,btnPl, userInputLogLbPl, p2_3;
    private JLabel titleLb, userInputViewLb, strikeAndBallLb, inputLogTitleLb, userInputLogLb;
    private final JButton [] btn = new JButton[9];
    private final JButton clearAndRecodeBtn = new JButton("지우기");
    private final JButton hintAndHomeBtn = new JButton("");
    private final JButton okAndReplayBtn = new JButton("확인");
    private final Font font = new Font("맑은 고딕", Font.BOLD, 20);
    private int replayCnt;
    private int number;
    private String userInputNumber;

    private final ArrayList <String> gameInputLog = new ArrayList<>();
    private final ArrayList <String> gameResultLog = new ArrayList<>();

    public void setGameInputLog(String userInputNumber) {
       this.gameInputLog.add(userInputNumber);
    }

    public ArrayList<String> getGameInputLog() {
        return this.gameInputLog;
    }

    public void setGameResultLog(String userInputNumber) {
        this.gameResultLog.add(userInputNumber);
    }

    public ArrayList <String>  getGameResultLog() {
        return this.gameResultLog;
    }

    // 생성자 호출 시 실행
    public GameStartView(int number, ArrayList <String> gameResultLog) {
        this.gameResultLog.addAll(gameResultLog);
        this.number = number;
        String randomNumber = randomNumber(number);
        setView(randomNumber);

        clearAndRecodeBtn.addActionListener(e->{
            int length = userInputViewLb.getText().length();
            if(length > 0 && clearAndRecodeBtn.getText().equals("지우기")){
                if(!strikeAndBallLb.getText().equals("정답 입니다 !")){
                    strikeAndBallLb.setText("");
                    userInputViewLb.setText(userInputViewLb.getText().substring(0, length-1));
                }
            }
            else if(clearAndRecodeBtn.getText().equals("게임 기록보기")){
                new GameRecordView(this.getGameResultLog(),number);
                this.dispose();
            }
        });
        
        // 힌트보기 or 홈으로 버튼 클릭 시 이벤트
        hintAndHomeBtn.addActionListener(e->{
            if(hintAndHomeBtn.getText().equals("힌트보기")){
                hintMsg(randomNumber,number);
            }
            else if(hintAndHomeBtn.getText().equals("홈으로")){
                new BaseballGameHomeView(getGameResultLog(), number);
                this.dispose();
            }
        });

        // 확인 or 다시하기 버튼 클릭 시 이벤트
        okAndReplayBtn.addActionListener(e->{
            if(okAndReplayBtn.getText().equals("확인")){
                int strike = 0;
                int ball = 0;
                userInputNumber = userInputViewLb.getText();
                if(checkUserInputNumber(userInputNumber,randomNumber)){
                    String text = userInputLogLb.getText();
                    replayCnt++;
                    if(replayCnt >= 3){
                        hintAndHomeBtn.setText("힌트보기");
                    }
                    setGameInputLog(userInputNumber);
                    ArrayList <String> arr = getGameInputLog();
                    for(String str : arr){
                        userInputLogLb.setText(text+" "+str);
                    }
                    for(int i = 0; i < number; i++){
                        for(int j = 0; j < number; j++) {
                            if (userInputNumber.charAt(i) == randomNumber.charAt(j)) {
                                if(i == j){
                                    strike++;
                                }
                                else{
                                    ball++;
                                }
                            }
                        }
                    }
                    if(strike == number){
                        replayCnt++;
                        strikeAndBallLb.setText("정답 입니다 !");
                        userInputLogLb.setText("");
                        this.gameInputLog.clear();
                        setGameResultLog(userInputNumber);
                        clearAndRecodeBtn.setText("게임 기록보기");
                        hintAndHomeBtn.setText("홈으로");
                        okAndReplayBtn.setText("다시하기");
                    }
                    else if(strike > 0 && ball > 0){
                        strikeAndBallLb.setText(strike+" Strike "+ball+" ball!");
                    }
                    else if(strike > 0 && ball == 0){
                        strikeAndBallLb.setText(strike+" Strike !");
                    }
                    else if(strike == 0 && ball > 0 ){
                        strikeAndBallLb.setText(ball+" Ball !");
                    }
                    else{
                        strikeAndBallLb.setText("아웃 입니다.");
                    }
                }
                else{
                    strikeAndBallLb.setText("자릿수와 중복된 숫자가 있는지를 다시 확인해주세요");
                }
            }
            else{
                // 다시하기 버튼 클릭 시 지금까지의 게임 결과를 저장해둔 리스트를 같이 보내줌.
                new GameStartView(number, getGameResultLog());
                this.dispose();
            }
        });
        setVisible(true);
    }

    //gui 창 셋팅
    public void setView(String randomNumber){
        setTitle("Game Start");
        setSize(800,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4,1));
        setLocationRelativeTo(null);

        titleLb = new JLabel(number+"자릿수의 중복되지 않는 숫자를 맞춰보세요 ~");
        userInputViewLb = new JLabel();
        strikeAndBallLb = new JLabel(""+randomNumber);
        inputLogTitleLb = new JLabel("현재 게임 중 입력하신 숫자");
        userInputLogLb = new JLabel();

        titleLbPl = new JPanel();
        userInputViewLbPl = new JPanel();
        btnPl = new JPanel(new GridLayout(4,3));
        strikeAndBallLbPl = new JPanel();
        userInputLogLbPl = new JPanel(new GridLayout(3,1));
        p2_3 = new JPanel();

        titleLb.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        userInputViewLb.setFont(new Font("맑은 고딕", Font.BOLD, 50));
        strikeAndBallLb.setFont(font);
        inputLogTitleLb.setFont(font);
        userInputLogLb.setFont(font);

        titleLbPl.add(titleLb);
        userInputViewLbPl.add(userInputViewLb);
        strikeAndBallLbPl.add(strikeAndBallLb);

        int num = 1;
        for(int i = 0; i < 9; i++){
            btn[i] = new JButton(num+"");
            num++;
            btnPl.add(btn[i]);
            btn[i].addActionListener(this);
        }
        p2_3.setLayout(new GridLayout(2,1));
        p2_3.add(userInputViewLbPl);
        p2_3.add(strikeAndBallLbPl);
        btnPl.add(clearAndRecodeBtn);
        btnPl.add(hintAndHomeBtn);
        btnPl.add(okAndReplayBtn);
        userInputLogLbPl.add(inputLogTitleLb);
        userInputLogLbPl.add(userInputLogLb);

        add(titleLbPl);
        add(p2_3);
        add(userInputLogLbPl);
        add(btnPl);
    }

    // 사용자가 입력한 숫자의 자릿수와 중복 숫자의 유무를 검사 후
    // 알맞으면 true, 아니라면 false 리턴
    public boolean checkUserInputNumber(String userInputNumber, String randomNumber){
        HashSet <String> hashSet = new HashSet<>();
        if(userInputNumber.length() == randomNumber.length()){
            for(int i = 0; i < userInputNumber.length(); i++){
                hashSet.add(userInputNumber.charAt(i) + "");
            }
            if(hashSet.size() == userInputNumber.length()){
                return true;
            }
        }
        return false;
    }

    // 가운데 숫자를 *로 치환 후 팝업창에 띄워줌.
    public void hintMsg(String randomNumber, int number){
        String target = "";
        String hint = "";
        String star = "*";
        target = randomNumber.substring(1,number-1);
        for(int i = 0; i < target.length(); i++){
            hint = randomNumber.replace(target,star);
            star += "*";
        }
        JOptionPane.showMessageDialog(null,hint,"힌트 !",JOptionPane.QUESTION_MESSAGE);
    }

    // 랜덤 숫자 생성
    public String randomNumber(int number){
        HashSet <Integer> hs = new HashSet<>();
        String randomNumber = "";
        while(true){
            int num = ThreadLocalRandom.current().nextInt(1,10);
            hs.add(num);
            if(hs.size() == number){
                break;
            }
        }
        Iterator <Integer> iterator = hs.iterator();
        while(iterator.hasNext()){
            randomNumber += iterator.next();
        }
        return randomNumber;
    }


    // 1 ~ 9 버튼 클릭 시 userInputViewLb에 숫자들을 이어 붙여줌
    public void actionPerformed(ActionEvent e) {
        int num = 1;
        for(int i = 0; i < btn.length; i++){
            if(e.getSource() == btn[i]){
                userInputViewLb.setText(userInputViewLb.getText()+num);
            }
            num++;
        }
        strikeAndBallLb.setText("");
    }
}
