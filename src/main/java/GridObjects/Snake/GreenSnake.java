package GridObjects.Snake;

import GridObjects.GridObjectType;

public class GreenSnake extends Snake{


    public GreenSnake (Body[] bodyArray) {
        super(GridObjectType.GREEN_SNAKE, bodyArray);
    }

    public GreenSnake deepCopy () {

        Body[] copyBodyArray = new Body[bodyArray.length];

        for (int i = 0; i < bodyArray.length; i++) {
            if(bodyArray[i] == null) {
                copyBodyArray[i] = null;
                continue;
            }
            copyBodyArray[i] = bodyArray[i].deepCopy();
        }

        GreenSnake gs = new GreenSnake(copyBodyArray);
        gs.setLength(this.getLength());
        return gs;
    }

    public String stateRep () { return "G"; }

}
