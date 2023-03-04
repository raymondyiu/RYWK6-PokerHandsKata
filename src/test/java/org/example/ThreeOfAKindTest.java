package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThreeOfAKindTest {

    @Test
    void getScore() {
        ThreeOfAKind threeOfAKind = new ThreeOfAKind();
        assertEquals(3000, threeOfAKind.getScore());
    }

    @Test
    void isMatch() {
    }
}