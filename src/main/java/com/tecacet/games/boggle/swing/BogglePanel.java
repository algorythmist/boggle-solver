package com.tecacet.games.boggle.swing;

import com.tecacet.games.boggle.Boggle;
import com.tecacet.games.boggle.BoggleSolver;
import com.tecacet.games.boggle.TrieBoggleSolver;
import com.tecacet.games.boggle.io.DictionaryReader;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.text.ParseException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.MaskFormatter;

public class BogglePanel extends JPanel implements BoggleView {

    private static final int DEFAULT_SIZE = 12;

    private final int size = DEFAULT_SIZE;

    private final AbstractFormatter formatter;
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JButton generate;
    private final JButton solve;
    private final JButton clear;
    private final JComboBox<Integer> sizeChooser = new JComboBox<>(new Integer[] {3, 4, 5, 6, 7, 8, 9, 10});
    private JFormattedTextField[][] grid;
    private final JPanel gridPanel = new JPanel();

    public BogglePanel() throws ParseException {
        formatter = new MaskFormatter("U");

        gridPanel.setLayout(new GridLayout(size, size));

        generate = new JButton("Generate Random Game");
        generate.setActionCommand(BoggleController.GENERATE);
        solve = new JButton("Solve");
        solve.setActionCommand(BoggleController.SOLVE);
        clear = new JButton("Clear Board");
        clear.setActionCommand(BoggleController.CLEAR);

        initGrid(size);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(generate);
        buttonPanel.add(solve);
        buttonPanel.add(clear);

        JList<String> solutions = new JList<>(listModel);

        JPanel controlPanel = new JPanel();
        JLabel sizeLabel = new JLabel("Board Size:");

        controlPanel.add(sizeLabel);
        sizeChooser.setSelectedItem(4);
        controlPanel.add(sizeChooser);

        setLayout(new BorderLayout());
        //TODO: Fix size control panel
        // add(controlPanel, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.WEST);
        add(new JScrollPane(solutions), BorderLayout.EAST);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] argv) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                createAndShowGUI();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void createAndShowGUI() throws Exception {
        DictionaryReader dictionaryReader = new DictionaryReader();
        BogglePanel view = new BogglePanel();
        InputStream is = view.getClass().getClassLoader().getResourceAsStream("dictionaries/dictionary-enable2k.txt");
        BoggleSolver boggleSolver = new TrieBoggleSolver(dictionaryReader.readDictionaryAsTrie(is));
        JFrame f = new JFrame("Boggle");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new BoggleController(view, DEFAULT_SIZE, boggleSolver);
        f.getContentPane().add(view);
        f.pack();
        f.setResizable(false);
        f.setVisible(true);
    }

    @Override
    public void initGrid(int size) {
        gridPanel.removeAll();
        grid = new JFormattedTextField[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new JFormattedTextField(formatter);// (i, j);
                // TODO set button size relative to grid size
                // int buttonSize = 500 / size;
                grid[i][j].setPreferredSize(new Dimension(30, 30));
                gridPanel.add(grid[i][j]);
            }
        }
        this.updateUI();
    }

    @Override
    public void setController(ActionListener actionListener) {
        generate.addActionListener(actionListener);
        solve.addActionListener(actionListener);
        clear.addActionListener(actionListener);
        sizeChooser.addActionListener(actionListener);
    }

    @Override
    public void populate(Boggle boggle) {
        for (int row = 0; row < boggle.rows(); row++) {
            for (int column = 0; column < boggle.columns(); column++) {
                grid[row][column].setText(String.valueOf(boggle.getLetter(row, column)));
            }
        }
        listModel.clear();
    }

    @Override
    public void setSolutions(Iterable<String> words) {
        listModel.removeAllElements();
        for (String word : words) {
            listModel.addElement(word);
        }
    }
}
