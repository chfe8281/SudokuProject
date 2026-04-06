package gametesting;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sudoku.GeneratorFactory;
import sudoku.SudokuBoard;
import sudoku.SudokuGame;
import sudoku.SudokuGenerator;
import sudoku.boardgameparts.CellFactory;
import sudoku.boardgameparts.GroupsFactory;
import sudoku.commands.CommandFactory;
import sudoku.strategies.DigitalPlayerStrategy;
import sudoku.strategies.IPlayerStrategy;

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
        IPlayerStrategy strategy = new DigitalPlayerStrategy(commandFactory);
        SudokuGenerator generator = generatorFactory.createStandardGenerator();
        int[][] solution = generator.generateSolution();
        SudokuBoard targetBoard = SudokuBoard
                .getBuilder(cellFactory, groupsFactory)
                .setSize(BOARD_SIZE)
                .setCells()
                .withSolution(solution)
                .createLineGroups()
                .createBoxGroups()
                .createBoard()
                .build();
        targetBoard.printBoard();
        SudokuBoard playerBoard = SudokuBoard
                .getBuilder(cellFactory, groupsFactory)
                .setSize(9)
                .setCells()
                .withPuzzle(solution, STANDARD_BLANKS)
                .createLineGroups()
                .createBoxGroups()
                .createBoard()
                .build();
        playerBoard.printBoard();
        game = new SudokuGame(targetBoard, playerBoard, strategy);



    }

    @Test
    public void testDigitalPlayerGame(){
        game.playGame();
        game.getBoard().printBoard();
        game.getTargetBoard().printBoard();
        assertTrue(game.gameComplete());
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                int playerVal = game.getBoard().getCellValue(i, j);
                int targetVal = game.getTargetBoard().getCellValue(i, j);


                assertEquals(targetVal, playerVal,
                            "Mismatch at (" + i + "," + j + ")");

            }
        }
    }
}
