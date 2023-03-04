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

class StraightFlushTest {

    public List<String[]> readCsv(String filename) {
        List<String[]> allData = new ArrayList<>();
        try {
            Path path = Paths.get(
                    ClassLoader.getSystemResource(filename).toURI());
            Reader reader = Files.newBufferedReader(path);
            CSVReader csvReader = new CSVReaderBuilder(reader).build();
            allData = csvReader.readAll();

        } catch (Exception e){
            e.printStackTrace();
        }
        return allData;
    }
    @Test
    void isMatchCheck() {
        StraightFlush straightFlush = new StraightFlush();
        ArrayList<Card> cards = new ArrayList<Card>();
        boolean expectedResult=true;
        Card card;

        List<String[]> csvTestCases = new ArrayList<>();
        csvTestCases = readCsv("testcases.csv");
        for (String[] item : csvTestCases) {

            expectedResult = Boolean.parseBoolean(item[0]);
            String[] cardList = item[1].split(" ");
            for (String cardStr : cardList) {

                card = new Card(cardStr);
                cards.add(card);
            }
        }
        if (expectedResult) {
            assertTrue(straightFlush.isMatch(cards));
        } else {
            assertFalse(straightFlush.isMatch(cards));
        }
    }

}