package com.tecacet.games.boggle.swing;

import com.tecacet.games.boggle.Boggle;
import com.tecacet.games.boggle.BoggleSolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ActionEvent;
import java.util.Collection;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;

public class BoggleController extends AbstractAction {

    public static final String SOLVE = "SOLVE";
    public static final String GENERATE = "GENERATE";
    public static final String CLEAR = "CLEAR";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final BoggleView view;
    private final BoggleSolver boggleSolver;
    private Boggle boggle;
    private int size;

    public BoggleController(BoggleView view, int size, BoggleSolver boggleSolver) {
        boggle = new Boggle(size, size);
        this.view = view;
        this.size = size;
        view.setController(this);
        this.boggleSolver = boggleSolver;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source instanceof JComboBox) {
            JComboBox<Integer> comboBox = (JComboBox<Integer>) source;
            Integer size = (Integer) comboBox.getSelectedItem();
            this.size = size == null ? 10 : size;
            view.initGrid(this.size);
        }
        switch (event.getActionCommand()) {
            case GENERATE:
                boggle.fillRandomly(System.currentTimeMillis());
                view.populate(boggle);
                break;
            case SOLVE:
                try {
                    long startTime = System.currentTimeMillis();
                    Collection<String> words = boggleSolver.solve(boggle);
                    long stopTime = System.currentTimeMillis();
                    logger.info("Time to solve puzzle = {} milliseconds", (stopTime - startTime));
                    view.setSolutions(words);
                } catch (Exception e) {
                    // TODO create error panel
                    e.printStackTrace();
                }
                break;
            case CLEAR:
                boggle = new Boggle(size, size);
                view.populate(boggle);
                break;
        }
    }

}
