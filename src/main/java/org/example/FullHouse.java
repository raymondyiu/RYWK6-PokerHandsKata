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
        for (Card card : cards){
            cardList += card.getCardValue().label();
        }

        for (CardValue cardValue : CardValue.values()){
            String targetStr = targetThreeOfAKind.get(cardValue.label());
            //System.out.println("targetStr : "+ targetStr);
            if (cardList.contains(targetStr)) {
                targetPair.remove(cardValue.label());
                for (CardValue cardValue1 : CardValue.values()) {
                        String targetStr1 = targetPair.get(cardValue1.label());
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

}
