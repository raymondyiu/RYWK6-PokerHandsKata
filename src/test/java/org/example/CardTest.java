package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    @Test
    void CheckCardParseExceptions() {
        Card card;
        try {
            card = new Card("2");
        } catch (Exception e){
            assertEquals("Length size not equals 2", e.getMessage());
        }
        try {
            card = new Card("");
        } catch (Exception e){
            assertEquals("Length size not equals 2", e.getMessage());
        }
        try {
            card = new Card("1234");
        } catch (Exception e){
            assertEquals("Length size not equals 2", e.getMessage());
        }
        try {
            card = new Card("1s");
        } catch (Exception e){
            assertEquals("Cannot invoke \"org.example.CardValue.name()\" because \"this.cardValue\" is null", e.getMessage());
        }
        try {
            card = new Card("1H");
        } catch (Exception e){
            assertEquals("Cannot invoke \"org.example.CardValue.name()\" because \"this.cardValue\" is null", e.getMessage());
        }
        try {
            card = new Card("AS");
            assertEquals("AS", card.getSymbol() );
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}