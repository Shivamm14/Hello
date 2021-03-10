package com.company;

public enum Runs {
    ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), WICKET(7);

        private final int value;

        Runs(int value) {
            this.value = value;
        }
        public int value(){
            return value;
        }
}