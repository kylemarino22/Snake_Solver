package GridObjects;

public class Empty extends GridObject{

    public Empty () {
        super(GridObjectType.EMPTY);
    }

    public Empty deepCopy () { return new Empty(); }

    public String stateRep () { return "E"; }

}
