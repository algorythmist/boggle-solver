package com.tecacet.games.boggle;

import java.util.Collection;

public class BoggleScorer {

    public int score(Collection<String> solution) {
        return solution.stream().map(w -> scoreOf(w)).mapToInt(i -> i).sum();
    }

    public int scoreOf(String word) {
        return scoreOf(word.length());
    }

    private int scoreOf(int length) {
        if (length < 3) {
            return 0;
        } else if (length < 5) {
            return 1;
        } else if (length == 5) {
            return 2;
        } else if (length == 6) {
            return 3;
        } else if (length == 7) {
            return 5;
        } else {
            return 11;
        }
    }

}
