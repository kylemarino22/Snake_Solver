package GridObjects;

public class Key extends GridObject{

    public Key () { super(GridObjectType.KEY); }

    public Key deepCopy () { return new Key(); }

    public String stateRep () { return "$"; }
}
