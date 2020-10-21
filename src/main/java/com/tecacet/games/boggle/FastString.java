package com.tecacet.games.boggle;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * String implemented as a fixed-size array allows fast operations for append and removeLast
 *
 * @author dimitri
 */
public class FastString {

    private final char[] buffer;
    private int length;

    public FastString() {
        this(50);
    }

    public FastString(int maxSize) {
        this.buffer = new char[maxSize];
    }

    public static FastString fromString(String string) {
        FastString fastString = new FastString();
        IntStream.range(0, string.length()).map(string::charAt).forEach(c -> fastString.append((char) c));
        return fastString;
    }

    public void append(char c) {
        buffer[length++] = c;
    }

    public void removeLast() {
        length--;
    }

    public int length() {
        return length;
    }

    public char charAt(int index) {
        return buffer[index];
    }

    public String toString() {
        return new String(Arrays.copyOf(buffer, length));
    }

}
