package org.example;

import java.util.ArrayList;

public class StraightFlush implements IMatch {

    @Override
    public boolean isMatch(ArrayList<Card> cards) {
        return false;
    }

    @Override
    public int getScore() {
        return 0;
    }
}
