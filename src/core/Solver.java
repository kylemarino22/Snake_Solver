package core;

import GridObjects.GridObject;
import GridObjects.GridObjectType;
import GridObjects.Snake.Body;
import GridObjects.Snake.Snake;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Solver {

//    private Grid Grid;

    //Stores hashes that describe the state of the game
    public ArrayList<Integer> moveList = new ArrayList<>();

    public Solver(Grid Grid) {

        move(Grid);
    }


    private void move(Grid Grid) {


        ArrayList<Body> movePoints = new ArrayList<>();

        for (Snake s: Grid.snakeMap.values()) {
            movePoints.add(s.bodyArray[0]);
            movePoints.add(s.bodyArray[s.getLength()]);
        }

        for(Body movePoint: movePoints) {
            ArrayList<Integer> moves = getValidMoves(Grid, movePoint);
            for(Integer moveDirection : moves) {
                Grid newGrid = Grid.deepCopy();

                newGrid.moveSnake(movePoint.getSnake_ID(), moveDirection, movePoint.isHead());

                //state has already occurred
                if (!verifyState(newGrid.hashCode())) { continue; }

                if (movePoint.isHead()) {
                    System.out.println("Head Move");
                }
                else {
                    System.out.println("Tail Move");
                }
                PrintGrid.PrintGrid(newGrid.grid);

                move(newGrid);
            }



//            PrintGrid.PrintArray(moves);
        }
        //finished all moves
    }

    private ArrayList<Integer> getValidMoves (Grid g, Body b) {
        int[] coords = b.getCoords();

        //down => 0, right => 1, up => 2, left => 3
        ArrayList<Integer> vaildMoves = new ArrayList<>();

        try {
            //down
            if (validSquare(g, b, coords[0] + 1, coords[1])) {
                vaildMoves.add(0);
            }
            //right
            if (validSquare(g, b, coords[0], coords[1] + 1)) {
                vaildMoves.add(1);
            }
            //up
            if (validSquare(g, b, coords[0] - 1, coords[1])) {
                vaildMoves.add(2);
            }
            //left
            if (validSquare(g, b, coords[0], coords[1] - 1)) {
                vaildMoves.add(3);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }

        return vaildMoves;

    }

    private boolean validSquare (Grid g, Body movePoint, int row, int col) throws Exception{

        GridObject current_obj = g.grid[row][col];

        if (current_obj.getType() == GridObjectType.EXIT) {
//            System.out.println("End has been reached");
            throw new Exception("End has been reached");
//            System.exit(0);
        }

        switch (current_obj.getType()) {

            case EMPTY: return true;
            case EXIT: return true;
        }

        if(current_obj.getType() == GridObjectType.GREEN_SNAKE
                || current_obj.getType() == GridObjectType.YELLOW_SNAKE){

            //check if they are the same id

            if(movePoint.getSnake_ID() == ((Body)current_obj).getSnake_ID()) {

                //check if a head -> tail or tail -> head
                if(movePoint.isHead() && ((Body)current_obj).isTail()){
                    return true;
                }
                else if(movePoint.isTail() && ((Body)current_obj).isHead()){
                    return true;
                }
            }


        }
        return false;
    }

    private boolean verifyState (int hash) {
        for (Integer state: moveList) {

            //state has already occurred
            if (state == hash) { return false; }
        }

        //state is new
        moveList.add(hash);
        return true;
    }

}
