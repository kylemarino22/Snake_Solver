package MoveTree;

public class Move {

    //data about move
    private int SnakeID;
    private int direction;
    private int moveCount;
    private boolean head;
    private boolean end = false;

    public Move(int SnakeID, int direction, boolean head, int moveCount) {
        this.SnakeID = SnakeID;
        this.direction = direction;
        this.moveCount = moveCount;
        this.head = head;
    }

    public Move () {
        this.moveCount = 0;
    }

    public int getSnakeID() {
        return SnakeID;
    }

    public int getDirection() {
        return direction;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    public boolean isHead() {
        return head;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public Move deepCopy() {
        Move m = new Move(this.SnakeID, this.direction,
                this.head, this.moveCount);
        m.setEnd(this.end);
        return m;
    }
}
