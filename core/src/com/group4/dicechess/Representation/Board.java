package com.group4.dicechess.Representation;

import java.util.ArrayList;
import java.util.Scanner;

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
    private Piece lastMovedPieceBlack;
    private Piece lastMovedPieceWhite;

    public static void main(String[] args) {
        Board test = new Board();
        test.printBoard();

        ArrayList<Square> result = test.getSquare(0, 4).getPiece().getPossibleMoves(test, test.getSquare(0, 4));
        System.out.println(result.size());
    }

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
    
    public Piece getLastMovedPieceWhite(){
        return lastMovedPieceWhite;
    }

    public Piece getLastMovePieceBlack() {
        return lastMovedPieceBlack;
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
        for(int i = 0; i < 8; i++){
            board[1][i] = new Square(1, i, new Pawn(false, 1, i));
            board[6][i] = new Square(6, i, new Pawn(true, 6, i));
        }
       
        // Set up empty squares
        for(int i = 2; i < 6; i++) {
            for(int j = 0; j < 8; j++) {
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
        board[0][4] = new Square(0, 4, new King(false, 0, 4));
        board[7][4] = new Square(7, 4, new King(true, 7, 4));

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
    public int movePiece(Square start, Square destination, int diceRoll){
        int captureValue =0;
        Piece piece = start.getPiece();
        if(piece.getWhiteStatus()){
            lastMovedPieceWhite = piece;
        } else {
            lastMovedPieceBlack = piece;
        }
        if(enPassant(start, destination)){
            captureValue = 1;
            if(piece.getWhiteStatus()){
                blackPieces.remove(board[start.getRow()][destination.getCol()].getPiece());
                blackCaptured.add(board[start.getRow()][destination.getCol()].getPiece());
            }
            else{
                whitePieces.remove(board[start.getRow()][destination.getCol()].getPiece());
                whiteCaptured.add(board[start.getRow()][destination.getCol()].getPiece());
            }
            this.board[start.getRow()][destination.getCol()].setPiece(null);
        }
        else if(castling(start, destination)){
            int white = start.getPiece().getWhiteStatus() ? 1 : 0;
            if(destination.getCol() < start.getCol()){
                Piece rook = this.board[0 + (white * 7)][0].getPiece();
                this.board[0 + (white * 7)][3].setPiece(rook);
                this.board[0 + (white * 7)][0].setPiece(null);
                rook.increaseMoveCounter();
                rook.setCol(3);
            }
            else{
                Piece rook = this.board[0 + (white * 7)][7].getPiece();
                this.board[0 + (white * 7)][5].setPiece(rook);
                this.board[0 + (white * 7)][7].setPiece(null);
                rook.increaseMoveCounter();
                rook.setCol(5);
            }
        }
        else if(pawnPromotion(start, destination)){
            if(destination.getPiece() != null){
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
            if(diceRoll != 1){
                piece = pieceFactory(diceRoll, start.getPiece().getWhiteStatus(), destination);
                if(piece.getWhiteStatus()){
                    whitePieces.add(piece);
                }
                else{
                    blackPieces.add(piece);
                }
            }
            else{
                int choice = 0;
                Scanner in = new Scanner(System.in);
                do{
                    System.out.println("Please enter the Piece you want to promote to: ");
                    System.out.println("1 - Pawn");
                    System.out.println("2 - Knight");
                    System.out.println("3 - Bishop");
                    System.out.println("4 - Rook");
                    System.out.println("5 - Queen");
                    choice = in.nextInt();
                }while(choice < 1 || choice > 5);
                in.close();
                piece = pieceFactory(choice, start.getPiece().getWhiteStatus(), destination);
                if(piece.getWhiteStatus()){
                    whitePieces.add(piece);
                }
                else{
                    blackPieces.add(piece);
                }
            }
        }
        else if(destination.getPiece() == null){
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
        start.setPiece(null);
        destination.setPiece(piece);
        piece.setRow(destination.getRow());
        piece.setCol(destination.getCol());
        piece.increaseMoveCounter();
        this.printBoard();
        return captureValue;
    }

    private boolean enPassant(Square start, Square destination){
        Piece piece = start.getPiece();
        if(piece.getWhiteStatus() && piece.getId().equals("P") && start.getRow() == 3 && board[destination.getRow()][destination.getCol()].getPiece() == null){
            return true;
        }
        else if(!piece.getWhiteStatus() && piece.getId().equals("P") && start.getRow() == 4 && board[destination.getRow()][destination.getCol()].getPiece() == null){
            return true;
        }
        return false;
    }

    private boolean castling(Square start, Square destination){
        Piece piece = start.getPiece();
        if(piece.getId().equals("K") && (destination.getCol() != start.getCol()+1 || destination.getCol() != start.getCol()-1)){
            return true;
        }
        return false;
    }

    private boolean pawnPromotion(Square start, Square destination){
        Piece piece = start.getPiece();
        if(piece.getId().equals("P") && (destination.getRow() == 0 || destination.getRow() == 7)){
            return true;
        }
        return false;
    }

    private Piece pieceFactory(int id, boolean white, Square sq){
        switch(id){
            case 1 : return new Pawn(white, sq.getRow(), sq.getCol());
            case 2 : return new Knight(white, sq.getRow(), sq.getCol());
            case 3 : return new Bishop(white, sq.getRow(), sq.getCol());
            case 4 : return new Rook(white, sq.getRow(), sq.getCol());
            case 5 : return new Queen(white, sq.getRow(), sq.getCol());
            default : return null;
        }
    }

}
