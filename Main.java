package com.company;

import java.util.Random;
public class Main {

    public static void main(String[] args) {
	// write your code here
        Team firstTeam = new Team("India");
        Team secondTeam = new Team("Australia");
        Game game = new Game(firstTeam, secondTeam);
        game.start();
    }

}
