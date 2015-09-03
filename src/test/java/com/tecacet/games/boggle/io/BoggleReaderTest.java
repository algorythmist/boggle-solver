package com.tecacet.games.boggle.io;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.tecacet.games.boggle.Boggle;
import com.tecacet.games.boggle.io.BoggleReader;

public class BoggleReaderTest {

    @Test
    public void testRead4x4() throws IOException {
        BoggleReader reader = new BoggleReader();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("boards/board4x4.txt");
        Boggle board = reader.read(is);
        assertEquals(4,board.columns());      
    }

    @Test
    public void testReadNonSymmetric() throws IOException {
        BoggleReader reader = new BoggleReader();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("boards/board-antidisestablishmentarianisms.txt");
        Boggle board = reader.read(is);
        assertEquals(29,board.columns());      
        assertEquals(1,board.rows());      
    }
}
