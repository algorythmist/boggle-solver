package com.tecacet.games.boggle.swing;

import java.awt.event.ActionEvent;
import java.util.Collection;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tecacet.games.boggle.Boggle;
import com.tecacet.games.boggle.BoggleSolver;

@SuppressWarnings("serial")
public class BoggleController extends AbstractAction {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    public static final String SOLVE = "SOLVE";
    public static final String GENERATE = "GENERATE";
    public static final String CLEAR = "CLEAR";

    private Boggle boggle;
    private final BoggleView view;
    private int size;
    private final BoggleSolver boggleSolver;

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
            //TODO
            this.size = size;
            view.initGrid(size);
        }
        if (event.getActionCommand().equals(GENERATE)) {
            boggle.fillRandomly(System.currentTimeMillis());
            view.populate(boggle);
        } else if (event.getActionCommand().equals(SOLVE)) {
            try {
            	long startTime = System.currentTimeMillis();
                Collection<String> words = boggleSolver.solve(boggle);
                long stopTime = System.currentTimeMillis();
                logger.info("Time to solve puzzle = {} milliseconds", (stopTime-startTime));
                view.setSolutions(words);
            } catch (Exception e) {
                // TODO create error panel
                e.printStackTrace();
            }
        } else if (event.getActionCommand().equals(CLEAR)) {
            boggle = new Boggle(size, size);
            view.populate(boggle);
        }
    }

}
