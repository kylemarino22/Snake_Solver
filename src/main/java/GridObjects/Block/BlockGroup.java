package GridObjects.Block;

public class BlockGroup extends Block {

    public Block[] blockArray;
    public int blockCount = 0;

    public BlockGroup(int Block_ID, Block[] blockArray){
        super(Block_ID);
        this.blockArray = blockArray;
    }

    public BlockGroup deepCopy () {

        Block[] copyBlockArray = new Block[blockArray.length];

        for (int i = 0; i < blockArray.length; i++) {
            if(blockArray[i] == null) {
                copyBlockArray[i] = null;
                continue;
            }
            copyBlockArray[i] = blockArray[i].deepCopy();
        }

        BlockGroup b = new BlockGroup(copyBlockArray[0].getBlock_ID(), copyBlockArray);
        b.blockCount = this.blockCount;
        return b;
    }

}
