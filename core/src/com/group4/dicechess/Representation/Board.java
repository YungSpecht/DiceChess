package com.group4.dicechess.Representation;

import java.util.ArrayList;

import com.group4.dicechess.Pieces.Bishop;
import com.group4.dicechess.Pieces.King;
import com.group4.dicechess.Pieces.Knight;
import com.group4.dicechess.Pieces.Pawn;
import com.group4.dicechess.Pieces.Queen;
import com.group4.dicechess.Pieces.Rook;

import static com.group4.dicechess.GameState.kingCaptured;

public class Board {
    private final Square[][] board;
    private final ArrayList<Piece> whitePieces;
    private final ArrayList<Piece> blackPieces;
    private final ArrayList<Piece> whiteCaptured;
    private final ArrayList<Piece> blackCaptured;
    private Piece lastMovedPieceBlack;
    private Piece lastMovedPieceWhite;
    public int promotionKey;

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

    public void setSquare(Square sq, int row, int column){
        board[row][column] = sq;
    }

    public ArrayList<Piece> getWhiteCaptured(){
        return whiteCaptured;
    }

    public void setWhiteCaptured(ArrayList<Piece> whiteCaputured){
        this.whiteCaptured = whiteCaputured;
    }

    public ArrayList<Piece> getBlackCaptured(){
        return blackCaptured;
    }

    
    public void setBlackCaptured(ArrayList<Piece> blackCaputured){
        this.blackCaptured = blackCaputured;
    }

    public ArrayList<Piece> getWhitePieces(){
        return (ArrayList<Piece>) whitePieces.clone();
    }
    
    public void setWhitePieces(ArrayList<Piece> whitePieces){
        this.whitePieces = whitePieces;
    }

    public ArrayList<Piece> getBlackPieces(){
        return (ArrayList<Piece>) blackPieces.clone();
    }

    public ArrayList<Piece> getAllPieces(){
        ArrayList<Piece> out = (ArrayList<Piece>) whitePieces.clone();
        out.addAll((Collection<? extends Piece>) blackPieces.clone());
        return out;
    }
    
    public void setBlackPieces(ArrayList<Piece> blackPieces){
        this.blackPieces = blackPieces;
    }
    
    public Piece getLastMovedPieceWhite(){
        return lastMovedPieceWhite;
    }

    public void setLastMovedPieceWhite(Piece lastMovedPieceWhite){
        this.lastMovedPieceWhite = lastMovedPieceWhite;
    }

    public Piece getLastMovePieceBlack() {
        return lastMovedPieceBlack;
    }

