package GridObjects;

public class Exit extends GridObject {

    public Exit () {
        super(GridObjectType.EXIT);
    }

    public Exit deepCopy () { return new Exit(); }

    public String stateRep () { return null; }

}
