package com.tecacet.games.boggle.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import com.tecacet.games.boggle.Boggle;

public class BoggleReader {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Boggle read(InputStream is) throws IOException {
        if (is == null) {
            throw new NullPointerException("Null stream");
        }
        Scanner scanner = new Scanner(is);
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();
        char[][] board;
        try {
            board  = readBoard(scanner, rows, columns);
        } finally {
            scanner.close();
        }
        return new Boggle(board);
    }

    private char[][] readBoard(Scanner scanner, int rows, int columns) {
        char[][] board = new char[columns][rows];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                String letter = scanner.next().toUpperCase();
                if (letter.equals("QU")) {
                    board[c][r] = 'Q';
                } else if (letter.length() != 1) {
                    throw new IllegalArgumentException("invalid character: " + letter);
                } else if (ALPHABET.indexOf(letter) == -1) {
                    throw new IllegalArgumentException("invalid character: " + letter);
                } else {
                    board[c][r] = letter.charAt(0);
                }
            }
        }
        return board;
    }

}
