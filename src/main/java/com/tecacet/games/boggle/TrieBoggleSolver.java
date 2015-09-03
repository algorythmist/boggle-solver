package com.tecacet.games.boggle;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class TrieBoggleSolver implements BoggleSolver {

    private final Trie dictionary;

    private boolean treatQAsQU = true;

    public TrieBoggleSolver(String[] words) {
        this.dictionary = new Trie(words);
    }

    public TrieBoggleSolver(Trie trie) {
        this.dictionary = trie;
    }

    @Override
    public Collection<String> solve(Boggle board) {
        Set<String> validWords = new TreeSet<String>();

        for (int r = 0; r < board.rows(); r++) {
            for (int c = 0; c < board.columns(); c++) {
                findPaths(r, c, board, validWords);
            }
        }
        return validWords;
    }

    private void findPaths(int x, int y, Boggle board, Set<String> validWords) {
        boolean[] visited = new boolean[board.rows() * board.columns()];
        FastString prefix = new FastString();
        findPaths(x, y, board, validWords, prefix, visited);
    }

    private void findPaths(int x, int y, Boggle board, Set<String> validWords, FastString prefix, boolean[] visited) {
        char c = board.getLetter(x, y);
        if (c == 'Q' && treatQAsQU) {
            prefix.append('Q');
            prefix.append('U');
        } else {
            prefix.append(c);
        }
        if (prefix.length() > 2 && dictionary.containsWord(prefix)) {
            validWords.add(prefix.toString());
        } else if (!dictionary.isValidPrefix(prefix)) {
            removeLast(prefix);
            return;
        }
        visited[y * board.rows() + x] = true;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nx = x + i;
                int ny = y + j;
                if (nx == x && ny == y) {
                    continue; // no self transitions
                }
                if (nx < 0 || nx >= board.rows() || ny < 0 || ny >= board.columns()) {
                    continue; // out of the board
                }
                if (visited[ny * board.rows() + nx]) {
                    continue;
                }
                findPaths(nx, ny, board, validWords, prefix, visited);
            }
        }

        removeLast(prefix);
        visited[y * board.rows() + x] = false;
    }

    private void removeLast(FastString string) {
        if (string == null || string.length() == 0) {
            return;
        }
        int length = string.length();
        if (treatQAsQU && string.length() >= 2 && string.charAt(length - 2) == 'Q' && string.charAt(length - 1) == 'U') {
            string.removeLast();
            string.removeLast();
        } else {
            string.removeLast();
        }
    }

    public boolean isTreatQAsQU() {
        return treatQAsQU;
    }

    public void setTreatQAsQU(boolean treatQAsQU) {
        this.treatQAsQU = treatQAsQU;
    }

}
