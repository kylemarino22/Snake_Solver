package core;

import GridObjects.Empty;
import GridObjects.GridObject;
import GridObjects.Snake.Snake;

import java.util.HashMap;

public class Grid {
    public GridObject[][] grid;
    public HashMap<Integer, Snake> snakeMap = new HashMap<>();

    public Grid () {}

    public void consolidate() {
        //Snake consolidation
        for(int i = 0; i < snakeMap.size(); i++) {
            Snake temp = snakeMap.get(i);
            int length = getMaxArrayIndex(temp.bodyArray);
            temp.setLength(length);
            temp.bodyArray[length].setTail(true);
        }
    }

    private int getMaxArrayIndex (Object[] arr) {

        if (arr[0] == null) { return -1; }

        for(int i = 1; i < arr.length; i++ ) {
            if (arr[i] == null) {
                return i-1;
            }
        }

        return arr.length-1;
    }

    public void moveSnake (int Snake_ID, int direction, boolean isHead) {

        Snake s = snakeMap.get(Snake_ID);

        if (isHead) {

            int[] prevCoords = {0,0};

            if (direction == 0) {
                //store old coords
                prevCoords = s.bodyArray[0].getCoords();

                //update body's self location
                s.bodyArray[0].setCoords(prevCoords[0] + 1, prevCoords[1]);

                //move gridobject to new location
                grid[prevCoords[0] + 1][prevCoords[1]] = grid[prevCoords[0]][prevCoords[1]];
            }
            else if (direction == 1) {
                //store old coords
                prevCoords = s.bodyArray[0].getCoords();

                //update body's self location
                s.bodyArray[0].setCoords(prevCoords[0], prevCoords[1] + 1);

                //move gridobject to new location
                grid[prevCoords[0]][prevCoords[1] + 1] = grid[prevCoords[0]][prevCoords[1]];
            }
            else if (direction == 2) {
                //store old coords
                prevCoords = s.bodyArray[0].getCoords();

                //update body's self location
                s.bodyArray[0].setCoords(prevCoords[0] - 1, prevCoords[1]);

                //move gridobject to new location
                grid[prevCoords[0] - 1][prevCoords[1]] = grid[prevCoords[0]][prevCoords[1]];
            }
            else if (direction == 3) {
                //store old coords
                prevCoords = s.bodyArray[0].getCoords();

                //update body's self location
                s.bodyArray[0].setCoords(prevCoords[0], prevCoords[1] - 1);

                //move gridobject to new location
                grid[prevCoords[0]][prevCoords[1] - 1] = grid[prevCoords[0]][prevCoords[1]];
            }

            for (int i = 1; i < s.getLength()+1; i++) {
                int[] newCoords = {prevCoords[0], prevCoords[1]};
                prevCoords = s.bodyArray[i].getCoords();

                //update body's self location
                s.bodyArray[i].setCoords(newCoords[0], newCoords[1]);

                //move gridobject to new location
                grid[newCoords[0]][newCoords[1]] = grid[prevCoords[0]][prevCoords[1]];

            }

            grid[prevCoords[0]][prevCoords[1]] = new Empty();

        }
        else {
            //if tail moves; do nothing for now
        }
    }


//    public void deepCopy() {
//
//    }
}