    public void setLastMovedPieceBlack(Piece lastMovedPieceBlack){
        this.lastMovedPieceBlack = lastMovedPieceBlack;
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

    public int[][] getBoardMatrix() {
        int[][] out = new int[8][8];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getPiece() != null)
                    out[i][j] = board[i][j].getPiece().getDiceChessId();
            }
        }
        return out;
    }

    public Square[][] getMBoard(){
        return board;
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

    public int movePiece(Move move, int diceRoll, boolean botMove){
        Square start = move.getStart();
        Square destination = move.getDestination();
        int captureValue = 0;
        Piece piece = move.getStart().getPiece();
        if(piece.getWhiteStatus()){
            lastMovedPieceWhite = piece;
        }
        else{
            lastMovedPieceBlack = piece;
        }

        start.setPiece(null);
        destination.setPiece(move.getPiece());
        move.getPiece().setRow(destination.getRow());
        move.getPiece().setCol(destination.getCol());
        piece.increaseMoveCounter();

        if(move.getCastling()){
            int rookCol = 0, newRookCol = 0;
            switch(destination.getCol()){
                case 2 : rookCol = 0; newRookCol = 3; break;
                case 6 : rookCol = 7; newRookCol = 5; break;
            }
            Piece rook = this.board[piece.getRow()][rookCol].getPiece();
            this.board[piece.getRow()][newRookCol].setPiece(rook);
            this.board[piece.getRow()][rookCol].setPiece(null);
            rook.increaseMoveCounter();
            rook.setCol(newRookCol);
        }
        else if(move.getEnPassant()){
            board[start.getRow()][destination.getCol()].setPiece(null);
        }
        else if(move.getPromotion()){
            if(diceRoll != 1){
                move.setPromotedPiece(pieceFactory(diceRoll, move.getPiece().getWhiteStatus(), destination));
            }
            else{
                if(!botMove){
                    move.setPromotedPiece(pieceFactory(promotionKey, move.getPiece().getWhiteStatus(), destination));
                }
                else{
                    move.setPromotedPiece(pieceFactory(5, move.getPiece().getWhiteStatus(), destination));
                }
            }
            destination.setPiece(move.getPromotedPiece());
            if(move.getPiece().getWhiteStatus()){
                whitePieces.remove(move.getPiece());
                whitePieces.add(move.getPromotedPiece());
            }
            else{
                blackPieces.remove(move.getPiece());
                blackPieces.add(move.getPromotedPiece());
            }
        }


        if(move.getCapturedPiece() != null){
            captureValue = move.getCaptureValue();
            if(move.getCapturedPiece().getWhiteStatus()){
                whitePieces.remove(move.getCapturedPiece());
                whiteCaptured.add(move.getCapturedPiece());
            }
            else{
                blackPieces.remove(move.getCapturedPiece());
                blackCaptured.add(move.getCapturedPiece());

            }
        }
        return captureValue;
    }

    public void reverseMove(Move move, Piece previouslyMoved){
        if(move.getPiece().getWhiteStatus()){
            lastMovedPieceWhite = previouslyMoved;
        }
        else{
            lastMovedPieceBlack = previouslyMoved;
        }

        move.getPiece().setMoveCounter(move.getPiece().getMoveCounter()-1);
        move.getPiece().setRow(move.getStart().getRow());
        move.getPiece().setCol(move.getStart().getCol());
        move.getDestination().setPiece(null);
        move.getStart().setPiece(move.getPiece());

        if(move.getCapturedPiece() != null){
            if(move.getCapturedPiece().getWhiteStatus()){
                whitePieces.add(move.getCapturedPiece());
                whiteCaptured.remove(move.getCapturedPiece());
            }
            else{
                blackPieces.add(move.getCapturedPiece());
                blackCaptured.remove(move.getCapturedPiece());
            }
            board[move.getCapturedPiece().getRow()][move.getCapturedPiece().getCol()].setPiece(move.getCapturedPiece());
        }

        if(move.getCastling()){
            int rookCol = 0, newRookCol = 0;
            switch(move.getDestination().getCol()){
                case 2 : rookCol = 3; newRookCol = 0; break;
                case 6 : rookCol = 5; newRookCol = 7; break;
            }
            Piece rook = board[move.getPiece().getRow()][rookCol].getPiece();
            board[move.getPiece().getRow()][newRookCol].setPiece(rook);
            board[move.getPiece().getRow()][rookCol].setPiece(null);
            rook.setMoveCounter(rook.getMoveCounter()-1);
            rook.setCol(newRookCol);
        }
        else if(move.getPromotion()){
            if(move.getPiece().getWhiteStatus()){
                whitePieces.add(move.getPiece());
                whitePieces.remove(move.getPromotedPiece());
            }
            else{
                blackPieces.add(move.getPiece());
                blackPieces.remove(move.getPromotedPiece());
            }
        }
    }

    private Piece pieceFactory(int id, boolean white, Square sq){
        switch(id){
            case 2 : return new Knight(white, sq.getRow(), sq.getCol());
            case 3 : return new Bishop(white, sq.getRow(), sq.getCol());
            case 4 : return new Rook(white, sq.getRow(), sq.getCol());
            case 5 : return new Queen(white, sq.getRow(), sq.getCol());
            default : return null;
        }
    }

    public int count(String id, boolean white){
        int result = 0;
        ArrayList<Piece> list = white ? whitePieces : blackPieces;
        for(Piece p : list){
            if(p.getId().equals(id)){
                result++;
            }
        }
        return result;
    }

    public Board copy(){
        Board copy = new Board();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                copy.setSquare(this.getSquare(i, j).copy(), i, j);
            }
        }
        ArrayList<Piece> wpc = new ArrayList<Piece>();
        for(Piece p : whitePieces){
            wpc.add(p.copy());
        }
        copy.setWhitePieces(wpc);
        ArrayList<Piece> bpc = new ArrayList<Piece>();
        for(Piece p : blackPieces){
            bpc.add(p.copy());
        }
        copy.setBlackPieces(bpc);
        ArrayList<Piece> wc = new ArrayList<Piece>();
        for(Piece p : whiteCaptured){
            wc.add(p.copy());
        }
        copy.setWhiteCaptured(wc);
        ArrayList<Piece> bc = new ArrayList<Piece>();
        for(Piece p : blackCaptured){
            bc.add(p.copy());
        }
        copy.setBlackCaptured(bc);
        if(lastMovedPieceWhite == null){
            copy.setLastMovedPieceWhite(null);
        }
        else{
            copy.setLastMovedPieceWhite(lastMovedPieceWhite.copy());
        }
        if(lastMovedPieceBlack == null){
            copy.setLastMovedPieceBlack(null);
        }
        else{
            copy.setLastMovedPieceBlack(lastMovedPieceBlack.copy());
        }
        return copy;
    }

}
