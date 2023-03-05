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
        cards1 = new ArrayList<>();
        cards2 = new ArrayList<>();

        String[] items = line.split(" ");
        player1Name = items[PLAYER1_INDEX].substring(0,items[PLAYER1_INDEX].length()-1);
        player2Name = items[PLAYER2_INDEX].substring(0,items[PLAYER2_INDEX].length()-1);

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

    public String winnerCheck(){
        StraightFlush straightFlush = new StraightFlush();
        WinnerMsg winnerMsg = straightFlush.whoWin(cards1, cards2, "with straight flush: ");
        switch (winnerMsg.getWinner()){
            case Player1Win -> {
                return(player1Name + " wins. - " + winnerMsg.getMessage());
            }
            case Player2Win -> {
                return(player2Name + " wins. - " + winnerMsg.getMessage());
            }
            case Tie -> {
                return(winnerMsg.getMessage());
            }
        }
        FourOfAKind fourOfAKind = new FourOfAKind();
        winnerMsg = fourOfAKind.whoWin(cards1, cards2,"with four of a kind: ");
        switch (winnerMsg.getWinner()){
            case Player1Win -> {
                return(player1Name + " wins. - " + winnerMsg.getMessage());
            }
            case Player2Win -> {
                return(player2Name + " wins. - " + winnerMsg.getMessage());
            }
            case Tie -> {
                return(winnerMsg.getMessage());
            }
        }
        FullHouse fullHouse = new FullHouse();
        winnerMsg = fullHouse.whoWin(cards1,cards2,"with full house: ");
        switch (winnerMsg.getWinner()){
            case Player1Win -> {
                return(player1Name + " wins. - " + winnerMsg.getMessage());
            }
            case Player2Win -> {
                return(player2Name + " wins. - " + winnerMsg.getMessage());
            }
            case Tie -> {
                return(winnerMsg.getMessage());
            }
        }
        Flush flush = new Flush();
        winnerMsg = flush.whoWin(cards1, cards2,"with flush: ");
        switch (winnerMsg.getWinner()){
            case Player1Win -> {
                return(player1Name + " wins. - " + winnerMsg.getMessage());
            }
            case Player2Win -> {
                return(player2Name + " wins. - " + winnerMsg.getMessage());
            }
            case Tie -> {
                return(winnerMsg.getMessage());
            }
        }
        return ("No result");
    }

}
