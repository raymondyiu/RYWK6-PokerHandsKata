package org.example;

import java.util.ArrayList;

public interface IMatch {

    public  boolean isMatch(ArrayList<Card> cards);
    public int getScore();
    //public WinnerMsg whoWin(ArrayList<Card> cards1, ArrayList<Card> cards2);
}
