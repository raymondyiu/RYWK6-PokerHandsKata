package org.example;

public enum Rank {
    StraightFlush(8000), FourOfAKind(7000), FullHouse(6000), Flush(5000),
    Straight(4000), ThreeOfAKind(3000), TwoPairs(2000), Pair(1000);

    int score;
    Rank ( int score ) {this.score = score;}
    public int score() { return score;}

}
