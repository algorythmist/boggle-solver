package com.tecacet.games.boggle.swing;

import com.tecacet.games.boggle.Boggle;

import java.awt.event.ActionListener;

public interface BoggleView {

    void populate(Boggle boggle);

    void setSolutions(Iterable<String> words);

    void setController(ActionListener actionListener);

    void initGrid(int size);

}