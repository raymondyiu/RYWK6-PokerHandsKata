package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.junit.jupiter.api.Test;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FourOfAKindTest {


    @Test
    void isGetScore(){
        FourOfAKind fourOfAKind = new FourOfAKind();
        assertEquals(7000, fourOfAKind.getScore());
    }
    @Test
    void isMatch() {

        FourOfAKind fourOfAKind = new FourOfAKind();
        ArrayList<Card> cards = new ArrayList<Card>();
        boolean expectedResult=true;
        Card card;
        ReadCsv readCsv = new ReadCsv("FourOfAKindTestcases.csv");

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
                assertTrue(fourOfAKind.isMatch(cards));
            } else {
                assertFalse(fourOfAKind.isMatch(cards));
            }
            cards.clear();
        }

    }
}