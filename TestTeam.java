package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;

public class TestTeam {
    @Test
    public void testTeam(){
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player("Sachin"));
        players.add(new Player("Dravid"));
        players.add(new Player("Virat"));
        players.add(new Player("Rohit"));

        Team team = new Team("India", players);
        for(Player player : team.getPlayers()){
            System.out.println(player);
        }

    }
    @Test
    public void testHasNext() {
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player("Sachin"));
        players.add(new Player("Dravid"));
        players.add(new Player("Virat"));
        players.add(new Player("Rohit"));
        Team team = new Team("India", players);

        Assertions.assertEquals(true, team.hasNextPlayer());
        team.increaseWicket();
        Assertions.assertEquals(true, team.hasNextPlayer());
        team.increaseWicket();
        Assertions.assertEquals(true, team.hasNextPlayer());
        team.increaseWicket();
        Assertions.assertEquals(true, team.hasNextPlayer());
        team.increaseWicket();
        Assertions.assertEquals(false, team.hasNextPlayer());

    }

    @Test
    void getRunner() {
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player("Sachin"));
        players.add(new Player("Dravid"));
        players.add(new Player("Virat"));
        players.add(new Player("Rohit"));
        Team team = new Team("India", players);

        Assertions.assertEquals("Sachin", team.getBatter().getName());
        Assertions.assertEquals("Dravid", team.getRunner().getName());
        team.increaseWicket();
        Assertions.assertEquals("Virat", team.getBatter().getName());
        Assertions.assertEquals("Dravid", team.getRunner().getName());

        team.takeTurn();
        Assertions.assertEquals("Dravid", team.getBatter().getName());
        Assertions.assertEquals("Virat", team.getRunner().getName());

        team.increaseScore(10);
        Assertions.assertEquals(10, team.getBatter().getScore());

    }

    @Test
    void testGetWickets() {
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player("Sachin"));
        players.add(new Player("Dravid"));
        players.add(new Player("Virat"));
        players.add(new Player("Rohit"));
        Team team = new Team("India", players);

        Assertions.assertEquals(0, team.getWickets());
        team.increaseWicket();
        Assertions.assertEquals(1, team.getWickets());
        team.increaseWicket();
        Assertions.assertEquals(2, team.getWickets());
        // 4 players. 2 opening, 2 in inPlayers.
        //

    }

    @Test
    public void testMethods(){

    }

    @Test
    void testIncreaseScore() {
    }

    @Test
    void testIncreaseWicket() {
    }
}
// edge case on getWickets.