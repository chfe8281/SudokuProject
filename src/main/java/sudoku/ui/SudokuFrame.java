package sudoku.ui;

import sudoku.SudokuGame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SudokuFrame extends JFrame {
    private SudokuGame game;
    private SudokuPanel panel;
    private int selectedRow = -1;
    private int selectedCol = -1;

    public SudokuFrame(SudokuGame game){
        this.game = game;
        this.panel = new SudokuPanel(game.getPlayerBoard());

        this.add(panel);
        this.pack();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Sudoku Game");
        this.setLocationRelativeTo(null);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int cellSize = 50; // must match SudokuPanel
                selectedCol = e.getX() / cellSize;
                selectedRow = e.getY() / cellSize;

                if (selectedRow >= 0 && selectedRow < game.getPlayerBoard().getSize() &&
                        selectedCol >= 0 && selectedCol < game.getPlayerBoard().getSize()) {

                    String input = JOptionPane.showInputDialog(
                            SudokuFrame.this,
                            "Enter number (1-"+game.getPlayerBoard().getSize()+"):"
                    );
                    /*
                    try {
                        int val = Integer.parseInt(input);
                        if(game.getPlayerBoard().isValidMove(selectedRow, selectedCol, val)){
                            game.getPlayerBoard().setCellValue(selectedRow, selectedCol, val);
                            if(game.getTargetBoard().getCellValue(selectedRow, selectedCol) != val){
                                game.getPlayerBoard().setIsWrong(selectedRow, selectedCol, true);
                            }
                            else{
                                game.getPlayerBoard().setIsWrong(selectedRow, selectedCol, false);
                            }
                            panel.repaint();

                            if(game.gameComplete()){
                                JOptionPane.showMessageDialog(SudokuFrame.this, "You completed the puzzle!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(SudokuFrame.this, "Invalid move!");
                        }
                    } catch (NumberFormatException ex) {
                        //ignore
                    }*/
                }
            }
        });
    }

    public void update(){
        panel.repaint();
    }


}
