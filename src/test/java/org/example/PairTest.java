package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PairTest {

    @Test
    void getScore() {
        Pair pair = new Pair();
        assertEquals(1000, pair.getScore());
    }

    @Test
    void isMatch() {
    }
}