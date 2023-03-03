package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StraightFlush implements IMatch {

    private Map<String, String> target;
    private Rank rank = Rank.StraightFlush;


    StraightFlush(){
        target = new HashMap<>();

        for (Suit suit : Suit.values()){
            String str="";
            for (CardValue cardValue : CardValue.values()){
                str += cardValue.label() + suit.label();
            }
            target.put(suit.label(), str);
        }
    }

    @Override
    public boolean isMatch(ArrayList<Card> cards) {
        return false;
    }

    @Override
    public int getScore() {
        return 0;
    }
}
