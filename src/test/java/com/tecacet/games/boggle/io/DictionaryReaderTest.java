package com.tecacet.games.boggle.io;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.tecacet.games.boggle.FastString;
import com.tecacet.games.boggle.Trie;

public class DictionaryReaderTest {

    @Test
    public void test() throws IOException {
        DictionaryReader dictionaryReader = new DictionaryReader();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("dictionaries/dictionary-2letters.txt");
        Trie trie = dictionaryReader.readDictionaryAsTrie(is);
        assertEquals(123, trie.size());
        assertFalse(trie.containsWord(FastString.fromString("NG")));
        assertTrue(trie.containsWord(FastString.fromString("IN")));
    }

}
