package sudoku.game;

import sudoku.boardgameparts.*;
import sudoku.commands.CommandFactory;
import sudoku.commands.ICommand;
import sudoku.strategies.DigitalPlayerStrategy;
import sudoku.strategies.HumanPlayerStrategy;
import sudoku.strategies.IPlayerStrategy;
import sudoku.ui.SudokuFrame;

import javax.swing.*;

public class SudokuGame implements IBoardGame {
    private IBoard targetBoard;
    private IBoard playerBoard;
    private IPlayerStrategy playerStrategy;
    private SudokuFrame frame;
    private static final CellFactory cellFactory = new CellFactory();
    private static final GroupsFactory groupsFactory = new GroupsFactory();
    private static final GeneratorFactory generatorFactory = new GeneratorFactory();
    private static final CommandFactory commandFactory = new CommandFactory();
    private static final int SMALL_BOARD_SIZE = 4;
    private static final int SMALL_STANDARD_BLANKS = 7;
    private static final int LARGE_BOARD_SIZE = 9;
    private static final int LARGE_STANDARD_BLANKS = 40;


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
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
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
        if (command == null) return false;
        command.execute();
        notifyFrame();
        if (gameComplete()) {
            notifyGameEnd(); // or however you're signaling observers
        }
        return true;
    }



    public void playSingleStep(){
        playerMakeMove();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        displayBoard();
    }
    public void attach(SudokuFrame frame){
        this.frame = frame;
    }

    public void detach(){
        this.frame = null;
    }
    public void notifyFrame(){
        if(frame != null){
            SwingUtilities.invokeLater(() -> frame.update());
        }
    }

    public void notifyGameEnd(){
        if(frame != null){
            frame.showGameOver();
        }
    }

    public IPlayerStrategy getPlayerStrategy(){return this.playerStrategy;}

    public boolean gameComplete(){
        return playerBoard.isFilled() && playerBoard.equals(targetBoard);
    }

    public static SudokuGame createDigitalGame(){
        IPlayerStrategy strategy = new DigitalPlayerStrategy(commandFactory);
        SudokuGenerator generator = generatorFactory.createGenerator(LARGE_BOARD_SIZE);
        int[][] solution = generator.generateSolution();
        SudokuBoard targetBoard = SudokuBoard
                .getBuilder(cellFactory, groupsFactory)
                .setSize(LARGE_BOARD_SIZE)
                .setCells()
                .withSolution(solution)
                .createLineGroups()
                .createBoxGroups()
                .createBoard()
                .build();
        targetBoard.printBoard();
        SudokuBoard playerBoard = SudokuBoard
                .getBuilder(cellFactory, groupsFactory)
                .setSize(LARGE_BOARD_SIZE)
                .setCells()
                .withPuzzle(solution, LARGE_STANDARD_BLANKS)
                .createLineGroups()
                .createBoxGroups()
                .createBoard()
                .build();
        playerBoard.printBoard();
        SudokuGame game = new SudokuGame(targetBoard, playerBoard, strategy);
        return game;
    }
    public static SudokuGame createHumanGame(){

        IPlayerStrategy strategy = new HumanPlayerStrategy(commandFactory);
        SudokuGenerator generator = generatorFactory.createGenerator(SMALL_BOARD_SIZE);
        int[][] solution = generator.generateSolution();
        SudokuBoard targetBoard = SudokuBoard
                .getBuilder(cellFactory, groupsFactory)
                .setSize(SMALL_BOARD_SIZE)
                .setCells()
                .withSolution(solution)
                .createLineGroups()
                .createBoxGroups()
                .createBoard()
                .build();
        targetBoard.printBoard();
        SudokuBoard playerBoard = SudokuBoard
                .getBuilder(cellFactory, groupsFactory)
                .setSize(SMALL_BOARD_SIZE)
                .setCells()
                .withPuzzle(solution, SMALL_STANDARD_BLANKS)
                .createLineGroups()
                .createBoxGroups()
                .createBoard()
                .build();
        playerBoard.printBoard();
        SudokuGame game = new SudokuGame(targetBoard, playerBoard, strategy);
        return game;

    }
}
