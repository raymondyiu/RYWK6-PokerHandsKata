package org.example;

import java.util.HashMap;
import java.util.Map;

public enum Suit {
    Club("C"), Heart("H"), Diamond("D"), Spade("S");
    private final String label;
    Suit (String label) { this.label = label;}
    public String label() { return label;}
    private static final Map<String, Suit> BY_LABEL = new HashMap<>();
    static {
        for (Suit suit : values()) {
            BY_LABEL.put(suit.label, suit);
        }
    }
    public static Suit valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }

}
