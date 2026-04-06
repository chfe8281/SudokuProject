package sudoku;

import sudoku.commands.ICommand;
import sudoku.commands.MoveCommand;
import sudoku.strategies.IPlayerStrategy;

import java.util.Stack;

public class SudokuGame {
    private SudokuBoard targetBoard;
    private SudokuBoard playerBoard;
    private IPlayerStrategy playerStrategy;
    private Stack<MoveCommand> moveHistory = new Stack<>();



    public SudokuGame(SudokuBoard targetBoard, SudokuBoard playerBoard, IPlayerStrategy strategy) {
        this.targetBoard = targetBoard;
        this.playerBoard = playerBoard;
        this.playerStrategy = strategy;
    }

    public void playGame(){
        while(!gameComplete()){
            if(!playerMakeMove()){
                break;
            }
            playerBoard.printBoard();
        }
    }
    public SudokuBoard getTargetBoard(){return targetBoard;}
    public SudokuBoard getBoard() {
        return playerBoard;
    }

    public boolean playerMakeMove() {
        ICommand command = playerStrategy.selectMove(playerBoard, targetBoard);
        if (command == null) {
            System.out.println("No moves available, stopping game.");
            return false;
        }
        command.execute();
        return true;
    }

    // Switch player strategy at runtime
    public void setPlayerStrategy(IPlayerStrategy strategy) {
        this.playerStrategy = strategy;
    }

    public boolean gameComplete(){
        return playerBoard.isFilled() && playerBoard.equals(targetBoard);
    }
}
