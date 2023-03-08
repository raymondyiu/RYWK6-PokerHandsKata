package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TwoPairs implements IMatch, IWinner {
    private Map<String, String> target;

    int FIRST_PAIR_INDEX = 0;
    int SECOND_PAIR_INDEX =1;

    TwoPairs(){
        target = new HashMap<>();
        for (CardValue cardValue : CardValue.values()){
            target.put(cardValue.label(), cardValue.label()+cardValue.label());
        }
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

            if (cardList.contains(targetStr1)) {
                targetCopy.remove(cardValue.label());
                for (CardValue cardValue1 : CardValue.values()) {
                    String targetStr2 = targetCopy.get(cardValue1.label());
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
    public WinnerMsg whoWin(ArrayList<Card> player1Cards, ArrayList<Card> player2Cards, String message){
        boolean match1 = isMatch(player1Cards);
        boolean match2 = isMatch(player2Cards);
        WinnerMsg winnerMsg = new WinnerMsg();
        winnerMsg.setMessage(message);

        if (match1 && match2) {
            winnerMsg = higherScore(player1Cards, player2Cards,winnerMsg);
        } else if (match1){
            winnerMsg.setWinner(WinEnum.Player1Win);
            ArrayList<Card> twoPairsCards1 = findTwoPairs(player1Cards);
            winnerMsg.setMessage(winnerMsg.getMessage()+
                    twoPairsCards1.get(SECOND_PAIR_INDEX).getCardValue().value() + " and " +
                    twoPairsCards1.get(FIRST_PAIR_INDEX).getCardValue().value());
        } else if (match2){
            winnerMsg.setWinner(WinEnum.Player2Win);
            ArrayList<Card> twoPairsCards2 = findTwoPairs(player2Cards);
            winnerMsg.setMessage(winnerMsg.getMessage()+
                    twoPairsCards2.get(SECOND_PAIR_INDEX).getCardValue().value() + " and " +
                    twoPairsCards2.get(FIRST_PAIR_INDEX).getCardValue().value());
        } else {
            winnerMsg.setWinner(WinEnum.NotMatch);
            winnerMsg.setMessage("NotMatch");
        }

        return winnerMsg;
    }
    private WinnerMsg higherScore(ArrayList<Card> player1Cards, ArrayList<Card> player2Cards, WinnerMsg winnerMsg){
        ArrayList<Card> twoPairsCards1 = findTwoPairs(player1Cards);
        ArrayList<Card> twoPairsCards2 = findTwoPairs(player2Cards);
        int score1=0;
        int score2=0;
        CardValue cardValue1=null;
        CardValue cardValue2=null;

        for (int i=1;i>=0; i--) {
            cardValue1 = twoPairsCards1.get(i).getCardValue();
            cardValue2 = twoPairsCards2.get(i).getCardValue();
            score1 = twoPairsCards1.get(i).getCardValue().score();
            score2 = twoPairsCards2.get(i).getCardValue().score();

            if (score1 == score2){
                continue;
            } else {
                break;
            }
        }

        //Two Pairs are same then check the last card
        if(score1 == score2){
            Card singleCard1 = findSingleCard(player1Cards, twoPairsCards1);
            Card singleCard2 = findSingleCard(player2Cards, twoPairsCards2);;
            score1 = singleCard1.getCardValue().score();
            score2 = singleCard2.getCardValue().score();
        }

        if ( score1 == score2){
            winnerMsg.setWinner(WinEnum.Tie);
            winnerMsg.setMessage("Tie.");
        } else if (score1 > score2){
            winnerMsg.setWinner(WinEnum.Player1Win);
            winnerMsg.setMessage(winnerMsg.getMessage() +
                    twoPairsCards1.get(SECOND_PAIR_INDEX).getCardValue().value() + " and " +
                    twoPairsCards1.get(FIRST_PAIR_INDEX).getCardValue().value());
        } else {
            winnerMsg.setWinner(WinEnum.Player2Win);
            winnerMsg.setMessage(winnerMsg.getMessage() +
                            twoPairsCards2.get(SECOND_PAIR_INDEX).getCardValue().value() + " and " +
                    twoPairsCards2.get(FIRST_PAIR_INDEX).getCardValue().value());
        }
        return(winnerMsg);
    }

    private ArrayList<Card> findTwoPairs(ArrayList<Card> cards){
        String cardList="";
        Map<String,String> targetCopy = new HashMap<>();
        targetCopy.putAll(target);
        boolean match=false;
        ArrayList<Card> retCards = new ArrayList<Card>();

        for (Card card : cards){
            cardList += card.getCardValue().label();
        }

        for (CardValue cardValue : CardValue.values()){
            String targetStr1 = target.get(cardValue.label());

            if (cardList.contains(targetStr1)) {
                for (Card card : cards){
                    if (card.getCardValue().label().equals(cardValue.label())){
                        retCards.add(card);
                        break;
                    }
                }
                targetCopy.remove(cardValue.label());
                for (CardValue cardValue1 : CardValue.values()) {
                    String targetStr2 = targetCopy.get(cardValue1.label());
                    if (targetStr2 != null) {

                        match = cardList.contains(targetStr2);
                        if (match) {
                            for (Card card : cards){
                                if (card.getCardValue().label().equals(cardValue1.label())){
                                    retCards.add(card);
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }
                if (match) {
                    break;
                }
            }
        }

        return (retCards);
    }

    private Card findSingleCard(ArrayList<Card> playerCards, ArrayList<Card> twoPairCards) {
        ArrayList<Card> playerCardsClone = new ArrayList<Card>(playerCards);

        for (Card twoPairCard : twoPairCards ){
            for(Card playerCardClone : playerCardsClone) {
                if (playerCardClone.getCardValue().label().equals(twoPairCard.getCardValue().value())) {
                    playerCardsClone.remove(playerCardClone);
                    break;
                }
            }
            for(Card playerCardClone : playerCardsClone) {
                if (playerCardClone.getCardValue().label().equals(twoPairCard.getCardValue().value())) {
                    playerCardsClone.remove(playerCardClone);
                    break;
                }
            }
        }

        return(playerCardsClone.get(0));
    }
}
