package com.tecacet.games.boggle;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.tecacet.games.boggle.io.BoggleReader;
import com.tecacet.games.boggle.io.DictionaryReader;

public class TrieBoggleSolverTest {

    private DictionaryReader dictionaryReader = new DictionaryReader();
    private BoggleReader reader = new BoggleReader();

    @Test
    public void testSolve1() throws Exception {
        Boggle boggle = new Boggle(4, 4);
        boggle.fillRandomly(23384378L);
        Trie trie = readDictionary("enable2k");
        BoggleSolver solver = new TrieBoggleSolver(trie);
        Iterable<String> words = solver.solve(boggle);

        assertEquals(
                "[DYING, FIN, FIND, FINS, FIX, IFS, INN, INS, NIX, NIXY, QUIN, QUINS, SIN, SING, SIX, XIS, YIN, YINS]",
                words.toString());
    }

    @Test
    public void test4x4() throws IOException {
        Trie dictionary = readDictionary("algs4");
        TrieBoggleSolver solver = new TrieBoggleSolver(dictionary);
        Boggle board = readBoggleBoard("board4x4");
        Collection<String> solution = solver.solve(board);
        int score = new BoggleScorer().score(solution);
        assertEquals(33, score);
    }

    @Test
    public void testQU() throws IOException {
        Trie dictionary = readDictionary("algs4");
        TrieBoggleSolver solver = new TrieBoggleSolver(dictionary);
        Boggle board = readBoggleBoard("/board-q");
        Collection<String> solution = solver.solve(board);
        int score = new BoggleScorer().score(solution);
        assertEquals(84, score);
    }

    @Test
    public void testAll() throws IOException {
        long time = System.currentTimeMillis();
        Trie dictionary = readDictionary("yawl");

        String parentDir = "src/main/resources/boards/";
        File dir = new File(parentDir);
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                continue;
            }
            if (file.getName().startsWith("board-points")) {
                String boardFile = file.getName();
                //System.out.println("testing " + boardFile);
                FileInputStream fis = new FileInputStream(parentDir + boardFile);
                Boggle board = reader.read(fis);
                TrieBoggleSolver solver = new TrieBoggleSolver(dictionary);
                Collection<String> solution = solver.solve(board);
                int score = new BoggleScorer().score(solution);
                int fileScore = getScore(boardFile);
                assertEquals(fileScore, score);
            }
        }
        time = System.currentTimeMillis() - time;
        System.err.println("Total time = " + time);
    }
    
    private Pattern pattern = Pattern.compile("\\d+");
    
    private int getScore(String boardFile) {
        Matcher matcher = pattern.matcher(boardFile);
        assertTrue(matcher.find());
        return Integer.parseInt(matcher.group());
    }
    
    private Trie readDictionary(String dictionary) throws IOException {
        InputStream is =
                this.getClass().getClassLoader()
                        .getResourceAsStream(String.format("dictionaries/dictionary-%s.txt", dictionary));
        return dictionaryReader.readDictionaryAsTrie(is);
    }

    private Boggle readBoggleBoard(String boardName) throws IOException {
        InputStream is =
                this.getClass().getClassLoader().getResourceAsStream(String.format("boards/%s.txt", boardName));
        return reader.read(is);
    }
}
