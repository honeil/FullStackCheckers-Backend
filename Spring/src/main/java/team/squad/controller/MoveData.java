package team.squad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import team.squad.Model.Move;

/**
 * Created by mkulima on 3/7/17.
 */
public class MoveData {

    private int xPositionInitial;
    private int yPositionInitial;
    private int xPositionDesired;
    private int yPositionDesired;
    private int[] hasMoved;

    public MoveData() {}

    public int getxPositionInitial() {
        return xPositionInitial;
    }

    public void setxPositionInitial(int xPositionInitial) {
        this.xPositionInitial = xPositionInitial;
    }

    public int getyPositionInitial() {
        return yPositionInitial;
    }

    public void setyPositionInitial(int yPositionInitial) {
        this.yPositionInitial = yPositionInitial;
    }

    public int getxPositionDesired() {
        return xPositionDesired;
    }

    public void setxPositionDesired(int xPositionDesired) {
        this.xPositionDesired = xPositionDesired;
    }

    public int getyPositionDesired() {
        return yPositionDesired;
    }

    public void setyPositionDesired(int yPositionDesired) {
        this.yPositionDesired = yPositionDesired;
    }

    //@Autowired
    public void updateMove(Move move){
        hasMoved = new int[]{move.getxPositionFinal(),move.getyPositionFinal()};
    }

    public int[] getHasMoved() {
        return hasMoved;
    }
}
