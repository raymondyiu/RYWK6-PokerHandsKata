package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Flush implements IMatch, IWinner{
    private Map<String, String> target;
    private Rank rank = Rank.Flush;

    private int HIGHEST_SCORE_CARD_INDEX=4;

    Flush(){
        target = new HashMap<>();

        for (Suit suit : Suit.values()){
            String str="";
            for (int i=0; i<5; i++){
                str += suit.label();
            }

            target.put(suit.label(), str);
        }
    }

    public int getScore() {
        return rank.score();
    }

    public boolean isMatch(ArrayList<Card> cards) {
        String suitList="";
        boolean match=false;
        for (Card card : cards){
            suitList += card.getSuit().label();
        }

        for (Suit suit : Suit.values()){
            String targetStr = target.get(suit.label());
            match = suitList.contains(targetStr);
            if (match) {break;}
        }
        return match;
    }

    public WinnerMsg whoWin(ArrayList<Card> player1Cards, ArrayList<Card> player2Cards, String message){
        boolean match1 = isMatch(player1Cards);
        boolean match2 = isMatch(player2Cards);
        WinnerMsg winnerMsg = new WinnerMsg();
        winnerMsg.setMessage(message);

        if (match1 && match2) {
            winnerMsg = higherScore(player1Cards,player2Cards,winnerMsg);
        } else if (match1){
            winnerMsg.setWinner(WinEnum.Player1Win);
            winnerMsg.setMessage(winnerMsg.getMessage()+player1Cards.get(HIGHEST_SCORE_CARD_INDEX).getSuit().name());
        } else if (match2){
            winnerMsg.setWinner(WinEnum.Player2Win);
            winnerMsg.setMessage(winnerMsg.getMessage()+player2Cards.get(HIGHEST_SCORE_CARD_INDEX).getSuit().name());
        } else {
            winnerMsg.setWinner(WinEnum.NotMatch);
            winnerMsg.setMessage("NotMatch");
        }

        return winnerMsg;
    }
    private WinnerMsg higherScore(ArrayList<Card> player1Cards, ArrayList<Card> player2Cards, WinnerMsg winnerMsg){

        Card card1 = null;
        Card card2 = null;
        int score1=0;
        int score2=0;

        for (int i=HIGHEST_SCORE_CARD_INDEX; i>=0;i--){
            card1 = player1Cards.get(i);
            card2 = player2Cards.get(i);
            score1 = card1.getCardValue().score();
            score2 = card2.getCardValue().score();

            if ( score1 == score2){
                continue;
            } else {
                break;
            }
        }

        if ( score1 == score2){
            winnerMsg.setWinner(WinEnum.Tie);
            winnerMsg.setMessage("Tie.");
        } else if (score1 > score2){
            winnerMsg.setWinner(WinEnum.Player1Win);
            winnerMsg.setMessage(winnerMsg.getMessage()+
                    card1.getSuit().name());
        } else {
            winnerMsg.setWinner(WinEnum.Player2Win);
            winnerMsg.setMessage(winnerMsg.getMessage()+
                    card2.getSuit().name());
        }
        return(winnerMsg);
    }


}
