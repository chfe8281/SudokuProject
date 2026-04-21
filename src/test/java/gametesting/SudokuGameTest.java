package gametesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sudoku.game.SudokuGame;
import sudoku.boardgameparts.*;
import sudoku.commands.CommandFactory;

import static org.junit.jupiter.api.Assertions.*;


public class SudokuGameTest {
    private final CellFactory cellFactory = new CellFactory();
    private final GroupsFactory groupsFactory = new GroupsFactory();
    private final GeneratorFactory generatorFactory = new GeneratorFactory();
    private final CommandFactory commandFactory = new CommandFactory();
    private final int BOARD_SIZE = 9;
    private final int STANDARD_BLANKS = 40;
    private SudokuGame game;


    @BeforeEach
    public void gameCreation(){
        game = SudokuGame.createDigitalGame();
    }

    @Test
    public void testDigitalPlayerGame(){
        game.playGame();
        game.displayBoard();
        game.displayTargetBoard();
        assertTrue(game.gameComplete());
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                int playerVal = game.getPlayerBoard().getCellValue(i, j);
                int targetVal = game.getTargetBoard().getCellValue(i, j);

                assertEquals(targetVal, playerVal,
                            "Mismatch at (" + i + "," + j + ")");

            }
        }
    }

    @Test
    void testGameMakesProgress() {

        int initialEmpty = game.getPlayerBoard().countEmptyCells();

        for (int i = 0; i < 5; i++) {
            game.playSingleStep();
        }

        int afterEmpty = game.getPlayerBoard().countEmptyCells();

        assertTrue(afterEmpty < initialEmpty);
    }
}
