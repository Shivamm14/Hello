package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestPlayer {
    @Test
    void getName() {
        Player player = new Player("Sachin");
        Assertions.assertEquals("Sachin", player.getName());
    }

    @Test
    void setName() {
        Player player = new Player("Sachin");
        Assertions.assertEquals("Sachin", player.getName());
        player.setName("Dravid");
        Assertions.assertEquals("Dravid", player.getName());

    }

    @Test
    void getScore() {
        Player player = new Player("Sachin");
        Assertions.assertEquals(0, player.getScore());
    }

    @Test
    void setScore() {
        Player player = new Player("Sachin");
        Assertions.assertEquals(0, player.getScore());
        player.setScore(50);
        Assertions.assertEquals(50, player.getScore());

    }

    @Test
    void testToString() {
        Player player = new Player("Sachin");
        System.out.println(player);
    }
}
