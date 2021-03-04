package com.company;

import org.junit.jupiter.api.Test;

public class TestGame {
    @Test
    public void testGetRuns(){

        for(int i = 0; i < 10; i++){
            System.out.print(Game.getRuns() + " ");
        }
    }
    @Test
    public void testCoinToss(){
        for(int i = 0; i < 10; i++){
            System.out.print(Game.coinToss() + " ");
        }
    }
    @Test
    public  void testBuildStart(){
        Game game = new Game(2);
        game.buildStart();
    }
    @Test
    public void  testStartMatch(){

    }
}
