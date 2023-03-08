package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FullHouse implements IMatch, IWinner{
    private Map<String, String> targetThreeOfAKind;

    private Map<String, String> targetPair;
    private Rank rank = Rank.FullHouse;

    FullHouse(){
        targetThreeOfAKind = new HashMap<>();
        targetPair = new HashMap<>();

        for (CardValue cardValue : CardValue.values()) {
            targetThreeOfAKind.put(cardValue.label(), cardValue.label() + cardValue.label() + cardValue.label());
            targetPair.put(cardValue.label(), cardValue.label()+cardValue.label());
        }
    }
    public int getScore() {
        return rank.score;
    }
    public boolean isMatch(ArrayList<Card> cards) {
        String cardList="";
        boolean match=false;
        Map<String, String> targetPairCopy = new HashMap<>();
        targetPairCopy.putAll(targetPair);

        for (Card card : cards){
            cardList += card.getCardValue().label();
        }

        for (CardValue cardValue : CardValue.values()){
            String targetStr = targetThreeOfAKind.get(cardValue.label());

            if (cardList.contains(targetStr)) {
                targetPairCopy.remove(cardValue.label());
                for (CardValue cardValue1 : CardValue.values()) {
                        String targetStr1 = targetPairCopy.get(cardValue1.label());
                        if (targetStr1 != null) {

                            match = cardList.contains(targetStr1);
                            if (match) {
                                break;
                            }
                        }
                }
                if (match) {
                    break;
                }
            }
        }
        return match;
    }

    public WinnerMsg whoWin(ArrayList<Card> player1Cards, ArrayList<Card> player2Cards,String message){
        boolean match1 = isMatch(player1Cards);
        boolean match2 = isMatch(player2Cards);
        WinnerMsg winnerMsg = new WinnerMsg();
        winnerMsg.setMessage(message);

        if (match1 && match2) {
            winnerMsg = higherScore(player1Cards, player2Cards,winnerMsg);
        } else if (match1){
            winnerMsg.setWinner(WinEnum.Player1Win);
            CardValue threeOfAKindCardValue = findKeyScore(player1Cards);
            CardValue pairCardValue = findPair(player1Cards);
            winnerMsg.setMessage(winnerMsg.getMessage() + threeOfAKindCardValue.value() + " over "+ pairCardValue.value());
        } else if (match2){
            winnerMsg.setWinner(WinEnum.Player2Win);
            CardValue threeOfAKindCardValue = findKeyScore(player2Cards);
            CardValue pairCardValue = findPair(player2Cards);
            winnerMsg.setMessage(winnerMsg.getMessage() + threeOfAKindCardValue.value() + " over "+ pairCardValue.value());
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
            CardValue pairCardValue = findPair(player1Cards);
            winnerMsg.setMessage(winnerMsg.getMessage() + cardValue1.value() + " over "+ pairCardValue.value());
        } else {
            winnerMsg.setWinner(WinEnum.Player2Win);
            CardValue pairCardValue = findPair(player2Cards);
            winnerMsg.setMessage(winnerMsg.getMessage() + cardValue2.value()+ " over "+ pairCardValue.value());
        }
        return (winnerMsg);
    }
    private CardValue findKeyScore(ArrayList<Card> cards){
        String cardList="";
        CardValue retCardValue=null;

        for (Card card : cards){
            cardList += card.getCardValue().label();
        }

        for (CardValue cardValue : CardValue.values()) {
            String targetStr = targetThreeOfAKind.get(cardValue.label());

            if (cardList.contains(targetStr)) {
                retCardValue = cardValue;
                break;
            }
        }
        return(retCardValue);
    }

    private CardValue findPair(ArrayList<Card> cards){
        String cardList="";
        boolean match=false;
        CardValue RetCardValue=null;
        Map<String, String> targetPairCopy = new HashMap<>();
        targetPairCopy.putAll(targetPair);

        for (Card card : cards){
            cardList += card.getCardValue().label();
        }

        for (CardValue cardValue : CardValue.values()){
            String targetStr = targetThreeOfAKind.get(cardValue.label());

            if (cardList.contains(targetStr)) {
                targetPairCopy.remove(cardValue.label());
                for (CardValue cardValue1 : CardValue.values()) {
                    String targetStr1 = targetPairCopy.get(cardValue1.label());
                    if (targetStr1 != null) {
                        match = cardList.contains(targetStr1);
                        if(match) {
                            RetCardValue = cardValue1;
                            break;
                        }
                    }
                }
                if (match) {
                    break;
                }
            }
        }
        return (RetCardValue);
    }
}
