package com.group4.dicechess.agents.rl_agent;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Board;
import com.group4.dicechess.agents.rl_agent.network.Network;

public class RL_Agent {

    private Network pieceNetwork;
    private Network[] moveNetwork;

    public RL_Agent(){
        pieceNetwork = new Network();
        moveNetwork = new Network[6];

        for (Network network : moveNetwork) {
            network = new Network();
        }
    }
}
