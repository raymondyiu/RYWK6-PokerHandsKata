package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.junit.jupiter.api.Test;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StraightFlushTest {

    @Test
    void isGetScore(){
        StraightFlush straightFlush = new StraightFlush();
        assertEquals(8000, straightFlush.getScore());
    }
    @Test
    void isMatchCheck() {
        StraightFlush straightFlush = new StraightFlush();
        ArrayList<Card> cards = new ArrayList<Card>();
        boolean expectedResult=true;
        Card card;
        ReadCsv readCsv = new ReadCsv("StraightFlushTestcases.csv");

        List<String[]> csvTestCases = new ArrayList<>();
        csvTestCases = readCsv.readCsv();

        for (String[] item : csvTestCases) {

            expectedResult = Boolean.parseBoolean(item[0]);
            String[] cardList = item[1].split(" ");
            for (String cardStr : cardList) {

                card = new Card(cardStr);
                cards.add(card);
            }
            Collections.sort(cards, new Comparator<Card>() {
                        public int compare(final Card lhs, Card rhs) {
                            return (lhs.getCardValue().score() - rhs.getCardValue().score());
                        }

                    }
            );
            if (expectedResult) {
                assertTrue(straightFlush.isMatch(cards));
            } else {
                assertFalse(straightFlush.isMatch(cards));
            }
            cards.clear();
        }
    }

}