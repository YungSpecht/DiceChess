package com.group4.dicechess.agents.rl_agent.utils;

import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.Representation.Piece;

import java.util.ArrayList;
import java.util.HashMap;

public record Input(double[][][] state, int pieceId, ArrayList<Piece> legalPieces, HashMap<Piece, ArrayList<Move>> pieceMoveMap) {
}
