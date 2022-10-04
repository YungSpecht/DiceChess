package com.group4.dicechess.Representation;

import java.util.ArrayList;

import com.group4.dicechess.Pieces.Bishop;
import com.group4.dicechess.Pieces.King;
import com.group4.dicechess.Pieces.Knight;
import com.group4.dicechess.Pieces.Pawn;
import com.group4.dicechess.Pieces.Queen;
import com.group4.dicechess.Pieces.Rook;

public class Board {
    private Square[][] board;
    private ArrayList<Piece> whitePieces;
    private ArrayList<Piece> blackPieces;
    private ArrayList<Piece> whiteCaptured;
    private ArrayList<Piece> blackCaptured;

    public Board(){
        board = new Square[8][8];
        whitePieces = new ArrayList<Piece>();
        blackPieces = new ArrayList<Piece>();
        whiteCaptured = new ArrayList<Piece>();
        blackCaptured = new ArrayList<Piece>();
        setUpBoard();
    }

    public Square getSquare(int row, int column){
        return board[row][column];
    }

    public ArrayList<Piece> getWhiteCaptured(){
        return whiteCaptured;
    }

    public ArrayList<Piece> getBlackCaptured(){
        return blackCaptured;
    }

    public ArrayList<Piece> getWhitePieces(){
        return whitePieces;
    }

    public ArrayList<Piece> getBlackPieces(){
        return blackPieces;
    }

    /**
     * Prints the current state of the chessboard to the terminal. Pieces are printed by a prefix
     * indicating their colour and their identifier Letter. "O" for empty squares.
     */
    public void printBoard(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                    if(board[i][j].getPiece() == null){
                        System.out.print(" O ");
                    }
                    else if(board[i][j].getPiece().getWhiteStatus()){
                        System.out.print("w"+board[i][j].getPiece().getId() + " ");
                    }
                    else{
                        System.out.print("b"+board[i][j].getPiece().getId() + " ");
                    }
            }
            System.out.println();
        }
        System.out.println("------------------------");
    }

    /**
     * Sets up the chess board in its initial starting position.
     */
    private void setUpBoard(){
        // Set up the pawns
        for(int i = 0; i < board.length; i++){
            board[1][i] = new Square(1, i, new Pawn(false, 1, i));
            board[6][i] = new Square(6, i, new Pawn(true, 6, i));
        }

        // Set up empty squares
        for(int i = 2; i < 6; i++) {
            for(int j = 0; j < board[i].length; j++) {
                board[i][j] = new Square(i, j, null);
            }

        }

        // Set up the Rooks
        board[0][0] = new Square(0, 0, new Rook(false, 0, 0));
        board[0][7] = new Square(0, 7, new Rook(false, 0, 7));
        board[7][0] = new Square(7, 0, new Rook(true, 7, 0));
        board[7][7] = new Square(7, 7, new Rook(true, 7, 7));

        // Set up the Knights
        board[0][1] = new Square(0, 1, new Knight(false, 0, 1));
        board[0][6] = new Square(0, 6, new Knight(false, 0, 6));
        board[7][1] = new Square(7, 1, new Knight(true, 7, 1));
        board[7][6] = new Square(7, 6, new Knight(true, 7, 6));

        // Set up the Bishops
        board[0][2] = new Square(0, 2, new Bishop(false, 0, 2));
        board[0][5] = new Square(0, 5, new Bishop(false, 0, 5));
        board[7][2] = new Square(7, 2, new Bishop(true, 7, 2));
        board[7][5] = new Square(7, 5, new Bishop(true, 7, 5));

        // Set up the Queens
        board[0][3] = new Square(0, 3, new Queen(false, 0, 3));
        board[7][3] = new Square(7, 3, new Queen(true, 7, 3));

        // Set up the Kings
        board[0][4] = new Square(0, 3, new King(false, 0, 4));
        board[7][4] = new Square(7, 3, new King(true, 7, 4));

        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 8; j++){
                blackPieces.add(board[i][j].getPiece());
            }
        }

        for(int i = 6; i < 8; i++){
            for(int j = 0; j < 8; j++){
                whitePieces.add(board[i][j].getPiece());
            }
        }
    }

    /**
     * Moves a piece from one square to another and updates all the relevant variables accordingly.
     * 
     * @param start the square on which the piece is currently on
     * @param destination the square to which the piece is to be moved to
     * @param legalMoves ArrayList containing all the squares the piece can be moved to
     * @return integer that represents the value of the captured piece
     */
    public int movePiece(Square start, Square destination, ArrayList<Square> legalMoves){
        int captureValue = 0;
        Piece piece = start.getPiece();
        if(legalMoves.contains(destination)){
            start.setPiece(null);
            if(destination.getPiece() == null){
                captureValue = 0;
            }
            else{
                Piece capturedPiece = destination.getPiece();
                captureValue = capturedPiece.getValue();
                if(destination.getPiece().getWhiteStatus()){
                    whitePieces.remove(capturedPiece);
                    whiteCaptured.add(capturedPiece);
                }
                else{
                    blackPieces.remove(capturedPiece);
                    blackCaptured.add(capturedPiece);
                }
            }
            destination.setPiece(piece);
            piece.setRow(destination.getRow());
            piece.setCol(destination.getCol());
            piece.hasMoved();
        }
        return captureValue;
    }

}
