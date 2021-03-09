package com.company;

// Ball class will have run and the Batsman and Bowler for this ball.
public class Ball {
    private Runs runs; // runs in 1, 2, 3, 4, 5, 6 or 7 for W
    private Player batsman;
    private Player bowler;

    public Ball(Runs runs, Player batsman, Player bowler) {
        this.runs = runs;
        this.batsman = batsman;
        this.bowler = bowler;
    }

    public Runs getRuns() {
        return runs;
    }

    public Player getBatsman() {
        return batsman;
    }

    public Player getBowler() {
        return bowler;
    }

    @Override
    public String toString() {
        return "Ball{" +
                "runs=" + runs +
                ", batsman=" + batsman.getName() +
                ", bowler=" + bowler.getName() +
                '}' + '\n';
    }
}

// use enum for one, two, three, four, five, six, wicket.