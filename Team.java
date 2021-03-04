package com.company;

import java.util.ArrayList;
// Assuming the team will be build one time.
// with players arrayList. Players won't be added after the initial adding.
public class Team {
    private String name;
    private int score;
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Player> inPlayers = new ArrayList<Player>();
    private  ArrayList<Player> outPlayers = new ArrayList<Player>();
    private Player batter, runner;

    // Assuming more than 2 players.
    public Team(String name, ArrayList<Player> players) {
        this.name = name;
        // adding players.
        for(Player player : players){
            this.players.add(player);
            this.inPlayers.add(player);
        }
        batter = inPlayers.get(0);
        inPlayers.remove(0);
        runner = inPlayers.get(0);
        inPlayers.remove(0);
    }

    public Player getBatter() {
        return batter;
    }

    public Player getRunner() {
        return runner;
    }

    private void setBatter(Player batter) {
        this.batter = batter;
    }

    private void setRunner(Player runner) {
        this.runner = runner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
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
    // Returns the next player which the nextPlayer pointer is pointing to.
    // Assumes hasNextPlayer() is true;
    private Player getNextPlayer(){
        Player removed = inPlayers.get(0); // get the next player in line.
        inPlayers.remove(0); // remove from inline.
        return removed;
    }

    public boolean hasNextPlayer() {
        return !inPlayers.isEmpty();
    }
    public void takeTurn(){
        // Assuming batter and runner are set.
        Player temp = batter;
        batter = runner;
        runner = temp;
    }
    public void increaseScore(int runs){
        score += runs;
        batter.setScore(batter.getScore() + runs);
    }
    // this function puts the current batter in outPlayers and puts next Player from inPlayers as the current batter.
    // Will do nothing if all out.
    public void increaseWicket(){
        // checking this condition to avoid repeated adding batter in outPlayers by calling increaseWickets.
        if(outPlayers.size() < players.size()-1)
            outPlayers.add(batter); // making the current batter sit with out Players.
        // Assuming hasNext is true
        if(hasNextPlayer()){
            batter = getNextPlayer(); // getting next player in line to bat.
        }
    }
    // returns number of wickets
    public int getWickets(){
        return outPlayers.size();
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
// TODO
// Improving getNextPlayer method by assuming hasNext is true already.
// Possible error in adding already set batter and setter in out players.
// Possible solution is adding batter and setter in outPlayers in starting as well, then have to change
// getWickets to return outPlayers.size() - 2 to return current wickets.
// Another solution is add the current batter in outPlayer when wicket is gone.
// TODO
// Comment each methods and class and put more rigorous tests.