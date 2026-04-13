package sudoku.strategies;

import sudoku.boardgameparts.IBoard;
import sudoku.boardgameparts.SudokuBoard;
import sudoku.commands.CommandFactory;
import sudoku.commands.ICommand;

public class HumanPlayerStrategy implements IPlayerStrategy{

    private CommandFactory commandFactory;
    private ICommand lastMove;
    private boolean moveReady;
    private int selectedRow;
    private int selectedCol;
    private int selectedNumber;

    public HumanPlayerStrategy(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
        this.moveReady = false;
    }



    @Override
    public ICommand selectMove(IBoard playerBoard, IBoard targetBoard) {
        if(!moveReady){
            return null;
        }

        ICommand move = commandFactory.createMoveCommand(playerBoard, selectedRow, selectedCol, selectedNumber);
        moveReady = false;
        lastMove = move;

        return move;
    }


}
