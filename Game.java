package com.company;

import java.util.Random;

public class Game {
    private Team firstTeam = new Team();
    private Team secondTeam = new  Team();
    private int overs = 1;
    public Game(Team firstTeam, Team secondTeam) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }
    // case when its draw.
    private void printResult(){
        if(firstTeam.getScore() > secondTeam.getScore() ){

        }
        System.out.println("Result: ");
    }
    private void bats(Team team){
        int totalBalls = 6 * overs;
        int curBalls = 0;
        System.out.println(team.getName() + " bats");
        while(team.getWickets() > 1 && curBalls < totalBalls){
            int run = getRuns();
            if(run < 7){
                team.setScore(team.getScore() + run);
            }else{
                team.setWickets(team.getWickets()-1);
            }
            curBalls++;
            System.out.println("ball: " + curBalls + "  runs: " + team.getScore() + " wickets: "  + (10 - team.getWickets()));
        }
    }
    public void chases(Team team,  int targetScore){
        int totalBalls = 6 * overs;
        int curBalls = 0;
        System.out.println(team.getName() + " chases the target score of " + targetScore );
        while(team.getWickets() > 1 && curBalls < totalBalls && team.getScore() < targetScore){
            int run = getRuns();
            if(run < 7){
                team.setScore(team.getScore() + run);
            }else{
                team.setWickets(team.getWickets()-1);
            }
            curBalls++;
            System.out.println("ball: " + curBalls + "  runs: " + team.getScore() + " wickets: "  + (10 - team.getWickets()));
        }

    }

    public void start(){
        bats(firstTeam); // passing team to let it know which team to bat first.
        chases(secondTeam, firstTeam.getScore());
        printResult();
    }
    // generate a function which returns random number between 0 and 7˳.
    public static int getRuns(){
        Random rand = new Random();
        int runs;
        runs = rand.nextInt(8);
        return runs;
    }

}
