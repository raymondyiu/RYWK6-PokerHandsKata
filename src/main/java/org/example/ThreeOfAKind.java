package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ThreeOfAKind implements IMatch{
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
}
