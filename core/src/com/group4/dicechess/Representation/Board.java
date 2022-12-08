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
        return whitePieces;
    }
    
    public void setWhitePieces(ArrayList<Piece> whitePieces){
        this.whitePieces = whitePieces;
    }

    public ArrayList<Piece> getBlackPieces(){
        return blackPieces;
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
            System.out.println("------PROMOTION------");
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

    /**
     * Moves a piece from one square to another and updates all the relevant variables accordingly.
     * 
     * @param start the square on which the piece is currently on
     * @param destination the square to which the piece is to be moved to
     * @param //legalMoves ArrayList containing all the squares the piece can be moved to
     * @return integer that represents the value of the captured piece
     */
    public int moveePiece(Move move, int diceRoll, boolean botMove){
        Square start = move.getStart();
        Square destination = move.getDestination();
        int captureValue = 0;
        move.setCaptureValue(0);
        Piece piece = move.getStart().getPiece();
        if(piece.getWhiteStatus()){
            lastMovedPieceWhite = piece;
        } else {
            lastMovedPieceBlack = piece;
        }
        if(enPassant(start, destination)){
            move.setEnPassant(true);
            captureValue = 1;
            move.setCaptureValue(captureValue);
            if(piece.getWhiteStatus()){
                blackPieces.remove(board[start.getRow()][destination.getCol()].getPiece());
                blackCaptured.add(board[start.getRow()][destination.getCol()].getPiece());
            }
            else{
                whitePieces.remove(board[start.getRow()][destination.getCol()].getPiece());
                whiteCaptured.add(board[start.getRow()][destination.getCol()].getPiece());
            }
            move.setCapturedPiece(board[start.getRow()][destination.getCol()].getPiece());
            board[start.getRow()][destination.getCol()].setPiece(null);
        }
        else if(castling(start, destination)){
            move.setCastling(true);
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
        else if(pawnPromotion(start, destination)){
            move.setPromotion(true);
            if(destination.getPiece() != null){
                Piece capturedPiece = destination.getPiece();
                move.setCapturedPiece(capturedPiece);
                captureValue = capturedPiece.getValue();
                move.setCaptureValue(captureValue);
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
                Piece newPiece = pieceFactory(diceRoll, start.getPiece().getWhiteStatus(), destination);
                move.setPromotedPiece(newPiece);
                if(piece.getWhiteStatus()){
                    whitePieces.add(newPiece);
                    whitePieces.remove(start.getPiece());
                }
                else{
                    blackPieces.add(newPiece);
                    blackPieces.remove(start.getPiece());
                }
                piece = newPiece;
            }
            else{
                Piece newPiece;
                if(!botMove){
                    int choice = 0;
                    do{
                        choice = promotionKey;
                    }while(choice < 2 || choice > 5);
                    newPiece = pieceFactory(choice, start.getPiece().getWhiteStatus(), destination);
                }
                else{
                    newPiece = pieceFactory(5, start.getPiece().getWhiteStatus(), destination);
                }
                move.setPromotedPiece(newPiece);
                if(piece.getWhiteStatus()){
                    whitePieces.add(newPiece);
                    whitePieces.remove(start.getPiece());
                }
                else{
                    blackPieces.add(newPiece);
                    blackPieces.remove(start.getPiece());
                }
                piece = newPiece;
            }
        }
        else if(destination.getPiece() == null){
            captureValue = 0;
            move.setCaptureValue(captureValue);
        }
        else{
            Piece capturedPiece = destination.getPiece();
            move.setCapturedPiece(capturedPiece);
            captureValue = capturedPiece.getValue();
            move.setCaptureValue(captureValue);
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
        return captureValue;
    }

    public void reverseeMove(Move m, Piece previousMovedPiece){
        if(m.getCastling()){
            int currentRookCol = 0, newRookCol = 0;
            switch(m.getPiece().getCol()){
                case 2 : currentRookCol = 3; newRookCol = 0; break;
                case 6 : currentRookCol = 5; newRookCol = 7; break;
            }
            board[m.getPiece().getRow()][newRookCol].setPiece(board[m.getPiece().getRow()][currentRookCol].getPiece());
            board[m.getPiece().getRow()][currentRookCol].setPiece(null);
            board[m.getPiece().getRow()][newRookCol].getPiece().setCol(newRookCol);

            m.getStart().setPiece(m.getPiece());
            m.getPiece().setRow(m.getStart().getRow());
            m.getPiece().setCol(m.getStart().getCol());
            m.getPiece().setMoveCounter(0);
            m.getDestination().setPiece(null);
            board[m.getPiece().getRow()][newRookCol].getPiece().setMoveCounter(0);
        }
        else if(m.getPromotion()){
            m.getStart().setPiece(m.getPiece());
            m.getPiece().setRow(m.getStart().getRow());
            m.getPiece().setCol(m.getStart().getCol());
            m.getPiece().setMoveCounter(m.getPiece().getMoveCounter()-1);
            if(m.getCapturedPiece() != null){
                m.getDestination().setPiece(m.getCapturedPiece());
                if(m.getCapturedPiece().getWhiteStatus()){
                    whitePieces.add(m.getCapturedPiece());
                    whiteCaptured.remove(m.getCapturedPiece());
                }
                else{
                    blackPieces.add(m.getCapturedPiece());
                    blackCaptured.remove(m.getCapturedPiece());
                }
            }
            else{
                m.getDestination().setPiece(null);
            }
            if(m.getPromotedPiece().getWhiteStatus()){
                whitePieces.remove(m.getPromotedPiece());
                whitePieces.add(m.getPiece());
            }
            else{
                blackPieces.remove(m.getPromotedPiece());
                blackPieces.add(m.getPiece());
            }
        }
        else if(m.getEnPassant()){
            board[m.getStart().getRow()][m.getDestination().getCol()].setPiece(m.getCapturedPiece());
            if(m.getCapturedPiece().getWhiteStatus()){
                whitePieces.add(m.getCapturedPiece());
                whiteCaptured.remove(m.getCapturedPiece());
            }
            else{
                blackPieces.add(m.getCapturedPiece());
                blackCaptured.remove(m.getCapturedPiece());
            }
            m.getStart().setPiece(m.getPiece());
            m.getPiece().setRow(m.getStart().getRow());
            m.getPiece().setCol(m.getStart().getCol());
            m.getPiece().setMoveCounter(m.getPiece().getMoveCounter()-1);
            m.getDestination().setPiece(null);
        }
        else{
            m.getStart().setPiece(m.getPiece());
            m.getPiece().setRow(m.getStart().getRow());
            m.getPiece().setCol(m.getStart().getCol());
            m.getPiece().setMoveCounter(m.getPiece().getMoveCounter()-1);
            if(m.getCapturedPiece() != null){
                m.getDestination().setPiece(m.getCapturedPiece());
                if(m.getCapturedPiece().getWhiteStatus()){
                    whitePieces.add(m.getCapturedPiece());
                    whiteCaptured.remove(m.getCapturedPiece());
                }
                else{
                    blackPieces.add(m.getCapturedPiece());
                    blackCaptured.remove(m.getCapturedPiece());
                }
            }
            else{
                m.getDestination().setPiece(null);
            }
        }


        if(m.getPiece().getWhiteStatus()){
            lastMovedPieceWhite = previousMovedPiece;
        }
        else{
            lastMovedPieceBlack = previousMovedPiece;
        }
    }

    private boolean enPassant(Square start, Square destination){
        Piece piece = start.getPiece();
        if(!piece.getId().equals("P")){
            return false;
        }
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

        if(piece.getId().equals("K") && piece.getMoveCounter() == 0 && (destination.getCol() == start.getCol()-2 || destination.getCol() == start.getCol()+2)){
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
