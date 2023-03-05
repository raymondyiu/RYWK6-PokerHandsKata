package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void winnerCheck() {
        String expectedResult="";
        ReadCsv readCsv = new ReadCsv("GameTestcases.csv");

        List<String[]> csvTestCases = new ArrayList<>();
        csvTestCases = readCsv.readCsv();

        for (String[] item : csvTestCases) {
            expectedResult = item[0];
            Game game = new Game(item[1]);
            assertEquals(expectedResult, game.winnerCheck());

        }
    }
}