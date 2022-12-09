package com.group4.dicechess.agents.rl_agent.utils;

import com.group4.dicechess.Representation.Piece;
import com.group4.dicechess.Representation.Square;

public record RLMove(Square start, Square destination, Piece piece) {
}
