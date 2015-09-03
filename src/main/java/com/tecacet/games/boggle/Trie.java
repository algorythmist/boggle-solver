package com.tecacet.games.boggle;

/**
 * An Immutable Trie implementation
 * 
 * @author dimitri
 *
 */
public class Trie {
    /* Position of capital A in ASCII table */
    private static final int A_POSITION = 65;

    /* Number of capital letters */
    private static final int LETTERS = 26;
    
    
    private Node root = new Node();
    private int size = 0;

    private static class Node {
        private Boolean valid;
        private Node[] next = new Node[LETTERS];
    }

    public Trie(String[] words) {
        for (String word : words) {
            add(word);
        }
    }

    public Trie() {
        super();
    }

    public boolean containsWord(FastString key) {
        return get(key) != null;
    }


    private Boolean get(FastString key) {
        Node x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return x.valid;
    }

    private Node get(Node x, FastString key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        char c = key.charAt(d);
        return get(x.next[c - A_POSITION], key, d + 1);
    }

    public void add(String key) {
        root = put(root, key, true, 0);
        size++;
    }

    private Node put(Node x, String key, boolean val, int d) {
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            x.valid = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c - A_POSITION] = put(x.next[c - A_POSITION], key, val, d + 1);
        return x;
    }

    public boolean isValidPrefix(FastString prefix) {
        Node x = get(root, prefix, 0);
        return x != null;
    }

    public int size() {
        return size;
    }

    
}
