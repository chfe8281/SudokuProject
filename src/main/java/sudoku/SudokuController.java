package sudoku;

import sudoku.commands.CommandFactory;
import sudoku.commands.ICommand;
import sudoku.commands.MoveCommand;
import sudoku.ui.SudokuFrame;

import javax.swing.*;

public class SudokuController implements IController{

    private IBoardGame game;
    private SudokuFrame boardFrame;
    private CommandFactory commandFactory;

    public SudokuController(IBoardGame game, SudokuFrame boardFrame, CommandFactory commandFactory){
        this.game = game;
        this.boardFrame = boardFrame;
    }


    public boolean tryMove(int row, int col, int input){

        ICommand moveCommand = commandFactory.createMoveCommand(game.getPlayerBoard(), row, col, input);
        game.playerTakeTurn(moveCommand);
        /*if (game.getPlayerBoard().isFixed(row, col)){
            System.out.println("Cannot change a fixed cell at (" + row + "," + col + ")");
            return false;
        }
        if(game.getPlayerBoard().isValidMove(row, col, input)){
            game.getPlayerBoard().setCellValue(row, col, input);
        }
        if(game.getTargetBoard().getCellValue(row, col) != input){
            game.getPlayerBoard().setIsWrong(row, col, true);
        }
        else{
            game.getPlayerBoard().setIsWrong(row, col, false);
        }
        return true;*/
        return true;

    }

}
