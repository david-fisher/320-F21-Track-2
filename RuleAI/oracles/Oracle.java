package oracles;
/* The interface for the AI and its decision making.
   We shall have the following implementations:
       - RandomOracle       -   Random (for any games)
       - DepthOracle        -   IDDFS (for games without a clear scoring mechanism)
       - HeuristicOracle    -   Greedy (for games with a clear scoring mechanism)
       - PruningOracle      -   Alpha-Beta Pruning (for zero-sum games with a scoring mechanism)
*/

public interface Oracle  {  
    public void processMove(Action a);
    public Action decideMove();
}