package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Pair implements IMatch{
    private Map<String, String> target;
    private Rank rank = Rank.Pair;

    Pair(){
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

            match = cardList.contains(targetStr1);
            if (match) {break;}

        }
        return match;
    }
}
