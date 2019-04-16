package GridObjects;

public class Lock extends GridObject{

    public boolean key;
    public boolean apple;
    public boolean mushroom;

    public Lock (boolean key, boolean apple, boolean mushroom) {
        super(GridObjectType.LOCK);
        this.key = key;
        this.apple = apple;
        this.mushroom = mushroom;
    }

    public Lock deepCopy () { return new Lock(key, apple, mushroom); }

    public String stateRep () { return null; }

    public boolean validate (GridObject[][] grid) {

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[0].length; j++) {

                switch (grid[i][j].getType()) {

                    case APPLE:
                        if (this.apple) { return false; }
                        break;

                    case MUSHROOM:
                        if (this.mushroom) { return false; }
                        break;

                    case KEY:
                        if (this.key) { return false; }
                        break;

                }

            }

        }
        return true;
    }

}
