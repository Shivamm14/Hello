package com.company;

import java.sql.Connection;
import java.sql.Statement;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;

import static com.company.Game.getRuns;

// Over class has list of balls. Each ball is a Ball object.
public class Over {
    // list of balls.
    private ArrayList<Ball> balls = new ArrayList<Ball>();
    private Player bowler;
    private int totalRuns;
    private int gameId;
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
    public void start(int gameId, Team battingTeam, Team bowlingTeam){
        this.gameId = gameId;
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
            //TODO:
            //Write update methods for game_team, game_player
             updateGameTeam(battingTeam, bowlingTeam);
             updateGamePlayer(battingTeam.getBatter(), bowlingTeam.getBowler());

            if(battingTeam.isAllOut())
                return;
        }
        // take turn after over.
        battingTeam.takeTurn();
    }
    // overloaded function for second inning overs.
    public void start(int gameId, Team battingTeam, Team bowlingTeam, int targetScore){
        this.gameId = gameId;
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
            updateGameTeam(battingTeam, bowlingTeam);
            updateGamePlayer(battingTeam.getBatter(), bowlingTeam.getBowler());

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
    // this method updates game_team table after each ball.
    void updateGameTeam(Team battingTeam, Team bowlingTeam){

        try{
            Connection conn = Database.getConnection();
            Statement st = conn.createStatement();
            // query to update battingTeam data in game_team;
            String query = String.format("UPDATE game_team SET score = %d , wickets = %d where game_id = %d and team_id" +
                            " = %d ",
                    battingTeam.getScore(), bowlingTeam.getWicketsTaken(), gameId, battingTeam.getTeamId());

            st.executeUpdate(query);
            st.close();
        }
        catch (Exception e){
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
    void updateGamePlayer(Player batter, Player bowler){
        // this updates game_player table after each ball.
        try{
            Connection conn = Database.getConnection();
            Statement st = conn.createStatement();
            // query to update batting player data in game_player;
            String query = String.format("UPDATE game_player SET score = %d , ballsPlayed = %d where game_id = %d " +
                            "and player_id = %d ",
                    batter.getScore(), batter.getBallsBatted(), gameId, gameId, batter.getPlayerId());

            st.executeUpdate(query);

            // query to update bowler data in game_player;
            query = String.format("UPDATE game_player SET wicketsTaken = %d , ballsBowled = %d , runsGiven = %d" +
                            " where game_id = %d and player_id = %d ",
                    bowler.getWicketTaken(), bowler.getBallsBowled(), bowler.getRunsGiven(),
                    gameId, bowler.getPlayerId());

            st.executeUpdate(query);

            st.close();
        }
        catch (Exception e){
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
}

// Or I can make Ball class, which will also store, the batsman and bowler for that ball along with runs.

// I want to update the tables after each ball.
// Challenge is putting the first entry in the table.
// Because after the first entry, I will just fetch the record and update it.
// game table will be updated at the time of match start and gameId will be noted.
// no change in team or player.
// gameTeam gamePlayer gameTeamPlayer-> will be updated at the match start.
// all changes after that will be in gameTeam, and gamePlayer.
// So the solution I am applying is to initialize the the gameTeam and gamePlayer as well.
// Then in the over.start() method , after each ball I can just update the gameTeam and gamePlayer table.
// using gameId, TeamId and PlayerId.

// One Challenge remained is how to decide the gameId. By user or automatic.
// Can enter game auto matic, but then how to retrieve the gameId?
// One solution is to make another unique key gameName like userName, to fetch gameId by gameName.
// Or enter the gameId by user -> Go with this solution for now.
// Or can get the maxId will be the current game id as autoIncrement is assigning ids in increasing
// order.

// Where to put the db manipulate methods?
// For now I am putting them in the class where they are called.

// TODO
// to change getNextBowler strategy to change bowlers. Right now only last player in the list is set as bowler.