package sudoku.ui;

import sudoku.boardgameparts.GroupsFactory;
import sudoku.boardgameparts.IBoard;
import sudoku.boardgameparts.SudokuBoard;

import javax.swing.*;
import java.awt.*;

public class SudokuPanel extends JPanel {
    private IBoard board;
    private final int cellSize = 50;

    public SudokuPanel(IBoard board) {
        this.board = board;
        int size = board.getSize();
        this.setPreferredSize(new Dimension(size * cellSize, size * cellSize));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // just do standard paint component

        int size = board.getSize();
        int boxSize = board.getBoxSize();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int x = col * cellSize;
                int y = row * cellSize;

                // Fill fixed cells with light gray
                if (board.isFixed(row, col)) {
                    g.setColor(new Color(220, 220, 220));
                    g.fillRect(x, y, cellSize, cellSize);
                }

                // Draw cell border
                g.setColor(Color.BLACK);
                g.drawRect(x, y, cellSize, cellSize);

                // Draw number
                int value = board.getCellValue(row, col);
                if (value != 0) {
                    if(board.isWrong(row, col)){
                        g.setColor(Color.RED);
                    }
                    else{
                        g.setColor(Color.BLACK);
                    }
                    g.setFont(new Font("Arial", Font.BOLD, 20));
                    FontMetrics fm = g.getFontMetrics();
                    int textX = x + (cellSize - fm.stringWidth(String.valueOf(value))) / 2;
                    int textY = y + ((cellSize - fm.getHeight()) / 2) + fm.getAscent();
                    g.drawString(String.valueOf(value), textX, textY);
                }
            }
        }

        // Draw thicker box lines
        g.setColor(Color.BLACK);
        for (int i = 0; i <= size; i++) {
            int thickness = (i % boxSize == 0) ? 3 : 1;
            ((Graphics2D) g).setStroke(new BasicStroke(thickness));

            // Horizontal line
            g.drawLine(0, i * cellSize, size * cellSize, i * cellSize);
            // Vertical line
            g.drawLine(i * cellSize, 0, i * cellSize, size * cellSize);
        }
    }
}
