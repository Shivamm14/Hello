package com.company;

import java.security.SecureRandom;
import java.util.Random;

public class Util {
    static Runs [] runs = Runs.values(); // caching Runs.values();
    private static Random rand = new SecureRandom();
    public static Runs getRuns(){
        int len = runs.length;
        int idx = rand.nextInt(len);
        return runs[idx];
    }
    // overloaded getRuns to lessen the probability of wicket for batsman.
    public static Runs getRuns(Player player){
        if(player instanceof Batsman){
            Runs runs = getRuns();
            Runs runs1 = getRuns();
            if(runs == Runs.WICKET && runs1.value() < Runs.FOUR.value()){
                return runs;
            }
            if(runs != Runs.WICKET) return runs;
            return runs1;
        }else{
            return getRuns();
        }
    }
}

// strategy for lessening the probability.
// 1/8 * 4/8 = 1/16
// 1/8 * 1/8 = 1/64
// 1/8
//