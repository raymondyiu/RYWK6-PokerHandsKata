package org.example;

import java.util.ArrayList;

public interface IWinner {


    public WinnerMsg higherScore(ArrayList<Card> player1Cards, ArrayList<Card> player2Cards, WinnerMsg winnerMsg);
    public WinnerMsg whoWin(ArrayList<Card> player1Cards, ArrayList<Card> player2Cards,String message);

}
