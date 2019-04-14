package GridObjects.Block;


import GridObjects.GridObject;
import GridObjects.GridObjectType;

public class Block extends GridObject{

    private int[] coords;

    private int Block_ID;


    public Block (int Block_ID) {
        super(GridObjectType.BLOCK);
        this.Block_ID = Block_ID;
    }

    public void setCoords(int row, int col) {
        this.coords = new int[]{row, col};
    }

    public int[] getCoords() {
        return coords;
    }

    public int getBlock_ID() {
        return Block_ID;
    }


    public Block deepCopy () {
        Block b = new Block(this.Block_ID);
        b.setCoords(this.coords[0], this.coords[1]);

        return b;
    }

    public String stateRep () { return "B" + Block_ID;}
}
