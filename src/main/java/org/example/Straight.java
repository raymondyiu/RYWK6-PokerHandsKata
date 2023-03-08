package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Straight implements IMatch, IWinner{
    private String target;
    private Rank rank = Rank.Straight;
    private int HIGHEST_SCORE_CARD_INDEX=4;
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

    public WinnerMsg whoWin(ArrayList<Card> player1Cards, ArrayList<Card> player2Cards, String message){
        boolean match1 = isMatch(player1Cards);
        boolean match2 = isMatch(player2Cards);
        WinnerMsg winnerMsg = new WinnerMsg();
        winnerMsg.setMessage(message);

        if (match1 && match2) {
            winnerMsg = higherScore(player1Cards,player2Cards,winnerMsg);
        } else if (match1){
            winnerMsg.setWinner(WinEnum.Player1Win);
            CardValue cardValue = findKeyScore(player1Cards);
            winnerMsg.setMessage(winnerMsg.getMessage()+cardValue.value());
        } else if (match2){
            winnerMsg.setWinner(WinEnum.Player2Win);
            CardValue cardValue = findKeyScore(player2Cards);
            winnerMsg.setMessage(winnerMsg.getMessage()+cardValue.value());
        } else {
            winnerMsg.setWinner(WinEnum.NotMatch);
            winnerMsg.setMessage("NotMatch");
        }

        return winnerMsg;
    }
    private WinnerMsg higherScore(ArrayList<Card> player1Cards, ArrayList<Card> player2Cards, WinnerMsg winnerMsg){

        CardValue cardValue1 = findKeyScore(player1Cards);
        CardValue cardValue2 = findKeyScore(player2Cards);
        if ( cardValue1.score() == cardValue2.score()){
            winnerMsg.setWinner(WinEnum.Tie);
            winnerMsg.setMessage("Tie.");
        } else if (cardValue1.score() > cardValue2.score()){
            winnerMsg.setWinner(WinEnum.Player1Win);
            winnerMsg.setMessage(winnerMsg.getMessage()+
                    cardValue1.value());
        } else {
            winnerMsg.setWinner(WinEnum.Player2Win);
            winnerMsg.setMessage(winnerMsg.getMessage()+
                    cardValue2.value());
        }
        return(winnerMsg);
    }

    private CardValue findKeyScore(ArrayList<Card> cards){
        return(cards.get(HIGHEST_SCORE_CARD_INDEX).getCardValue());
    }

}
