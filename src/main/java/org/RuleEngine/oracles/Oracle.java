package org.RuleEngine.oracles;
/* The interface for the AI and its decision making.
   We shall have the following implementations:
       - RandomOracle       -   Random (for any games)
       - DepthOracle        -   IDDFS (for games without a clear scoring mechanism)
       - HeuristicOracle    -   Greedy (for games with a clear scoring mechanism)
*/

import org.RuleEngine.engine.GameState;

public interface Oracle  {
    public GameState processMove(String a);
    public String decideMove();
}