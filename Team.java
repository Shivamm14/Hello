package com.company;

import java.util.ArrayList;

public class Team {
    private String name;
    private int score = 0;
    private ArrayList<Player> players = new ArrayList<>();
    private int wickets = 10;

    public Team() {

    }

    public Team(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getWickets() {
        return wickets;
    }
}

// TODO
// play or chase score is a method of team or game. Since other team has no input in it. or does it?
// Design decision. Do I make bats and chases method of Team or Game class. Since it requires overs of Game, will have
// to make it too part of Team. If made it part of Game, then will have to pass bats.
// chose to make it part of team, and pass the overs as arg.
// But then have to write the getRuns function in Team, which does not look appropriate.
// So changing the decision to writing bats and chase in Game and passing Team as arg, instead of overs in Team.
// So only have to write the getRuns in Game. And can make it static too. Since it does not need any non static member.
// TODO
// making getRun function less probable towards wicket or number 7.
// TOdo
// Additional features: Toss, players-> batsman, bowler,
// TODO pushing in github
