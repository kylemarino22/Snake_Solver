package GridObjects.Snake;

import GridObjects.GridObjectType;

public class Body extends Snake{

    private int segment;
    private int Snake_ID;
    private boolean isHead;

    //only used for head
    private int[] coords;

    public Body (GridObjectType type, int segment, int Snake_ID, boolean isHead) {
        super(type, null);
        this.segment = segment;
        this.Snake_ID = Snake_ID;
        this.isHead = isHead;
    }

    public void setCoords(int row, int col) {
        this.coords = new int[]{row, col};
    }

    public boolean isHead() {
        return isHead;
    }

    public int getSegment() {
        return segment;
    }

    public int getSnake_ID() {
        return Snake_ID;
    }

    public int[] getCoords() {
        return coords;
    }
}
