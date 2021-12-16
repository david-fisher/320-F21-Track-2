package org.RuleEngine.oracles;
/* The interface for the AI and its decision making.
   We shall have the following implementations:
       - RandomOracle       -   Random (for any games)
       - DepthOracle        -   IDDFS (for games without a clear scoring mechanism)
       - HeuristicOracle    -   Greedy (for games with a clear scoring mechanism)
*/

import org.RuleEngine.engine.GameState;

import java.util.ArrayList;

public interface Oracle  {
    public void processMove(String event, GameState currState);
    public String decideMove(GameState currState, ArrayList<String> prev);
}