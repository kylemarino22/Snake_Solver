package GridObjects;

public class Key extends GridObject{

    public Key () { super(GridObjectType.KEY); }

    public GridObjects.Key deepCopy () { return new GridObjects.Key(); }

    public String stateRep () { return "$"; }
}
