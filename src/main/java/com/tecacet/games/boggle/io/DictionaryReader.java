package com.tecacet.games.boggle.io;

import com.tecacet.games.boggle.Trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class DictionaryReader {

    public Trie readDictionaryAsTrie(InputStream is) throws IOException {
        Trie trie = new Trie();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            while (br.ready()) {
                trie.add(br.readLine().toUpperCase());
            }
        }
        return trie;
    }


}
