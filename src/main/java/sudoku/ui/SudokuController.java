package sudoku.ui;

import sudoku.game.IBoardGame;
import sudoku.strategies.HumanPlayerStrategy;

import javax.swing.*;

public class SudokuController implements IController {

    private IBoardGame game;
    private SudokuFrame boardFrame;

    public SudokuController(IBoardGame game, SudokuFrame boardFrame){
        this.game = game;
        this.boardFrame = boardFrame;
    }



    public void handleCellClick(int row, int col) {
        String input = JOptionPane.showInputDialog(
                boardFrame,
                "Enter number (1-" + game.getPlayerBoard().getSize() + "):"
        );

        try {
            int val = Integer.parseInt(input);
            applyMove(row, col, val);
        } catch (NumberFormatException e) {
            // ignore
        }
    }

    public void applyMove(int row, int col, int val) {
        if (game.getPlayerStrategy() instanceof HumanPlayerStrategy human) {
            human.setMove(row, col, val);
        }
        game.playerMakeMove();
    }




}
