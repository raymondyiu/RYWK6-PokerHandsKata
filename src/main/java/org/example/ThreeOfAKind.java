package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ThreeOfAKind implements IMatch, IWinner{
    private Map<String, String> target;
    private Rank rank = Rank.ThreeOfAKind;

    ThreeOfAKind(){
        target = new HashMap<>();

        for (CardValue cardValue : CardValue.values()){
            target.put(cardValue.label(), cardValue.label()+cardValue.label() + cardValue.label());
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
    public WinnerMsg whoWin(ArrayList<Card> player1Cards, ArrayList<Card> player2Cards, String message){
        boolean match1 = isMatch(player1Cards);
        boolean match2 = isMatch(player2Cards);
        WinnerMsg winnerMsg = new WinnerMsg();
        winnerMsg.setMessage(message);

        if (match1 && match2) {
            winnerMsg = higherScore(player1Cards, player2Cards,winnerMsg);
        } else if (match1){
            winnerMsg.setWinner(WinEnum.Player1Win);
            CardValue cardValue1 = findKeyScore(player1Cards);
            winnerMsg.setMessage(winnerMsg.getMessage()+cardValue1.value());
        } else if (match2){
            winnerMsg.setWinner(WinEnum.Player2Win);
            CardValue cardValue2 = findKeyScore(player2Cards);
            winnerMsg.setMessage(winnerMsg.getMessage()+cardValue2.value());
        } else {
            winnerMsg.setWinner(WinEnum.NotMatch);
            winnerMsg.setMessage("NotMatch");
        }

        return winnerMsg;
    }
    public WinnerMsg higherScore(ArrayList<Card> player1Cards, ArrayList<Card> player2Cards, WinnerMsg winnerMsg){
        CardValue cardValue1 = findKeyScore(player1Cards);
        CardValue cardValue2 = findKeyScore(player2Cards);

        if ( cardValue1.score() == cardValue2.score()){
            winnerMsg.setWinner(WinEnum.Tie);
            winnerMsg.setMessage("Tie.");
        } else if (cardValue1.score() > cardValue2.score()){
            winnerMsg.setWinner(WinEnum.Player1Win);
            winnerMsg.setMessage(winnerMsg.getMessage() + cardValue1.value());
        } else {
            winnerMsg.setWinner(WinEnum.Player2Win);
            winnerMsg.setMessage(winnerMsg.getMessage() + cardValue2.value());
        }
        return(winnerMsg);
    }

    public CardValue findKeyScore(ArrayList<Card> cards){
        String cardList="";
        CardValue retCardValue=null;

        for (Card card : cards){
            cardList += card.getCardValue().label();
        }
        for (CardValue cardValue : CardValue.values()){
            String targetStr = target.get(cardValue.label());
            if (cardList.contains(targetStr)) {
                retCardValue = cardValue;
                break;
            }
        };
        return(retCardValue);
    }
}
