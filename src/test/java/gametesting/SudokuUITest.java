package gametesting;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sudoku.ui.SudokuController;
import sudoku.game.SudokuGame;
import sudoku.ui.SudokuFrame;
import org.assertj.swing.fixture.FrameFixture;

import javax.swing.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SudokuUITest {
    private SudokuGame game;
    private SudokuFrame frame;
    private SudokuController controller;
    private FrameFixture window;
    @BeforeEach
    void setUp() {
        game = SudokuGame.createHumanGame();

        frame = new SudokuFrame(game);
        controller = new SudokuController(game, frame);

        frame.setController(controller);

        window = new FrameFixture(frame);
        window.show(); // shows GUI in test mode
    }

    @Test
    void frameInitializesCorrectly() {
        assertNotNull(frame);
        assertEquals("Sudoku Game", frame.getTitle());
    }

    @Test
    void enteringNumberUpdatesBoard() throws InterruptedException {
        List<Integer> unfilledPosition = game.getPlayerBoard().getFirstNotFilled();
        if(unfilledPosition != null) {
            SwingUtilities.invokeLater(() -> controller.applyMove(unfilledPosition.getFirst(), unfilledPosition.getLast(), 5));

            Thread.sleep(2000);
            assertEquals(5, game.getPlayerBoard().getCellValue(unfilledPosition.getFirst(), unfilledPosition.getLast()));
        }
    }

    @AfterEach
    void tearDown() {
        window.cleanUp();
        game.detach();
    }
}

