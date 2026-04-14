package sudoku;

import sudoku.boardgameparts.IBoard;
import sudoku.commands.ICommand;
import sudoku.strategies.IPlayerStrategy;
import sudoku.ui.SudokuFrame;

public interface IBoardGame {

    public void attach(SudokuFrame frame);

    public void detach();

    public void notifyFrame();
    public boolean gameComplete();
    public IBoard getTargetBoard();
    public boolean playerMakeMove();
    public IBoard getPlayerBoard();
    public IPlayerStrategy getPlayerStrategy();
    public void playerTakeTurn(ICommand command);
}
