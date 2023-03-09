package org.example;

import java.util.ArrayList;

public class AllSingle implements IWinner {
    private int MAX_CARD_INDEX =4;
    public WinnerMsg whoWin(ArrayList<Card> player1Cards, ArrayList<Card> player2Cards, String message){
        WinnerMsg winnerMsg = new WinnerMsg();
        winnerMsg.setMessage(message);
        Card card1=null;
        Card card2=null;
        int score1=0;
        int score2=0;
        for (int i=MAX_CARD_INDEX; i>=0; i--){
            card1 = player1Cards.get(i);
            card2 = player2Cards.get(i);
            score1 = player1Cards.get(i).getCardValue().score();
            score2 = player2Cards.get(i).getCardValue().score();
            if (score1 != score2){
                break;
            }
        }
        if (score1 == score2){
            winnerMsg.setMessage("Tie.");
        } else if (score1 > score2){
            winnerMsg.setWinner(WinEnum.Player1Win);
            winnerMsg.setMessage(winnerMsg.getMessage()+ card1.getCardValue().value());
        } else {
            winnerMsg.setWinner(WinEnum.Player2Win);
            winnerMsg.setMessage(winnerMsg.getMessage()+ card2.getCardValue().value());
        }
        return (winnerMsg);
    }
}
