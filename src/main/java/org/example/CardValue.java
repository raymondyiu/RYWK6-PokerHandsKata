package org.example;

import java.util.HashMap;
import java.util.Map;

public enum CardValue {
    Two("2",2), Three("3",3), Four("4", 4), Five("5", 5), Six("6",6),
    Seven("7", 7), Eight("8",8 ), Nine("9", 9), Ten("T",10),
    Jack("J",11), Queen("Q", 12), King("K",13), Ace("A",14);


    private final String label;
    private final int score;

    CardValue(String label, int score) {
        this.label = label;
        this.score = score;
    }
    public String label() { return label;}
    public int score() {return score;}
    private static final Map<String, CardValue> BY_LABEL = new HashMap<>();
    static {
        for (CardValue cardValue : values()) {
            BY_LABEL.put(cardValue.label, cardValue);
        }
    }
    public static CardValue valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }

}
