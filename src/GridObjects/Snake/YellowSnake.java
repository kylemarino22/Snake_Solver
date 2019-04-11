package GridObjects.Snake;

import GridObjects.GridObjectType;

public class YellowSnake extends Snake {

    public YellowSnake (Body[] bodyArray) {
        super(GridObjectType.YELLOW_SNAKE, bodyArray);
    }

    public YellowSnake deepCopy () {

        Body[] copyBodyArray = new Body[bodyArray.length];

        for (int i = 0; i < bodyArray.length; i++) {
            if(bodyArray[i] == null) {
                copyBodyArray[i] = null;
                continue;
            }
            copyBodyArray[i] = bodyArray[i].deepCopy();
        }

        YellowSnake ys = new YellowSnake(copyBodyArray);
        ys.setLength(this.getLength());
        return ys;

    }

    public String stateRep () { return "Y"; }

}
