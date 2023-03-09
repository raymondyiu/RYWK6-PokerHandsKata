package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AllSingleTest {

    @Test
    void whoWin() {
        AllSingle allSingle = new AllSingle();
        ArrayList<Card> cards1 = new ArrayList<Card>();
        ArrayList<Card> cards2 = new ArrayList<Card>();
        boolean expectedResult=true;
        Card card;
        ReadCsv readCsv = new ReadCsv("AllSingleTestcases.csv");

        List<String[]> csvTestCases = new ArrayList<>();
        csvTestCases = readCsv.readCsv();
        WinnerMsg winnerMsg = new WinnerMsg();

        for (String[] item : csvTestCases) {

            String[] cardList = item[1].split(" ");
            for (String cardStr : cardList) {

                card = new Card(cardStr);
                cards1.add(card);
            }
            Collections.sort(cards1, new Comparator<Card>() {
                        public int compare(final Card lhs, Card rhs) {
                            return (lhs.getCardValue().score() - rhs.getCardValue().score());
                        }

                    }
            );
            cardList = item[2].split(" ");
            for (String cardStr : cardList) {

                card = new Card(cardStr);
                cards2.add(card);
            }
            Collections.sort(cards2, new Comparator<Card>() {
                        public int compare(final Card lhs, Card rhs) {
                            return (lhs.getCardValue().score() - rhs.getCardValue().score());
                        }

                    }
            );
            winnerMsg = allSingle.whoWin(cards1, cards2,"with high card: ");

            assertEquals(item[0], winnerMsg.getMessage() );

            cards1.clear();
            cards2.clear();
        }
    }
}