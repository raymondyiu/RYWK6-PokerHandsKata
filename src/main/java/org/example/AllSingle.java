package org.example;

import java.util.ArrayList;

public class AllSingle implements IWinner {
    public WinnerMsg whoWin(ArrayList<Card> player1Cards, ArrayList<Card> player2Cards, String message){
        WinnerMsg winnerMsg = new WinnerMsg();
        winnerMsg.setMessage(message);
        return (winnerMsg);
    }
}
