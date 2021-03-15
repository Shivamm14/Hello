package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// This is the Game controller class. Which will build the game, then start it.
public class Game {
    private  int overs;
    private ArrayList<Over> firstInningOvers = new ArrayList<Over>();
    private ArrayList<Over> secondInningOvers = new ArrayList<Over>();
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

        // first inning
        for(int i = 0; i < overs; i++){
            Over curOver = new Over();
            curOver.start(firstTeam, secondTeam);
            firstInningOvers.add(curOver);
            // if all out then break.
            if(firstTeam.isAllOut()){
                break;
            }
        }
        // second inning
        for(int i = 0; i < overs; i++){
            Over curOver = new Over();
            curOver.start(secondTeam, firstTeam, firstTeam.getScore());
            secondInningOvers.add(curOver);
            // if all out or won then break;
            if(secondTeam.isAllOut() || secondTeam.getScore() > firstTeam.getScore()){
                break;
            }
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
        System.out.println(firstTeam.getName() + " vs " + secondTeam.getName());
        System.out.println(firstTeam.getName() + " is batting first. ");
        // printing first inning overs details.
        for(Over over : firstInningOvers){
            System.out.println("Over");

            for(Ball ball : over.getBalls()){
                System.out.println("Batsman: " + ball.getBatsman().getName() + " " +  ball.getRuns());
            }
            Player bowler = over.getBowler();
            System.out.println("Bowler: " + bowler.getName() + " runs given: " + over.getTotalRuns());
        }
        System.out.println(secondTeam.getName() + " bats to chase score of " + firstTeam.getScore());
        // printing second inning overs details.
        for(Over over : secondInningOvers){
            System.out.println("Over");

            for(Ball ball : over.getBalls()){
                System.out.println("Batsman: " + ball.getBatsman().getName() + " " +  ball.getRuns());
            }
            Player bowler = over.getBowler();
            System.out.println("Bowler: " + bowler.getName() + " runs given: " + over.getTotalRuns());
        }

        if(firstTeam.getScore() < secondTeam.getScore()){
            System.out.println(secondTeam.getName() + " won!");
        }else if(firstTeam.getScore() > secondTeam.getScore()){
            System.out.println(firstTeam.getName() + " won!");
        }else{
            System.out.println("Draw!");
        }
    }
    // prints the match state
    private void showMatchState(Team firstTeam, int ballsLeft){
        // printing game state after each ball.
        System.out.printf("Balls left: %d, score: %d wickets: %d\n", ballsLeft, firstTeam.getScore(),
                firstTeam.getWickets());
        System.out.println(firstTeam.getBatter() + " " + firstTeam.getRunner());

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
 - Write method for showing match state after each ball.
 - Considering the immutability of internal objects. for eg. batter's attribute can be change by clients.
 - Relating to making defensively copying and returning the copy object.
 - making defensive copies to make internal player objects inaccessible by clients
 - Then have to consider the player's batsman or bowler type. ? what will be the effect of copies.
 - Then have to also change getters of players attributes in Team to return copies instead of actual player.

 Possible issues in adding features later on.
 - Replacing batsman and bowler  with players.
//

TODO
- To include bowler in giving runs and wicket, considering making methods in Team class, itself.
  similar to increaseScore method for batting team.

- Considered adding runsMade and runsGiven attributes in Batsman and Bowler, but that will restrict choosing a batsman
 to bowl, and vice versa. Since they are interchangeable.

- 1. Take input from file.
- 2. storing overs details. Done
- 3. firstInningsOvers, secondInningOvers. Done
- 4. Over - list of balls. 1, 2, 3, 4, 5, 6, W Done
- 5. Adding bowler data. - adding currentBowler in Team, and runsGiven and wicketsTaken Done. // nextBowler.
- 6. My design decision is based on not exposing players of Team outside. Doing everything inside Team.
- 7. Modifying getRuns for Batsman and Bowler.
- 8. Storing data in database. What to store? Scoreboard, match details,
*/
