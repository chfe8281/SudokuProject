package sudoku.boardgameparts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface IGroup {
    void setCells(List<Cell> cells);
    List<Integer> unusedValues();

    List<Integer> repeatedValues();

    List<Cell> getCells();


}
