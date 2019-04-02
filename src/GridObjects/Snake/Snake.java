package GridObjects.Snake;

import GridObjects.GridObject;
import GridObjects.GridObjectType;

public class Snake extends GridObject {

    private int length;

    public Body[] bodyArray;

    public Snake (GridObjectType type, Body[] bodyArray) {
        super(type);
        this.bodyArray = bodyArray;
    }

    public int getLength() {
        return length;
    }

    public Body[] getBodyArray() {
        return bodyArray;
    }
}
