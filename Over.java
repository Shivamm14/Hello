package com.company;

import java.time.temporal.TemporalAmount;
import java.util.ArrayList;

import static com.company.Game.getRuns;

// Over class has list of balls. Each ball is a Ball object.
public class Over {
    // list of balls.
    private ArrayList<Ball> balls = new ArrayList<Ball>();
    private Player bowler;
    private int totalRuns;

    public Player getBowler() {
        return bowler;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public Over() {

    }

    public ArrayList<Ball> getBalls() {
        return balls;
    }

    // for first inning overs.
    public void start(Team battingTeam, Team bowlingTeam){
        bowler = bowlingTeam.getBowler();
        for(int i = 0; i < 6; i++){
            Runs runs = Util.getRuns(battingTeam.getBatter());
            balls.add(new Ball(runs, battingTeam.getBatter(), bowlingTeam.getBowler()));
            if(runs != Runs.WICKET){
                battingTeam.increaseScore(runs.value());
                bowlingTeam.increaseGivenRuns(runs.value());
                totalRuns += runs.value();
            }else{
                bowlingTeam.increaseWicketTaken();
                battingTeam.increaseWicket();
             }

            if(battingTeam.isAllOut())
                return;
        }
        // take turn after over.
        battingTeam.takeTurn();
    }
    // overloaded function for second inning overs.
    public void start(Team battingTeam, Team bowlingTeam, int targetScore){
        bowler = bowlingTeam.getBowler();
        for(int i = 0; i < 6; i++){
            Runs runs = Util.getRuns();
            balls.add(new Ball(runs, battingTeam.getBatter(), bowlingTeam.getBowler()));
            if(runs != Runs.WICKET){
                battingTeam.increaseScore(runs.value());
                bowlingTeam.increaseGivenRuns(runs.value());
                totalRuns += runs.value();
                // take turns on odd runs.
                if(runs.value() % 2 != 0){
                    battingTeam.takeTurn();
                }
            }else{
                bowlingTeam.increaseWicketTaken();
                battingTeam.increaseWicket();
            }
            // break if all out or won.
            if(battingTeam.isAllOut() || battingTeam.getScore() > targetScore)
                return;
        }
        // take turn after over.
        battingTeam.takeTurn();
    }

    @Override
    public String toString() {
        return "Over{" +
                "balls=" + balls +
                '}' + '\n';
    }
}

// Or I can make Ball class, which will also store, the batsman and bowler for that ball along with runs.