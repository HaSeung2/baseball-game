package baseBallGame.refactory;

import java.util.ArrayList;
import java.util.List;

public class GameHistory {
    private final List <Integer> baseBallResult = new ArrayList<>();
    private final List <Integer> gameCnt = new ArrayList<>();

    public void setBaseBallResult(int baseBallResult) {
        this.baseBallResult.add(baseBallResult);
    }

    public void setGameCnt(int gameCnt) {
        this.gameCnt.add(gameCnt);
    }

    public List <Integer> getBaseBallResult() {
        return baseBallResult;
    }

    public List <Integer> getGameCnt() {
        return gameCnt;
    }
}
