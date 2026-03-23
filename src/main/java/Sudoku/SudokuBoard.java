package Sudoku;

import Sudoku.BoardGameParts.*;

import java.util.ArrayList;
import java.util.List;

public class SudokuBoard {
    private List<IGroup> horizontalLineGroups = new ArrayList<>();
    private List<IGroup> verticalLineGroups = new ArrayList<>();
    private List<IGroup> boxGroups = new ArrayList<>();

    private SudokuBoard(List<IGroup> boxGroups, List<IGroup> verticalLineGroups, List<IGroup> horizontalLineGroups){
        this.horizontalLineGroups = horizontalLineGroups;
        this.boxGroups = boxGroups;
        this.verticalLineGroups = verticalLineGroups;
    }

    public List<IGroup> getHorizontalLineGroups(){
        return new ArrayList<>(this.horizontalLineGroups);
    }

    public List<IGroup> getVerticalLineGroups(){
        return new ArrayList<>(this.verticalLineGroups);
    }

    public List<IGroup> getBoxGroups(){
        return new ArrayList<>(this.boxGroups);
    }

    public static SudokuBoardBuilder getBuilder(CellFactory cellFactory, GroupsFactory groupsFactory){
        return new SudokuBoardBuilder(cellFactory, groupsFactory);
    };


    public static class SudokuBoardBuilder{
        private SudokuBoard currentBoard;
        private List<IGroup> horizontalLineGroups = new ArrayList<>();
        private List<IGroup> verticalLineGroups = new ArrayList<>();
        private List<IGroup> boxGroups = new ArrayList<>();
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

        public SudokuBoardBuilder setSize(int size){this.boardSize = size; return this;}

        public SudokuBoardBuilder createLineGroups(){
            this.horizontalLineGroups = groupsFactory.createGroups("line", this.boardSize, this.boardSize);
            this.verticalLineGroups = groupsFactory.createGroups("line", this.boardSize, this.boardSize);
            /*for(int i = 0; i < boardSize; i++){
                List<Cell> verticalLineGroup = new ArrayList<>();
                for(int j = 0; j < boardSize; j++){
                    verticalLineGroup.add(cells[i][j]);
                }
                this.verticalLineGroups.get(i).setCells(verticalLineGroup);
            }
            for(int j = 0; j < boardSize; j++){
                List<Cell> horizontalLineGroup = new ArrayList<>();
                for(int i = 0; i < boardSize; i++){
                    horizontalLineGroup.add(cells[i][j]);
                }
                this.horizontalLineGroups.get(j).setCells(horizontalLineGroup);
            }*/
            setVerticalLineGroupCells();
            setHorizontalLineGroupCells();

            return this;
        }

        private void setVerticalLineGroupCells(){
            for(int i = 0; i < boardSize; i++){
                List<Cell> verticalLineGroup = new ArrayList<>();
                for(int j = 0; j < boardSize; j++){
                    verticalLineGroup.add(cells[i][j]);
                }
                this.verticalLineGroups.get(i).setCells(verticalLineGroup);
            }
        }

        private void setHorizontalLineGroupCells(){
            for(int j = 0; j < boardSize; j++){
                List<Cell> horizontalLineGroup = new ArrayList<>();
                for(int i = 0; i < boardSize; i++){
                    horizontalLineGroup.add(cells[i][j]);
                }
                this.horizontalLineGroups.get(j).setCells(horizontalLineGroup);
            }
        }

        private void setBoxGroupCells(){

            int boxSize = (int) Math.sqrt(this.boardSize);
            int boxIndex = 0;
            for(int boxRow = 0; boxRow < boxSize; boxRow++){
                for(int boxCol = 0; boxCol < boxSize; boxCol++){
                    Cell[][] box = new Cell[boxSize][boxSize];
                    for(int i = 0; i < boxSize; i++){
                        for(int j = 0; j < boxSize; j++){
                            int boardRow = boxRow*boxSize + i;
                            int boardCol = boxCol*boxSize + j;
                            box[i][j] = cells[boardRow][boardCol];
                        }
                    }
                    if(this.boxGroups.get(boxIndex) instanceof BoxGroup b){
                        b.setCells(box);
                    }
                    boxIndex++;
                }
            }
        }

        public SudokuBoardBuilder createBoxGroups(){
            this.boxGroups = groupsFactory.createGroups("box", this.boardSize, this.boardSize);
            setBoxGroupCells();
            return this;
        }

        public SudokuBoardBuilder setCells(){
            cells = cellFactory.createCells(this.boardSize, this.boardSize);
            return this;
        }

        public SudokuBoardBuilder createBoard(){
            this.currentBoard = new SudokuBoard(this.boxGroups, this.verticalLineGroups, this.horizontalLineGroups);
            return this;
        }


    }
}
