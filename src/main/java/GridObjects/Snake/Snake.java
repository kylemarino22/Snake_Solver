package GridObjects.Snake;

import GridObjects.GridObject;
import GridObjects.GridObjectType;

public abstract class Snake extends GridObject {

    private int length;

    public Body[] bodyArray;

    public Snake (GridObjectType type, Body[] bodyArray) {
        super(type);
        this.bodyArray = bodyArray;
    }

    public int getLength () {
        return length;
    }

    public void setLength (int length) {
        this.length = length;
    }

    public Body[] getBodyArray () {
        return bodyArray;
    }

    public abstract Snake deepCopy ();
}
