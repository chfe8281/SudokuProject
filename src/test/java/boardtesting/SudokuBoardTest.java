package boardtesting;

import org.junit.jupiter.api.Test;
import sudoku.GeneratorFactory;
import sudoku.SudokuBoard;
import sudoku.SudokuGenerator;
import sudoku.boardgameparts.CellFactory;
import sudoku.boardgameparts.Cell;
import sudoku.boardgameparts.GroupsFactory;
import sudoku.boardgameparts.IGroup;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardTest {
    private final CellFactory cellFactory = new CellFactory();
    private final GroupsFactory groupsFactory = new GroupsFactory();
    private final GeneratorFactory generatorFactory = new GeneratorFactory();
    private final int BOARD_SIZE = 9;
    private final int STANDARD_BLANKS = 40;

    @Test
    public void testBoardSizes(){
        SudokuBoard board = SudokuBoard.getBuilder(cellFactory, groupsFactory)
                .setSize(BOARD_SIZE)
                .setCells()
                .createLineGroups()
                .createBoxGroups()
                .createBoard()
                .build();
        System.out.print("TEST RUNNING");
        assertNotNull(board);
        assertEquals(BOARD_SIZE, board.getBoxGroups().size());
        assertEquals(BOARD_SIZE, board.getHorizontalLineGroups().size());
        assertEquals(BOARD_SIZE, board.getVerticalLineGroups().size());
    }

    @Test
    public void testGroupsContainCells(){
        SudokuBoard board = SudokuBoard.getBuilder(cellFactory, groupsFactory)
                .setSize(9)
                .setCells()
                .createLineGroups()
                .createBoxGroups()
                .createBoard()
                .build();

        for(IGroup group: board.getHorizontalLineGroups()){
            assertFalse(group.getCells().isEmpty());
        }
        for(IGroup group: board.getVerticalLineGroups()){
            assertFalse(group.getCells().isEmpty());
        }
        for(IGroup group: board.getBoxGroups()){
            assertFalse(group.getCells().isEmpty());
        }
    }

    @Test
    public void testGroupsSizes(){
        SudokuBoard board = SudokuBoard.getBuilder(cellFactory, groupsFactory)
                .setSize(9)
                .setCells()
                .createLineGroups()
                .createBoxGroups()
                .createBoard()
                .build();

        for(IGroup group: board.getHorizontalLineGroups()){
            assertEquals(BOARD_SIZE, group.getCells().size());
        }
        for(IGroup group: board.getVerticalLineGroups()){
            assertEquals(BOARD_SIZE, group.getCells().size());
        }
        for(IGroup group: board.getBoxGroups()){
            assertEquals(BOARD_SIZE, group.getCells().size());
        }
    }

    @Test
    public void testCellBelongsToCorrectGroups() {
        SudokuBoard board = SudokuBoard.getBuilder(cellFactory, groupsFactory)
                .setSize(9)
                .setCells()
                .createLineGroups()
                .createBoxGroups()
                .createBoard()
                .build();

        Cell testCell = board.getHorizontalLineGroups().getFirst().getCells().getFirst();
        int occurrences = 0;

        for(IGroup group: board.getHorizontalLineGroups()){
            if(group.getCells().contains(testCell)){
                occurrences++;
            }
        }
        for(IGroup group: board.getVerticalLineGroups()){
            if(group.getCells().contains(testCell)){
                occurrences++;
            }
        }
        for(IGroup group: board.getBoxGroups()){
            if(group.getCells().contains(testCell)){
                occurrences++;
            }
        }

        assertEquals(3, occurrences);
    }


    @Test
    public void testTargetAndPlayerBoards(){
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
        System.out.println("TARGET BOARD\n");
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
        System.out.println("PLAYER BOARD\n");
        playerBoard.printBoard();

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                int playerVal = playerBoard.getCellValue(i, j);
                int targetVal = targetBoard.getCellValue(i, j);

                if (playerVal != 0) {
                    assertEquals(targetVal, playerVal,
                            "Mismatch at (" + i + "," + j + ")");
                }
            }
        }
    }
}
