package core;

import GridObjects.GridObject;
import GridObjects.GridObjectType;
import GridObjects.Snake.Body;
import GridObjects.Snake.GreenSnake;

import java.util.ArrayList;

public abstract class PrintGrid {

    public static void PrintGrid(GridObject[][] Grid) {


        for (int i = 0; i < Grid.length; i++) {

            for (int j = 0; j < Grid[0].length; j++) {

                switch (Grid[i][j].getType()) {

                    case WALL:
                        System.out.format("%-7s","X");
                        break;

                    case EMPTY:
                        System.out.format("%-7s","");

                        break;

                    case EXIT:
                        System.out.format("%-7s","-");
                        break;

                    case GREEN_SNAKE:

                        Body obj = (Body) Grid[i][j];

                        if (obj.isHead()) {
                            System.out.format("%-7s","#." + obj.getSegment());
                        }
                        else {
                            System.out.format("%-7s","+." + obj.getSegment());
                        }

                        break;
                }
            }

            System.out.print("\n");
        }
        System.out.println("\n");
    }

    public static void PrintArray(ArrayList<Integer> arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Integer i : arr) {
            sb.append(" " + i.toString());
        }
        sb.append("]");

        System.out.println(sb.toString());
    }
}
