package com.tecacet.games.boggle.swing;

import java.awt.event.ActionListener;

import com.tecacet.games.boggle.Boggle;

public interface BoggleView {

	void populate(Boggle boggle);

	void setSolutions(Iterable<String> words);

	void setController(ActionListener actionListener);

    void initGrid(int size);

}