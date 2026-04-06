package sudoku.strategies;

import sudoku.SudokuBoard;
import sudoku.commands.ICommand;

public interface IPlayerStrategy {
    ICommand selectMove(SudokuBoard playerBoard);
}
