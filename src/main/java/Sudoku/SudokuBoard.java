package Sudoku;

import Sudoku.BoardGameParts.Cell;
import Sudoku.BoardGameParts.CellFactory;
import Sudoku.BoardGameParts.GroupsFactory;
import Sudoku.BoardGameParts.IGroup;

import java.util.ArrayList;
import java.util.List;

public class SudokuBoard {
    private List<IGroup> horizontalLineGroups = new ArrayList<>();
    private List<IGroup> verticalLineGroups = new ArrayList<>();
    private List<IGroup> boxGroups = new ArrayList<>();



    public static SudokuBoardBuilder getBuilder(CellFactory cellFactory, GroupsFactory groupsFactory){
        return new SudokuBoardBuilder(cellFactory, groupsFactory);
    };


    public static class SudokuBoardBuilder{
        private SudokuBoard currentBoard;
        private List<IGroup> horizontalLineGroups = new ArrayList<>();
        private List<IGroup> verticalLineGroups = new ArrayList<>();
        private List<IGroup> boxLineGroups = new ArrayList<>();
        private int boardSize;
        private Cell[][] cells;


        private CellFactory cellFactory;
        private GroupsFactory groupsFactory;
        /*
            - createBoard(int row, int column)
            - createLineGroups(int size)
            - createBoxGroups(int size)
         */
        private SudokuBoardBuilder(CellFactory cellFactory, GroupsFactory groupsFactory){
            this.cellFactory = cellFactory;
            this.groupsFactory = groupsFactory;
        }
        public SudokuBoard build(){ return this.currentBoard; };

        public void setSize(int size){this.boardSize = size;}

        public void createLineGroups(int size){
            horizontalLineGroups = groupsFactory.createGroups("line", this.boardSize, this.boardSize);
            verticalLineGroups = groupsFactory.createGroups("Line", this.boardSize, this.boardSize);
            for(int i = 0; i < boardSize; i++){
                List<Cell> verticalLineGroup = new ArrayList<>();
                for(int j = 0; j < boardSize; j++){
                    verticalLineGroup.add(cells[i][j]);
                }
                verticalLineGroups.get(i).setCells(verticalLineGroup);
            }
            for(int j = 0; j < boardSize; j++){
                List<Cell> horizontalLineGroup = new ArrayList<>();
                for(int i = 0; i < boardSize; i++){
                    horizontalLineGroup.add(cells[i][j]);
                }
                horizontalLineGroups.get(j).setCells(horizontalLineGroup);
            }
        }

        public void createBoard(int row, int column){
            cells = cellFactory.createCells(row, column);

        }


    }
}
