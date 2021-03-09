package com.company;

import java.time.temporal.TemporalAmount;
import java.util.ArrayList;

import static com.company.Game.getRuns;

// Over class has list of balls. Each ball is a Ball object.
public class Over {
    // list of balls.
    private ArrayList<Ball> balls = new ArrayList<Ball>();

    public Over() {

    }

    public ArrayList<Ball> getBalls() {
        return balls;
    }

    // for first inning overs.
    public void start(Team battingTeam, Team bowlingTeam){

        for(int i = 0; i < 6; i++){
            int runs = getRuns();
            balls.add(new Ball(runs, battingTeam.getBatter(), bowlingTeam.getBowler()));
            if(runs < 7){
                battingTeam.increaseScore(runs);
                bowlingTeam.increaseGivenRuns(runs);
                // take turns on odd runs.
                if(runs % 2 != 0){
                    battingTeam.takeTurn();
                }
            }else{
                bowlingTeam.increaseWicketTaken();
                battingTeam.increaseWicket();
             }

            if(!battingTeam.hasNextPlayer())
                return;

        }
        // take turn after over.
        battingTeam.takeTurn();
    }
    // overloaded function for second inning overs.
    public void start(Team battingTeam, Team bowlingTeam, int targetScore){
        for(int i = 0; i < 6; i++){
            int runs = getRuns();
            balls.add(new Ball(runs, battingTeam.getBatter(), bowlingTeam.getBowler()));
            if(runs < 7){
                battingTeam.increaseScore(runs);
                bowlingTeam.increaseGivenRuns(runs);
                // take turns on odd runs.
                if(runs % 2 != 0){
                    battingTeam.takeTurn();
                }
            }else{
                bowlingTeam.increaseWicketTaken();
                battingTeam.increaseWicket();
            }
            // break if all out or won.
            if(!battingTeam.hasNextPlayer() || battingTeam.getScore() > targetScore)
                return;
        }
        // take turn after over.
        battingTeam.takeTurn();
    }
}

// Or I can make Ball class, which will also store, the batsman and bowler for that ball along with runs.