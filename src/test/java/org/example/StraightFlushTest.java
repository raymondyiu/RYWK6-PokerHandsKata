package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StraightFlushTest {

    @Test
    void isMatch() {
        StraightFlush straightFlush = new StraightFlush();
        ArrayList<Card> cards = new ArrayList<Card>();
        Card card = new Card("1H");
        cards.add(card);
        assertTrue(straightFlush.isMatch(cards));
    }

    @Test
    void getScore() {
    }
}