package sudoku;
import sudoku.SudokuGame;
import sudoku.boardgameparts.*;
import sudoku.commands.CommandFactory;
import sudoku.strategies.DigitalPlayerStrategy;
import sudoku.strategies.HumanPlayerStrategy;
import sudoku.strategies.IPlayerStrategy;
import sudoku.ui.SudokuFrame;

import javax.swing.*;


public class Main {
    private static final CellFactory cellFactory = new CellFactory();
    private static final GroupsFactory groupsFactory = new GroupsFactory();
    private static final GeneratorFactory generatorFactory = new GeneratorFactory();
    private static final CommandFactory commandFactory = new CommandFactory();
    private static final int BOARD_SIZE = 4;
    private static final int STANDARD_BLANKS = 7;
    private static SudokuGame game;

    private static void gameCreation(){
        IPlayerStrategy strategy = new DigitalPlayerStrategy(commandFactory);
        SudokuGenerator generator = generatorFactory.createGenerator(BOARD_SIZE);
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
                .setSize(BOARD_SIZE)
                .setCells()
                .withPuzzle(solution, STANDARD_BLANKS)
                .createLineGroups()
                .createBoxGroups()
                .createBoard()
                .build();
        playerBoard.printBoard();
        game = new SudokuGame(targetBoard, playerBoard, strategy);



    }
    public static void main(String[] args) {
        gameCreation();
        SwingUtilities.invokeLater(() -> {
            SudokuFrame frame = new SudokuFrame(game);
            SudokuController controller = new SudokuController(game, frame);
            frame.setController(controller);
            frame.setVisible(true);
            new Thread(() -> game.playGame()).start();
        });
    }
}