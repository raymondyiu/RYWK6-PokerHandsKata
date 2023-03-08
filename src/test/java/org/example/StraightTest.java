package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StraightTest {
    @Test
    void isMatch() {
        Straight straight = new Straight();
        ArrayList<Card> cards = new ArrayList<Card>();
        boolean expectedResult=true;
        Card card;
        ReadCsv readCsv = new ReadCsv("StraightTestcases.csv");

        List<String[]> csvTestCases = new ArrayList<>();
        csvTestCases = readCsv.readCsv();

        for (String[] item : csvTestCases) {

            expectedResult = Boolean.parseBoolean(item[0]);
            String[] cardList = item[1].split(" ");
            for (String cardStr : cardList) {

                card = new Card(cardStr);
                cards.add(card);
            }

            if (expectedResult) {
                assertTrue(straight.isMatch(cards));
            } else {
                assertFalse(straight.isMatch(cards));
            }
            cards.clear();
        }
    }
}