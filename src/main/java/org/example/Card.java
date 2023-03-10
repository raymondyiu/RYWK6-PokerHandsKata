package org.example;

import java.lang.Exception;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Card {
    private CardValue cardValue;
    private Suit suit;
    private String symbol;
    public Card(String cardStr) {
        this.parse(cardStr);
    }
    public String getSymbol() {return symbol;}
    public CardValue getCardValue() { return cardValue;}
    public Suit getSuit() { return suit;}

    private void parse(String cardStr) {
        cardStr = cardStr.toUpperCase();
        if (!(cardStr.length() == 2)) {
            throw new StringIndexOutOfBoundsException("Length size not equals 2");
        }
        cardValue = CardValue.valueOfLabel((new Character(cardStr.charAt(0))).toString());
        if (cardValue == null){
            throw new EnumConstantNotPresentException(CardValue.class, cardValue.name());
        }
        suit = Suit.valueOfLabel((new Character(cardStr.charAt(1))).toString());
        if (suit == null){
            throw new EnumConstantNotPresentException(Suit.class, suit.name());
        }
        symbol = cardStr;
    }


}
