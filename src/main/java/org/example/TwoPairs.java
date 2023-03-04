package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TwoPairs implements IMatch{
    private Map<String, String> target;
    private Rank rank = Rank.TwoPairs;

    TwoPairs(){
        target = new HashMap<>();
        for (CardValue cardValue : CardValue.values()){
            target.put(cardValue.label(), cardValue.label()+cardValue.label());
        }
    }
    public int getScore() {
        return rank.score;
    }
    public boolean isMatch(ArrayList<Card> cards) {
        String cardList="";
        Map<String,String> targetCopy = new HashMap<>();
        targetCopy.putAll(target);
        boolean match=false;
        for (Card card : cards){
            cardList += card.getCardValue().label();
        }

        for (CardValue cardValue : CardValue.values()){
            String targetStr1 = target.get(cardValue.label());

            if (cardList.contains(targetStr1)) {
                targetCopy.remove(cardValue.label());
                for (CardValue cardValue1 : CardValue.values()) {
                    String targetStr2 = targetCopy.get(cardValue1.label());
                    if (targetStr2 != null) {

                        match = cardList.contains(targetStr2);
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
