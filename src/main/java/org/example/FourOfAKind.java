package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FourOfAKind implements IMatch{
    private Map<String, String> target;
    private Rank rank = Rank.FourOfAKind;

    FourOfAKind(){
        target = new HashMap<>();

        for (CardValue cardValue : CardValue.values()){
            String str="";

            for (Suit suit : Suit.values()) {
                str += cardValue.label();
            }
            target.put(cardValue.label(), str);
        }
    }
    public int getScore() {
        return rank.score;
    }

    public boolean isMatch(ArrayList<Card> cards) {
        String cardList="";
        boolean match=false;
        for (Card card : cards){
            cardList += card.getCardValue().label();
        }

        for (CardValue cardValue : CardValue.values()){
            String targetStr = target.get(cardValue.label());
            match = cardList.contains(targetStr);
            if (match) {break;}
        }
        return match;
    }
    public WinnerMsg whoWin(ArrayList<Card> player1Cards, ArrayList<Card> player2Cards){
        boolean match1 = isMatch(player1Cards);
        boolean match2 = isMatch(player2Cards);
        WinnerMsg winnerMsg = new WinnerMsg();
        winnerMsg.setMessage("with four of a kind: ");

        if (match1 && match2) {
            winnerMsg = higherScore(player1Cards, player2Cards,winnerMsg);
        } else if (match1){
            winnerMsg.setWinner(WinEnum.Player1Win);
        } else if (match2){
            winnerMsg.setWinner(WinEnum.Player2Win);
        } else {
            winnerMsg.setWinner(WinEnum.NotMatch);
            winnerMsg.setMessage("NotMatch");
        }

        return winnerMsg;
    }
    private WinnerMsg higherScore(ArrayList<Card> player1Cards, ArrayList<Card> player2Cards, WinnerMsg winnerMsg){
        int score1 = findKeyScore(player1Cards);
        int score2 = findKeyScore(player2Cards);
        if ( score1 == score2){
            winnerMsg.setWinner(WinEnum.Tie);
            winnerMsg.setMessage("Tie.");
        } else if (score1 > score2){
            winnerMsg.setWinner(WinEnum.Player1Win);
        } else {
            winnerMsg.setWinner(WinEnum.Player2Win);
        }
        return(winnerMsg);
    }

    private int findKeyScore(ArrayList<Card> cards){
        String cardList="";
        int score=0;

        for (Card card : cards){
            cardList += card.getCardValue().label();
        }
        for (CardValue cardValue : CardValue.values()){
            String targetStr = target.get(cardValue.label());
            if (cardList.contains(targetStr)) {
                score = cardValue.score();
            }
        }
    }
}
