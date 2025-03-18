package tictactoe;

import java.net.StandardSocketOptions;
import java.sql.SQLOutput;

public class Board {
    private int size;
    private Cell[][] board;

    public  Board(int size){
        this.size = size;
        board = new Cell[size][size];

        for(int i =0;i<size;i++){
            for(int j =0;j<size;j++){
                board[i][j] = new Cell(i,j);
            }
        }
    }

    public boolean makeMove(int row, int col, char symbol){
        if(row < 0 || col < 0 || row >= size || col >= size || !board[row][col].isEmpty()){
            return false;
        }
        board[row][col].setSymbol(symbol);
        return true;
    }

    public void printBoard(){
        for(int i =0;i<size;i++){
            for(int j =0;j<size;j++){
                System.out.print(board[i][j].getSymbol() == ' '? "-" : board[i][j].getSymbol());
                if(j < size - 1){
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if(i < size - 1){
                System.out.println("-------------------");
            }
        }
    }

    public Cell[][] getBoard() {
        return board;
    }

    public int getSize() {
        return size;
    }

    public Cell[][] getBoardState() {
        return board;
    }
}
