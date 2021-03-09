package com.company;

import java.util.Random;

public class Util {
    static Runs [] runs = Runs.values(); // caching Runs.values();
    public static Runs getRuns(){
        Random rand = new Random();
        int idx = rand.nextInt(7);
        return runs[idx];
    }
}
