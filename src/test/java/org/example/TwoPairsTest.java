package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TwoPairsTest {

    @Test
    void isMatch() {
        TwoPairs twoPairs = new TwoPairs();
        ArrayList<Card> cards = new ArrayList<Card>();
        boolean expectedResult=true;
        Card card;
        ReadCsv readCsv = new ReadCsv("TwoPairsTestcases.csv");

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
                assertTrue(twoPairs.isMatch(cards));
            } else {
                assertFalse(twoPairs.isMatch(cards));
            }
            cards.clear();
        }
    }
}