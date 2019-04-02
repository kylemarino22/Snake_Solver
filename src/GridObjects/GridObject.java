package GridObjects;

public class GridObject {
    private GridObjectType type;

    public GridObject () {}

    public GridObject (GridObjectType type) {
        this.type = type;
    }

    public GridObjectType getType() {
        return type;
    }

    public void setType(GridObjectType type) {
        this.type = type;
    }
}
