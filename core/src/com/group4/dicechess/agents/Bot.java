package com.group4.dicechess.agents;

import com.group4.dicechess.Representation.Move;

public interface Bot {
    public Move getMove();
    public int getRoll();
}
