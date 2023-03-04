package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Flush implements IMatch{
    private Map<String, String> target;
    private Rank rank = Rank.Flush;

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
