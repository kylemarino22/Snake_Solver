package GridObjects;

public class Apple extends GridObject {

    public Apple () {
        super(GridObjectType.APPLE);
    }

    public Apple deepCopy () { return new Apple(); }

    public String stateRep () { return "^"; }

}
