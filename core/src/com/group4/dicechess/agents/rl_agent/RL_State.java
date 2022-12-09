package com.group4.dicechess.agents.rl_agent;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Board;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.Representation.Piece;
import com.group4.dicechess.agents.rl_agent.utils.Input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static com.group4.dicechess.agents.rl_agent.network.NetworkParameters.inputChannels;
import static com.group4.dicechess.agents.rl_agent.utils.Utils.isZeroMatrix;

public class RL_State {

    private final GameState gameState;
    private final Random random = new Random();

    public RL_State(GameState gameState){
        this.gameState = gameState;
    }

    public Input getInput(boolean white, int pieceID) throws Exception {
        HashMap<Piece, ArrayList<Move>> pieceMoveMap = new HashMap<>();
        ArrayList<Piece> pieces = gameState.getBoard().getAllPieces();
        Board board = gameState.getBoard();

        for (Piece piece : pieces)
            pieceMoveMap.put(piece, piece.getPossibleMoves(board));

        return getInput(white, pieceID, pieceMoveMap, pieces);
    }

    public Input getInput(boolean white) throws Exception {
        HashMap<Piece, ArrayList<Move>> pieceMoveMap = new HashMap<>();
        ArrayList<Piece> pieces = gameState.getBoard().getAllPieces();
        Board board = gameState.getBoard();

        for (Piece piece : pieces)
            pieceMoveMap.put(piece, piece.getPossibleMoves(board));

        int pieceID = rollDice(white, pieceMoveMap, pieces);

        return getInput(white, pieceID, pieceMoveMap, pieces);
    }

    public Input getInput() throws Exception {
        HashMap<Piece, ArrayList<Move>> pieceMoveMap = new HashMap<>();
        ArrayList<Piece> pieces = gameState.getBoard().getAllPieces();
        Board board = gameState.getBoard();

        for (Piece piece : pieces)
            pieceMoveMap.put(piece, piece.getPossibleMoves(board));

        int pieceID = gameState.diceRoll();

        return getInput(false, pieceID, pieceMoveMap, pieces);
    }


    private Input getInput(boolean white, int pieceID, HashMap<Piece, ArrayList<Move>> pieceMoveMap, ArrayList<Piece> pieces) throws Exception {

        double[][][] state = new double[inputChannels.valueInt][8][8];
        int channel;

        for (Piece piece : pieces) {
            channel = piece.getDiceChessId() - 1;
            channel = piece.getWhiteStatus() ? channel : channel + 6;
            state[channel][piece.getRow()][piece.getCol()] = 1;
        }

        ArrayList<Piece> legalPieces = getLegalPieces(white, pieceID, pieceMoveMap),
                enemyPieces = getEnemyPieces(white);

        state[12] = getLegalPiecePositions(legalPieces, pieceMoveMap);
        state[13] = getLegalMovePositions(legalPieces, pieceMoveMap);
        state[14] = getLegalMovePositions(enemyPieces, pieceMoveMap);

        if (isZeroMatrix(state[14]))
            throw new Exception("No legal Pieces");

        return new Input(state, pieceID, legalPieces, pieceMoveMap);
    }

    private double[][] getLegalPiecePositions(ArrayList<Piece> pieces, HashMap<Piece, ArrayList<Move>> pieceMoveMap) {

        double[][] out = new double[8][8];

        for (Piece piece : pieces)
            out[piece.getRow()][piece.getCol()] = 1;

        return out;
    }

    private ArrayList<Piece> getLegalPieces(boolean white, int pieceID, HashMap<Piece, ArrayList<Move>> pieceMoveMap) throws Exception {
        ArrayList<Piece> teamPieces = white ? gameState.getBoard().getWhitePieces() : gameState.getBoard().getBlackPieces(),
                legalPieces = new ArrayList<>();

        for (Piece piece : teamPieces) {
            if (piece.getDiceChessId() == pieceID && !pieceMoveMap.get(piece).isEmpty())
                legalPieces.add(piece);
        }

/*        if (legalPieces.isEmpty())
            throw new Exception("No Legal Pieces");*/

        return legalPieces;
    }

    private ArrayList<Piece> getEnemyPieces(boolean white){
        return white ? gameState.getBoard().getBlackPieces() : gameState.getBoard().getWhitePieces();
    }

    private double[][] getLegalMovePositions(ArrayList<Piece> pieces, HashMap<Piece, ArrayList<Move>> pieceMoveMap) {
        ArrayList<Move> moves;
        double[][] out = new double[8][8];

        for (Piece piece : pieces) {
            moves = pieceMoveMap.get(piece);
            for (Move move : moves)
                out[move.getDestination().getRow()][move.getDestination().getCol()] = 1;
        }

        return out;
    }

    private int rollDice(boolean white,HashMap<Piece, ArrayList<Move>> pieceMoveMap, ArrayList<Piece> pieces){

        boolean selected = false;
        Piece piece = null;

        while (!selected) {
            piece = pieces.get(random.nextInt(pieces.size()));
            if (piece.getWhiteStatus() != white) continue;
            if (pieceMoveMap.get(piece).isEmpty()) continue;
            selected = true;
        }

        return piece.getDiceChessId();
    }

    public int getDiceRoll(){
        return gameState.getDiceRoll();
    }
}
