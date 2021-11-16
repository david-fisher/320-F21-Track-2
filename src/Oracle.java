// The interface for the AI and its decision making.

public interface Oracle  {
    public void processMove(Action a);
    public Action decideMove();
}