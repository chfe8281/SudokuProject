package BoardTesting;
import Sudoku.BoardGameParts.CellFactory;
import Sudoku.BoardGameParts.GroupsFactory;
import Sudoku.SudokuBoard;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SudokuBoardTest {
    private final CellFactory cellFactory = new CellFactory();
    private final GroupsFactory groupsFactory = new GroupsFactory();
    private final int BOARD_SIZE = 9;

    @Test
    public void testBoardBuilder(){
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

}
