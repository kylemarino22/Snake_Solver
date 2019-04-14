package GridObjects;

public class Wall extends GridObject{

    public Wall () {
        super(GridObjectType.WALL);
    }

    public Wall deepCopy () { return new Wall(); }

    public String stateRep () { return null; }

}
