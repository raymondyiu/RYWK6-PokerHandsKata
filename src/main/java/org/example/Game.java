package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Game {
    private int PLAYER1_INDEX=0;
    private int POKERHAND1_START_INDEX=1;
    private int POKERHAND1_END_INDEX=5;

    private int PLAYER2_INDEX=6;
    private int POKERHAND2_START_INDEX=7;
    private int POKERHAND2_END_INDEX=11;
    private String player1Name;
    private String player2Name;
    private ArrayList<Card> cards1;
    private ArrayList<Card> cards2;

    Game(String line){

        String[] items = line.split(" ");
        player1Name = items[PLAYER1_INDEX];
        player2Name = items[PLAYER2_INDEX];
        for (int i=POKERHAND1_START_INDEX; i<=POKERHAND1_END_INDEX; i++){
            Card card = new Card(items[i]);
            cards1.add(card);
        }
        Collections.sort(cards1, new Comparator<Card>() {
            public int compare(final Card lhs, Card rhs) {
                return (lhs.getCardValue().score() - rhs.getCardValue().score());
            }

        }
        );

        for (int j=POKERHAND2_START_INDEX; j<=POKERHAND2_END_INDEX; j++){
            Card card = new Card(items[j]);
            cards2.add(card);
        }
        Collections.sort(cards2, new Comparator<Card>() {
                    public int compare(final Card lhs, Card rhs) {
                        return (lhs.getCardValue().score() - rhs.getCardValue().score());
                    }

                }
        );

    }

    public void winnerCheck(){
        StraightFlush straightFlush = new StraightFlush();
        WinnerMsg winnerMsg = straightFlush.whoWin(cards1, cards2);
        switch (winnerMsg.getWinner()){
            case Player1 -> {
                System.out.println(player1Name + winnerMsg.getMessage());
                return;
            }
            case Player2 -> {
                System.out.println(player2Name + winnerMsg.getMessage());
                return;
            }
        }

    }
}
