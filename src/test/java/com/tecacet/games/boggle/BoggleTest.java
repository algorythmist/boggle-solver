package com.tecacet.games.boggle;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BoggleTest {

    @Test
    public void fillRandomly() {
        Boggle boggle = new Boggle(4, 4);
        boggle.fillRandomly(77868L);
        assertEquals("X, F, I, S\nW, B, K, L\nD, T, I, L\nH, B, O, E\n", boggle.toString());
    }

    @Test
    public void fillRandomlyAssymetric() {
        Boggle boggle = new Boggle(4, 2);
        boggle.fillRandomly(77868L);
        System.out.println(boggle);
        assertEquals("X, D, F, T\nW, H, B, B\n", boggle.toString());
    }

}
