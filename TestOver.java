package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class TestOver {

    @Test
    public void test1(){
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

        Over over  = new Over();
        over.start(teamOne, teamTwo);
        System.out.println(over);
        System.out.println(teamOne.getPlayers());
        System.out.println(teamTwo.getPlayers());
    }
    @Test
    public void testEnum(){
        for(Coin coin : Coin.values()){
            System.out.println(coin); // HEADS TAILS
        }
        for(Runs runs : Runs.values()){
            System.out.println(runs + " " + runs.value());
        }
        Assertions.assertEquals(Runs.WICKET, Runs.values()[7]);
        Assertions.assertEquals(Runs.ONE, Runs.values()[1]);
        Assertions.assertEquals(Runs.TWO, Runs.values()[2]);
        Assertions.assertEquals(3, Runs.values()[3].value());
        Assertions.assertEquals(Runs.FOUR, Runs.values()[4]);

    }
    @Test
    public void testEnumRandom() {

        Random rand = new Random();
        for (int i = 0; i < 15; i++) {
            int idx = rand.nextInt(Runs.values().length);
            System.out.println(idx);
//            System.out.println(Runs.values()[idx]);

        }
    }
    @Test
    public void testGetRuns(){
        int total = 100000;
        int cnt = 0;
        for(int i = 0; i < total; i++){
            Runs runs = Util.getRuns();
            if(runs == Runs.WICKET)
                cnt++;
        }
        System.out.println(cnt + "/" + total);

        Batsman batsman = new Batsman("Virat");
        Assertions.assertEquals(true, batsman instanceof Batsman);
        cnt = 0;
        for(int i = 0; i < total; i++){
            Runs runs = Util.getRuns(batsman);
            if(runs == Runs.WICKET)
                cnt++;
        }

        System.out.println(cnt + "/" + total);

    }
}
