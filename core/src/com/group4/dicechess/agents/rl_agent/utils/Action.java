package com.group4.dicechess.agents.rl_agent.utils;

import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.Representation.Piece;

public record Action(double actionValue, double pieceValue, double moveValue, Move move, Piece capture) {
}
