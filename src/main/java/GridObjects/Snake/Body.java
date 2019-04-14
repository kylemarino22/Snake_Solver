package GridObjects.Snake;

import GridObjects.GridObjectType;

public class Body extends Snake{

    private int segment;
    private int Snake_ID;
    private boolean isHead = false;
    private boolean isTail = false;

    private int[] coords;

    public Body (GridObjectType type, int segment, int Snake_ID) {
        super(type, null);
        this.segment = segment;
        this.Snake_ID = Snake_ID;
    }

    public void setCoords(int row, int col) {
        this.coords = new int[]{row, col};
    }

    public void setHead(boolean head) {
        isHead = head;
    }

    public void setTail(boolean tail) {
        isTail = tail;
    }

    public boolean isHead() {
        return isHead;
    }

    public boolean isTail() { return isTail; }

    public int getSegment() {
        return segment;
    }

    public int getSnake_ID() {
        return Snake_ID;
    }

    public int[] getCoords() {
        return coords;
    }

    public Body deepCopy () {
        Body b = new Body(this.getType(), this.segment, this.Snake_ID);
        b.setCoords(this.coords[0], this.coords[1]);
        b.setHead(this.isHead);
        b.setTail(this.isTail);

        return b;
    }

    public String stateRep () { return Snake_ID + (isTail() ? "" : "T" ); }

}
