package com.tecacet.games.boggle;

import java.util.Random;

/**
 * The Boggle Board
 *
 * @author dimitri
 */
public class Boggle {

    private final char[][] board;

    private final int columns;
    private final int rows;

    public Boggle(char[][] board) {
        this.board = board;
        this.columns = board.length;
        this.rows = board[0].length;
    }

    public Boggle(int columns, int rows) {
        board = new char[columns][rows];
        this.columns = columns;
        this.rows = rows;
    }

    public void fillRandomly(long seed) {
        final Random random = new Random(seed);
        for (int c = 0; c < columns; c++) {
            for (int r = 0; r < rows; r++) {
                board[c][r] = (char) (random.nextInt(26) + 'A');
            }
        }
    }

    public char getLetter(int row, int column) {
        return board[column][row];
    }

    public int rows() {
        return rows;
    }

    public int columns() {
        return columns;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns - 1; c++) {
                sb.append(board[c][r] + ", ");
            }
            sb.append(board[columns - 1][r] + "\n");
        }
        return sb.toString();
    }

}
