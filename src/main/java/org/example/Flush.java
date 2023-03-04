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
        String suitList="";
        boolean match=false;
        for (Card card : cards){
            suitList += card.getSuit().label();
        }

        for (Suit suit : Suit.values()){
            String targetStr = target.get(suit.label());
            match = suitList.contains(targetStr);
            if (match) {break;}
        }
        return match;
    }
}
