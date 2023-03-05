package org.example;

import java.util.HashMap;
import java.util.Map;

public enum CardValue {
    Two("2","2",2), Three("3","3",3), Four("4","4", 4),
    Five("5","5", 5), Six("6","6",6),Seven("7","7", 7),
    Eight("8", "8",8 ), Nine("9", "9", 9), Ten("10","T",10),
    Jack("Jack","J",11), Queen("Queen", "Q", 12), King("King","K",13),
    Ace("Ace","A",14);


    private final String value;
    private final String label;
    private final int score;

    CardValue(String value, String label, int score) {
        this.value = value;
        this.label = label;
        this.score = score;
    }
    public String value() {return value;}
    public String label() { return label;}
    public int score() {return score;}
    private static final Map<String, CardValue> BY_LABEL = new HashMap<>();
    static {
        for (CardValue cardValue : values()) {
            BY_LABEL.put(cardValue.label, cardValue);
        }
    }
    private static final Map<Integer, CardValue> BY_SCORE = new HashMap<>();
    static {
        for (CardValue cardValue : values()) {
            BY_SCORE.put(Integer.valueOf(cardValue.score), cardValue);
        }
    }

    public static CardValue valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }
    public static CardValue valueOfScore(int score){
        return BY_SCORE.get(Integer.valueOf(score));
    }

}
