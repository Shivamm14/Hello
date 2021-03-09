package com.company;
// this stores Player info
public class Player {
    private String name;
    private int score, bowlsBatted;
    private int runsGiven;
    private int wicketTaken;
    private int ballsBowled;

    public void setWicketTaken(int wicketTaken) {
        this.wicketTaken = wicketTaken;
    }

    public void setBowlsBatted(int bowlsBatted) {
        this.bowlsBatted = bowlsBatted;
    }

    public void setRunsGiven(int runsGiven) {
        this.runsGiven = runsGiven;
    }



    public void setBallsBowled(int ballsBowled) {
        this.ballsBowled = ballsBowled;
    }

    public int getBowlsBatted() {
        return bowlsBatted;
    }

    public int getRunsGiven() {
        return runsGiven;
    }

    public int getWicketTaken() {
        return wicketTaken;
    }

    public int getBallsBowled() {
        return ballsBowled;
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
