package BoardTesting;
import Sudoku.BoardGameParts.Cell;
import Sudoku.BoardGameParts.CellFactory;
import Sudoku.BoardGameParts.GroupsFactory;
import Sudoku.BoardGameParts.IGroup;
import Sudoku.SudokuBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardTest {
    private final CellFactory cellFactory = new CellFactory();
    private final GroupsFactory groupsFactory = new GroupsFactory();
    private final int BOARD_SIZE = 9;

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
}
