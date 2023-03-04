package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TwoPairs implements IMatch{
    private Map<String, String> target1;
    private Map<String, String> target2;
    private Rank rank = Rank.TwoPairs;

    TwoPairs(){
        target1 = new HashMap<>();
        target2 = new HashMap<>();

        for (CardValue cardValue : CardValue.values()){
            target1.put(cardValue.label(), cardValue.label()+cardValue.label());
            target2.put(cardValue.label(), cardValue.label()+cardValue.label());
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
            String targetStr1 = target1.get(cardValue.label());

            if (cardList.contains(targetStr1)) {
                target2.remove(cardValue.label());
                for (CardValue cardValue1 : CardValue.values()) {
                    String targetStr2 = target2.get(cardValue1.label());
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
