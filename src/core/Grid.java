package core;

import GridObjects.Empty;
import GridObjects.GridObject;
import GridObjects.GridObjectType;
import GridObjects.Snake.Body;
import GridObjects.Snake.Snake;
import GridObjects.Wall;

import java.util.ArrayList;
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

            Body head = s.bodyArray[0];

            if (direction == 0) {
                //store old coords
                prevCoords = head.getCoords();

                //update body's self location
                head.setCoords(prevCoords[0] + 1, prevCoords[1]);

                //move gridobject to new location
                grid[prevCoords[0] + 1][prevCoords[1]] = grid[prevCoords[0]][prevCoords[1]];
            }
            else if (direction == 1) {
                //store old coords
                prevCoords = head.getCoords();

                //update body's self location
                head.setCoords(prevCoords[0], prevCoords[1] + 1);

                //move gridobject to new location
                grid[prevCoords[0]][prevCoords[1] + 1] = grid[prevCoords[0]][prevCoords[1]];
            }
            else if (direction == 2) {
                //store old coords
                prevCoords = head.getCoords();

                //update body's self location
                head.setCoords(prevCoords[0] - 1, prevCoords[1]);

                //move gridobject to new location
                grid[prevCoords[0] - 1][prevCoords[1]] = grid[prevCoords[0]][prevCoords[1]];
            }
            else if (direction == 3) {
                //store old coords
                prevCoords = head.getCoords();

                //update body's self location
                head.setCoords(prevCoords[0], prevCoords[1] - 1);

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
            int[] prevCoords = {0,0};

            Body tail = s.bodyArray[s.getLength()];

            if (direction == 0) {
                //store old coords
                prevCoords = tail.getCoords();

                //update body's self location
                tail.setCoords(prevCoords[0] + 1, prevCoords[1]);

                //move gridobject to new location
                grid[prevCoords[0] + 1][prevCoords[1]] = grid[prevCoords[0]][prevCoords[1]];
            }
            else if (direction == 1) {
                //store old coords
                prevCoords = tail.getCoords();

                //update body's self location
                tail.setCoords(prevCoords[0], prevCoords[1] + 1);

                //move gridobject to new location
                grid[prevCoords[0]][prevCoords[1] + 1] = grid[prevCoords[0]][prevCoords[1]];
            }
            else if (direction == 2) {
                //store old coords
                prevCoords = tail.getCoords();

                //update body's self location
                tail.setCoords(prevCoords[0] - 1, prevCoords[1]);

                //move gridobject to new location
                grid[prevCoords[0] - 1][prevCoords[1]] = grid[prevCoords[0]][prevCoords[1]];
            }
            else if (direction == 3) {
                //store old coords
                prevCoords = tail.getCoords();

                //update body's self location
                tail.setCoords(prevCoords[0], prevCoords[1] - 1);

                //move gridobject to new location
                grid[prevCoords[0]][prevCoords[1] - 1] = grid[prevCoords[0]][prevCoords[1]];
            }

            for (int i = s.getLength()-1; i >=0; i--) {
                int[] newCoords = {prevCoords[0], prevCoords[1]};
                prevCoords = s.bodyArray[i].getCoords();

                //update body's self location
                s.bodyArray[i].setCoords(newCoords[0], newCoords[1]);

                //move gridobject to new location
                grid[newCoords[0]][newCoords[1]] = grid[prevCoords[0]][prevCoords[1]];

            }

            grid[prevCoords[0]][prevCoords[1]] = new Empty();
        }
    }


    public Grid deepCopy() {
        Grid g = new Grid();

        g.grid = new GridObject[this.grid.length][this.grid[0].length];

        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[0].length; j++) {
                g.grid[i][j] = this.grid[i][j].deepCopy();
            }
        }

        for (int i = 0; i < this.snakeMap.size(); i++) {
            g.snakeMap.put(i, this.snakeMap.get(i).deepCopy());
        }

        return g;
    }

    public int hashCode() {

        StringBuilder gameState = new StringBuilder();
        for (GridObject[] row: this.grid) {
            for (GridObject obj: row){

                //These remain constant so no need to hash them
                if (obj.getType() == GridObjectType.WALL
                        || obj.getType() == GridObjectType.EXIT) {
                    continue;
                }

                gameState.append(obj.stateRep());

            }
        }

        return gameState.toString().hashCode();
    }

}
