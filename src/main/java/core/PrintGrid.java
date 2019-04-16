package core;

import GridObjects.Block.Block;
import GridObjects.GridObject;
import GridObjects.Lock;
import GridObjects.Snake.Body;

import MoveTree.*;
import java.util.ArrayList;

public abstract class PrintGrid {

    private static final boolean DEBUG = false;

    public static void printGrid(GridObject[][] Grid, boolean solution) {
        if (solution || DEBUG) { displayGrid(Grid); }
    }

    public static void printGrid(GridObject[][] Grid) {
        if(DEBUG) { displayGrid(Grid);}
    }


    private static void displayGrid(GridObject[][] Grid) {

        for (int i = 0; i <( Grid[0].length - 1) *  7 + 1; i++){
            System.out.format("=");
        }

        System.out.format("\n");


        for (int i = 0; i < Grid.length; i++) {

            for (int j = 0; j < Grid[0].length; j++) {

                Body obj;

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

                    case APPLE:
                        System.out.format("%-7s","^");
                        break;

                    case MUSHROOM:
                        System.out.format("%-7s","v");
                        break;

                    case KEY:
                        System.out.format("%-7s","$");
                        break;

                    case LOCK:
                        StringBuilder sb = new StringBuilder();
                        sb.append("&");

                        if(((Lock) Grid[i][j]).key) { sb.append(".$"); }
                        if(((Lock) Grid[i][j]).apple) { sb.append(".^"); }
                        if(((Lock) Grid[i][j]).mushroom) { sb.append(".v"); }

                        System.out.format("%-7s", sb.toString());
                        break;


                    case BLOCK:
                        System.out.format("%-7s","[" + ((Block) Grid[i][j]).getBlock_ID() + "]");
                        break;

                    case GREEN_SNAKE:

                        obj = (Body) Grid[i][j];

                        if (obj.isHead()) {
                            System.out.format("%-7s","#." + obj.getSegment());
                        }
                        else {
                            System.out.format("%-7s","+." + obj.getSegment());
                        }

                        break;

                    case YELLOW_SNAKE:

                        obj = (Body) Grid[i][j];

                        if (obj.isHead()) {
                            System.out.format("%-7s","@." + obj.getSegment() + "." + obj.getSnake_ID());
                        }
                        else {
                            System.out.format("%-7s","*." + obj.getSegment() + "." + obj.getSnake_ID());
                        }

                        break;
                }
            }

            System.out.print("\n");
        }

        for (int i = 0; i <( Grid[0].length - 1) *  7 + 1; i++){
            System.out.format("=");
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

    public static void printSideBySide(GridObject[][] left, GridObject[][] right, int layer, boolean solution) {
        if (solution || DEBUG) { displaySideBySide(left, right, layer); }
    }

    public static void printSideBySide(GridObject[][] left, GridObject[][] right, int layer) {
        if(DEBUG) { displaySideBySide(left, right, layer);}
    }


    private static void displaySideBySide (GridObject[][] left, GridObject[][] right, int layer) {

        for (int i = 0; i < left.length; i++) {

            GridObject[][] temp = left;

            for (int l = 0; l < layer; l++) {
               System.out.format("   ");

            }
            System.out.format(" | ");

            for (int k = 0; k < 2; k++) {
                for (int j = 0; j < temp[0].length; j++) {

                    Body obj;

                    switch (temp[i][j].getType()) {

                        case WALL:
                            System.out.format("%-7s","X");
                            break;

                        case EMPTY:
                            System.out.format("%-7s","");

                            break;

                        case EXIT:
                            System.out.format("%-7s","-");
                            break;

                        case APPLE:
                            System.out.format("%-7s","^");
                            break;

                        case MUSHROOM:
                            System.out.format("%-7s","v");
                            break;

                        case KEY:
                            System.out.format("%-7s","$");
                            break;

                        case LOCK:
                            StringBuilder sb = new StringBuilder();
                            sb.append("&");

                            if(((Lock) temp[i][j]).key) { sb.append(".$"); }
                            if(((Lock) temp[i][j]).apple) { sb.append(".^"); }
                            if(((Lock) temp[i][j]).mushroom) { sb.append(".v"); }

                            System.out.format("%-7s", sb.toString());
                            break;

                        case BLOCK:
                            System.out.format("%-7s","[" + ((Block) temp[i][j]).getBlock_ID() + "]");
                            break;

                        case GREEN_SNAKE:

                            obj = (Body) temp[i][j];

                            if (obj.isHead()) {
                                System.out.format("%-7s","#." + obj.getSegment());
                            }
                            else {
                                System.out.format("%-7s","+." + obj.getSegment());
                            }

                            break;

                        case YELLOW_SNAKE:

                            obj = (Body) temp[i][j];

                            if (obj.isHead()) {
                                System.out.format("%-7s","@." + obj.getSegment() + "." + obj.getSnake_ID());
                            }
                            else {
                                System.out.format("%-7s","*." + obj.getSegment() + "." + obj.getSnake_ID());
                            }

                            break;
                    }
                }

                System.out.format("         ");
                temp = right;
            }


            System.out.print("\n");
        }

        for (int l = 0; l < layer; l++) {
            System.out.format("   ");

        }
        System.out.format(" | ");

        for (int i = 0; i < (left[0].length * 2) *  7 + 2; i++){
            System.out.format("=");
        }

        System.out.format("\n");

    }

    public static void indent(int layer, boolean solution) {
        if (solution || DEBUG) { indentHelper(layer); }
    }

    public static void indent(int layer) {
        if(DEBUG) { indentHelper(layer);}
    }

    private static void indentHelper (int layer) {
        for (int l = 0; l < layer; l++) {
            System.out.format("   ");

        }
        System.out.format(" | ");
    }

    public static void printIndented(int layer, String message, boolean solution) {
        if (solution || DEBUG) { printIndentedHelper(layer, message); }
    }

    public static void printIndented(int layer, String message) {
        if(DEBUG) { printIndentedHelper(layer, message);}
    }

    private static void printIndentedHelper (int layer, String message) {
        indentHelper(layer);
        System.out.format(message + "\n");
    }

    public static void printSolution (MoveTree mt, Grid mainGrid) {
        Node currentNode = mt.head;
        while (currentNode.children.size() > 0) {
            Move m = currentNode.children.get(0).data;
            mainGrid.moveSnake(m.getSnakeID(), m.getDirection(), m.isHead());
            printGrid(mainGrid.grid, true);
            currentNode = currentNode.children.get(0);
        }
    }

}
