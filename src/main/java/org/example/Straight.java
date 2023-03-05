package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Straight implements IMatch{
    private String target;
    private Rank rank = Rank.Straight;

    Straight(){
        target = "";
        for (CardValue cardValue : CardValue.values()){
                target += cardValue.label();
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

        for (Card card : cards){
            cardList += card.getCardValue().label();
        }
        return (target.contains(cardList));
    }
}
