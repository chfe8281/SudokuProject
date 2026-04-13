package sudoku;

import sudoku.boardgameparts.IBoard;
import sudoku.boardgameparts.SudokuBoard;
import sudoku.commands.ICommand;
import sudoku.commands.MoveCommand;
import sudoku.strategies.IPlayerStrategy;
import sudoku.ui.SudokuFrame;

import java.util.Stack;

public class SudokuGame implements IBoardGame {
    private IBoard targetBoard;
    private IBoard playerBoard;
    private IPlayerStrategy playerStrategy;
    private Stack<MoveCommand> moveHistory = new Stack<>();
    private SudokuFrame frame;


    public SudokuGame(SudokuBoard targetBoard, SudokuBoard playerBoard, IPlayerStrategy strategy) {
        this.targetBoard = targetBoard;
        this.playerBoard = playerBoard;
        this.playerStrategy = strategy;
    }

    public void displayBoard(){
        playerBoard.printBoard();
    }

    public void displayTargetBoard(){
        targetBoard.printBoard();
    }

    public void playGame(){
        while(!gameComplete()){
            if(!playerMakeMove()){
                break;
            }
            displayBoard();
        }
    }
    public IBoard getTargetBoard(){return targetBoard;}
    public IBoard getPlayerBoard() {
        return playerBoard;
    }

    public boolean playerMakeMove() {
        ICommand command = playerStrategy.selectMove(playerBoard, targetBoard);
        if (command == null) {
            System.out.println("No moves available, stopping game.");
            return false;
        }
        command.execute();
        notifyFrame();
        return true;
    }

    public void playerTakeTurn(ICommand command){
        command.execute();
        notifyFrame();
    }

    public void attach(SudokuFrame frame){
        this.frame = frame;
    }

    public void detach(){
        this.frame = null;
    }
    public void notifyFrame(){
        if(frame != null){
            frame.update();
        }
    }
    // Switch player strategy at runtime
    public void setPlayerStrategy(IPlayerStrategy strategy) {
        this.playerStrategy = strategy;
    }

    public boolean gameComplete(){
        return playerBoard.isFilled() && playerBoard.equals(targetBoard);
    }
}
