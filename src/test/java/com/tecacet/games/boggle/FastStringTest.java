package com.tecacet.games.boggle;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FastStringTest {

    @Test
    public void test() {
        FastString fastString = FastString.fromString("ABRACADABRA");
        assertEquals("ABRACADABRA", fastString.toString());
        assertEquals('R', fastString.charAt(2));
        assertEquals(11, fastString.length());

        fastString.removeLast();
        assertEquals("ABRACADABR", fastString.toString());
        assertEquals('R', fastString.charAt(2));
        assertEquals(10, fastString.length());

        fastString.append('L');
        assertEquals("ABRACADABRL", fastString.toString());
    }

}
