package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// This is the Game controller class. Which will build the game, then start it.
public class Game {
    private  int overs = 1;

    public Game(int overs) {
        this.overs = overs;
    }

    // this function will build the game by building the teams, doing the toss and then starting the match.
    public void buildStart(){
        // build teamOne
        ArrayList<Player> players = new ArrayList<Player>(Arrays.asList(
                new Batsman("Sachin"),
                new Batsman("Virat"),
                new Batsman("Rohit"),
                new Batsman("Dhoni"),
                new Bowler("Hardik")
        ));
        Team teamOne = new Team("India", players);
        // build teamTwo
        players = new ArrayList<Player>(Arrays.asList(
                new Batsman("Ab diviliears"),
                new Batsman("Ricky"),
                new Bowler("Bret lee"),
                new Bowler("Clark"),
                new Bowler("Micheal")
        ));
        Team teamTwo = new Team("Australia", players);

        // doing toss.
        if(coinToss() == 0){
            // teamOne bats
            startMatch(teamOne, teamTwo);
        }else{
            // teamTwo bats
            startMatch(teamTwo, teamOne);
        }

    }
    // this function starts the match, assuming firstTeam is batting first.
    private void startMatch(Team firstTeam, Team secondTeam){
        System.out.println(firstTeam.getName() + " is batting first. ");
        // first inning
        int ballsLeft = overs * 6;
        // repeat till all balls played or all wickets gone.
        while(ballsLeft > 0){
            int run = getRuns();
            if(run < 7){
                firstTeam.increaseScore(run);
            }else{
                // wicket
                if(!firstTeam.hasNextPlayer()){
                    break; // no more player left.
                }
                firstTeam.increaseWicket();
            }
            ballsLeft--;
            // take turns on over change
            if(ballsLeft % 6 == 0){
                firstTeam.takeTurn();
            }
            // printing game state after each ball.
            System.out.printf("Balls left: %d, score: %d wickets: %d\n", ballsLeft, firstTeam.getScore(),
                    firstTeam.getWickets());
            System.out.println(firstTeam.getBatter() + " " + firstTeam.getRunner());
        }
        System.out.println("Score: " + firstTeam.getScore());
        // second inning.
        int targetScore = firstTeam.getScore();
        ballsLeft = 6 * overs;
        // repeat till last ball or last wicket is taken or target score is reached.
        while(ballsLeft > 0 && secondTeam.getScore() < targetScore ){
            int run = getRuns();
            if(run < 7){
                secondTeam.increaseScore(run);
            }else{
                // wicket
                if(!secondTeam.hasNextPlayer()){
                    break; // no more player left.
                }
                secondTeam.increaseWicket();
            }
            ballsLeft--;
            // take turns
            if(ballsLeft % 6 == 0){
                secondTeam.takeTurn();
            }
            // printing current state of match.
            System.out.printf("Balls left: %d, score: %d Wickets: %d \n", ballsLeft, secondTeam.getScore(),
                    secondTeam.getWickets());
            System.out.println(secondTeam.getBatter() + " " + secondTeam.getRunner());
        }
        printResult(firstTeam, secondTeam);


    }
    // generate a function which returns random number between 0 and 7˳.
    public static int getRuns(){
        Random rand = new Random();
        int runs;
        runs = rand.nextInt(8);
        return runs;
    }
    // Returns 0 or 1 randomly.
    public static int coinToss(){
        Random rand = new Random();
        int toss;
        toss = rand.nextInt(2);
        return toss;
    }
    // prints the result based on the scores of the two team.
    private void printResult(Team firstTeam, Team secondTeam){
        if(firstTeam.getScore() < secondTeam.getScore()){
            System.out.println(secondTeam.getName() + " won!");
        }else if(firstTeam.getScore() > secondTeam.getScore()){
            System.out.println(firstTeam.getName() + " won!");
        }else{
            System.out.println("Draw!");
        }
    }
    // TODO
    // this function takes user input of players.
    private ArrayList<Player> getPlayers(){
        return new ArrayList<Player>();
    }

}



/*
 TODO
 Implementing players individual score.
 Implementing turn taking of players.
 Using getNextPlayer() method in Team class to get next player to play.
 Using canPlay attribute in player to get its state of whether can play or not.

 // Design decision: Would it be better to do the batter and runner inside the team itself with out exposing it in
    the Game class?
 TODO:
 Implementing Batsman and Bowler.
 Implementing constructor of Game to allow for setting overs by client.
 Edge case of hasNext. What last two are playing and no one is left on the bench. It stops. Is it right?
 Ans: Might have to remove the condition hasNext from the while loop in matchStart and put it in the wicket part of else

 TODO:
 enum for type of ball: normal, noBall, wideBall
 enum for type of run: 0, 1, 2, 3, 4, 5, 6, Wicket
 - Writing Batsman and Bowler specific fields.

 - Considering putting the playing methods of Team class in separate PlayMatch class.
 - Using txt file to load team and players data. Will be helpful in adding database.
 - Organising the code in better way. Writing more descriptive commits. Creating new branches for features.
*/
