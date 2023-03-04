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
            System.out.println("four :" + cardValue.label());
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
        return false;
    }
}
