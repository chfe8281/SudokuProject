package sudoku.ui;

import sudoku.game.SudokuGame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SudokuFrame extends JFrame {
    private SudokuGame game;
    private SudokuPanel panel;
    private SudokuController controller;
    public SudokuFrame(SudokuGame game){
        this.game = game;
        this.panel = new SudokuPanel(game.getPlayerBoard());
        this.add(panel);
        this.game.attach(this);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Sudoku Game");
        this.setLocationRelativeTo(null);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int cellSize = 50;
                int col = e.getX() / cellSize;
                int row = e.getY() / cellSize;
                if (controller != null) {
                    controller.handleCellClick(row, col);
                }
            }
        });
    }

    public void update(){
        panel.repaint();
    }

    public void setController(SudokuController controller){
        this.controller = controller;
    }

    public void showGameOver() {
        JOptionPane.showMessageDialog(
                this,
                "You completed the puzzle!"
        );
    }

}
