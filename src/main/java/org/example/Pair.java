package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Pair implements IMatch, IWinner{
    private Map<String, String> target;
    private Rank rank = Rank.Pair;

    int MAX_SINGLE_CARD_INDEX =2;

    Pair(){
        target = new HashMap<>();
        for (CardValue cardValue : CardValue.values()){
            target.put(cardValue.label(), cardValue.label()+cardValue.label());
        }
    }
    public int getScore() {
        return rank.score;
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

            match = cardList.contains(targetStr1);
            if (match) {break;}
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
            Card pairCard1 = findPair(player1Cards);
            winnerMsg.setMessage(winnerMsg.getMessage()+
                    pairCard1.getCardValue().value());
        } else if (match2){
            winnerMsg.setWinner(WinEnum.Player2Win);
            Card pairCard2 = findPair(player2Cards);
            winnerMsg.setMessage(winnerMsg.getMessage()+
                    pairCard2.getCardValue().value());
        } else {
            winnerMsg.setWinner(WinEnum.NotMatch);
            winnerMsg.setMessage("NotMatch");
        }

        return winnerMsg;
    }
    private WinnerMsg higherScore(ArrayList<Card> player1Cards, ArrayList<Card> player2Cards, WinnerMsg winnerMsg){
        Card pairCard1 = findPair(player1Cards);
        Card pairCard2 = findPair(player2Cards);
        int score1=0;
        int score2=0;
        CardValue cardValue1=null;
        CardValue cardValue2=null;


        cardValue1 = pairCard1.getCardValue();
        cardValue2 = pairCard2.getCardValue();
        score1 = pairCard1.getCardValue().score();
        score2 = pairCard2.getCardValue().score();


        //Two Pairs are same then check the last card
        if(score1 == score2){
            ArrayList<Card> singleCards1 = findSingleCards(player1Cards, pairCard1);
            ArrayList<Card> singleCards2 = findSingleCards(player2Cards, pairCard2);

            for(int i=MAX_SINGLE_CARD_INDEX; i>=0; i--) {
                score1 = singleCards1.get(i).getCardValue().score();
                score2 = singleCards2.get(i).getCardValue().score();
                if (score1 != score2) {
                    break;
                }
            }
        }

        if ( score1 == score2){
            winnerMsg.setWinner(WinEnum.Tie);
            winnerMsg.setMessage("Tie.");
        } else if (score1 > score2){
            winnerMsg.setWinner(WinEnum.Player1Win);
            winnerMsg.setMessage(winnerMsg.getMessage() +
                    pairCard1.getCardValue().value());
        } else {
            winnerMsg.setWinner(WinEnum.Player2Win);
            winnerMsg.setMessage(winnerMsg.getMessage() +
                    pairCard2.getCardValue().value());
        }
        return(winnerMsg);
    }

    private Card findPair(ArrayList<Card> cards){
        String cardList="";
        Map<String,String> targetCopy = new HashMap<>();
        targetCopy.putAll(target);
        boolean match=false;
        Card retCard = null;

        for (Card card : cards){
            cardList += card.getCardValue().label();
        }

        for (CardValue cardValue : CardValue.values()){
            String targetStr1 = target.get(cardValue.label());

            if (cardList.contains(targetStr1)) {
                for (Card card : cards){
                    if (card.getCardValue().label().equals(cardValue.label())){
                        retCard = card;
                        break;
                    }
                }
                break;
            }
        }

        return (retCard);
    }

    private ArrayList<Card> findSingleCards(ArrayList<Card> playerCards, Card pairCard) {
        ArrayList<Card> playerCardsClone = new ArrayList<Card>(playerCards);


            for(Card playerCardClone : playerCardsClone) {
                if (playerCardClone.getCardValue().label().equals(pairCard.getCardValue().value())) {
                    playerCardsClone.remove(playerCardClone);
                    break;
                }
            }
            for(Card playerCardClone : playerCardsClone) {
                if (playerCardClone.getCardValue().label().equals(pairCard.getCardValue().value())) {
                    playerCardsClone.remove(playerCardClone);
                    break;
                }
            }

        return(playerCardsClone);
    }

}
