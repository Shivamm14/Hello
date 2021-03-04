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
    // TODO:
    // Handle the no more players situation more effectively. Better to fail. Throw exception
    private Player getNextPlayer(){
        if(hasNextPlayer()){
            Player removed = inPlayers.get(0);
            inPlayers.remove(0);
            outPlayers.add(removed);
            return removed;
        }else{
            // crash
            return null;
        }
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
    public void increaseWicket(){
        // Assuming hasNext is true
        batter = getNextPlayer();
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
