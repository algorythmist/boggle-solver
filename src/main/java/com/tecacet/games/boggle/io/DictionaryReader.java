package com.tecacet.games.boggle.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.tecacet.games.boggle.Trie;

public class DictionaryReader {

	public Trie readDictionaryAsTrie(InputStream is) throws IOException {
		Trie trie = new Trie();
		Reader reader = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(reader);
		String line;
		while ((line = br.readLine()) != null) {
			trie.add(line.toUpperCase());
		}
		br.close();
		reader.close();
		return trie;
	}


}
