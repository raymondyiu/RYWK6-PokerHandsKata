package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FullHouse implements IMatch{
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
        } else if (match2){
            winnerMsg.setWinner(WinEnum.Player2Win);
        } else {
            winnerMsg.setWinner(WinEnum.NotMatch);
            winnerMsg.setMessage("NotMatch");
        }

        return winnerMsg;
    }
    public WinnerMsg higherScore(ArrayList<Card> player1Cards, ArrayList<Card> player2Cards, WinnerMsg winnerMsg){
        return (winnerMsg);
    }
}
