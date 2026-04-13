package sudoku;

import sudoku.boardgameparts.IBoard;
import sudoku.commands.ICommand;
import sudoku.ui.SudokuFrame;

public interface IBoardGame {

    public void attach(SudokuFrame frame);

    public void detach();

    public void notifyFrame();

    public IBoard getTargetBoard();

    public IBoard getPlayerBoard();

    public void playerTakeTurn(ICommand command);
}
