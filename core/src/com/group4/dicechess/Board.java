package com.group4.dicechess;

import com.group4.dicechess.Pieces.Bishop;
import com.group4.dicechess.Pieces.King;
import com.group4.dicechess.Pieces.Knight;
import com.group4.dicechess.Pieces.NullPiece;
import com.group4.dicechess.Pieces.Pawn;
import com.group4.dicechess.Pieces.Queen;
import com.group4.dicechess.Pieces.Rook;

public class Board {
    private Square[][] board;

    public Board(){
        board = new Square[8][8];
        setUpBoard();
    }

    public Square getSquare(int row, int column){
        return board[row][column];
    }

    public void setSquare(int row, int column, Piece piece) {
        board[row][column] =  new Square(row, column, piece);
    }

    public void setNullPiece(int row, int column) {
        board[row][column] = new Square(row, column, new NullPiece(true));
    }

    public Piece getPieceOfSquare(int row, int column) {
        return board[row][column].getPiece();
    }

    public boolean isWhite(int currentRow, int currentColumn) {
        if(board[currentRow][currentRow].getPiece().getWhiteStatus()) {
            return true;
        }
        return false;
    }

    public boolean isNull(int currentRow, int currentColumn) {
        if(board[currentRow][currentRow].getPiece().getValue().equals("O")) {
            return true;
        }
        return false;
    }


    public void printBoard(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                    if(board[i][j].getPiece().getWhiteStatus() && !board[i][j].getPiece().getNullStatus()) {
                        System.out.print("w"+board[i][j].getPiece().getValue() + " ");
                    } else if(!board[i][j].getPiece().getWhiteStatus() && !board[i][j].getPiece().getNullStatus()) {
                        System.out.print("b"+board[i][j].getPiece().getValue() + " ");
                    } else {
                        System.out.print(board[i][j].getPiece().getValue() + "  ");
                    }
            }
            System.out.println();
        }
        System.out.println("------------------------");
    }

    private void setUpBoard(){
        // Set up the pawns
        for(int i = 0; i < board.length; i++){
            board[1][i] = new Square(i, 1, new Pawn(false));
            board[6][i] = new Square(i, 1, new Pawn(true));
        }

        // Set up empty squares
        for(int i = 2; i < 6; i++) {
            for(int j = 0; j < board[i].length; j++) {
                board[i][j] = new Square(i, j, new NullPiece(true));
            }

        }

        // Set up the Rooks
        board[0][0] = new Square(0, 0, new Rook(false));
        board[0][7] = new Square(0, 7, new Rook(false));
        board[7][0] = new Square(7, 0, new Rook(true));
        board[7][7] = new Square(7, 7, new Rook(true));

        // Set up the Knights
        board[0][1] = new Square(0, 1, new Knight(false));
        board[0][6] = new Square(0, 6, new Knight(false));
        board[7][1] = new Square(7, 1, new Knight(true));
        board[7][6] = new Square(7, 6, new Knight(true));

        // Set up the Bishops
        board[0][2] = new Square(0, 2, new Bishop(false));
        board[0][5] = new Square(0, 5, new Bishop(false));
        board[7][2] = new Square(7, 2, new Bishop(true));
        board[7][5] = new Square(7, 5, new Bishop(true));

        // Set up the Queens
        board[0][3] = new Square(0, 3, new Queen(false));
        board[7][3] = new Square(7, 3, new Queen(true));

        // Set up the Kings
        board[0][4] = new Square(0, 3, new King(false));
        board[7][4] = new Square(7, 3, new King(true));
    }

}
