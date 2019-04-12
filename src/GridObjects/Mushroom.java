package GridObjects;

public class Mushroom extends GridObject  {

    public Mushroom () { super(GridObjectType.MUSHROOM); }

    public Mushroom deepCopy () { return new Mushroom(); }

    public String stateRep () { return "v"; }
}
