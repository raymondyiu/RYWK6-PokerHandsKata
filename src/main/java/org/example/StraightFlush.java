package org.example;

import java.util.ArrayList;
import java.util.Collections;
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

    public int getScore() {
        return rank.score();
    }
    @Override
    public boolean isMatch(ArrayList<Card> cards) {
        String cardList="";
        ArrayList<String> cardSymbolList = new ArrayList<>();
        boolean match=false;
        cards = Card.sort(cards);
        for (Card card : cards){
            cardSymbolList.add(card.getSymbol());
        }

        for (String item : cardSymbolList){
            cardList += item;
        }
        System.out.println("cardList: "+ cardList);
        for (Suit suit : Suit.values()){
            String targetStr = target.get(suit.label());
            System.out.println("targetStr: "+ targetStr);

            match = targetStr.contains(cardList);
            if (match) {break;}
        }
        return match;
    }

}
