package gametesting;

import org.junit.jupiter.api.Test;
import sudoku.SudokuBoard;
import sudoku.SudokuGame;
import sudoku.boardgameparts.CellFactory;
import sudoku.boardgameparts.GroupsFactory;

import static org.junit.jupiter.api.Assertions.*;


public class SudokuGameTest {
    private final CellFactory cellFactory = new CellFactory();
    private final GroupsFactory groupsFactory = new GroupsFactory();
    private final int BOARD_SIZE = 9;

    @Test
    public void testGameCreation(){
        SudokuBoard board = SudokuBoard.getBuilder(cellFactory, groupsFactory)
                .setSize(BOARD_SIZE)
                .setCells()
                .createLineGroups()
                .createBoxGroups()
                .createBoard()
                .build();
        System.out.print("TEST RUNNING");
        SudokuGame game = new SudokuGame(board);
        SudokuBoard gameBoard = game.getBoard();
        assertEquals(board, gameBoard);
    }
}
