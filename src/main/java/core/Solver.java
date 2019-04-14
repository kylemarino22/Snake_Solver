package core;

import GridObjects.Block.Block;
import GridObjects.Block.BlockGroup;
import GridObjects.GridObject;
import GridObjects.GridObjectType;
import GridObjects.Snake.Body;
import GridObjects.Snake.Snake;

import MoveTree.*;

import java.util.ArrayList;

public class Solver {

//    private Grid Grid;

    //Stores hashes that describe the state of the game
    public ArrayList<Integer> moveList = new ArrayList<>();
    public MoveTree MoveTree = new MoveTree();

    private static final int MAX_LAYER_COUNT = 35;

    private int moveCounter = 0;
    private long startTime, estimatedTime;

    public Solver(Grid mainGrid) {

        mainGrid.lastMove = MoveTree.head;
        mainGrid.addBackMove(mainGrid.hashCode(-1));

        startTime = System.nanoTime();
        System.out.println("Solving...");

        try {
            move(mainGrid);
        }
        catch (Exception e) {
            System.out.println("End has been reahed");
        }
        MoveTree.clean();

        PrintGrid.printSolution(MoveTree, mainGrid.deepCopy());

        estimatedTime = System.nanoTime();

        System.out.println(TimeConverter.convertTimeToString(estimatedTime - startTime));

        System.out.println(moveCounter);
        System.out.println("done");
    }


    private void move (Grid mainGrid) throws Exception{

        if (mainGrid.lastMove.data.getMoveCount() > Grid.TOTAL_MOVES) {
//            System.out.print("Path took more than n moves. Most likely an infinite loop");
            return;
        }

        if (mainGrid.layer > MAX_LAYER_COUNT) {
            return;
        }

        moveCounter++;

        if (moveCounter % 10000 == 0) {
            System.out.print(" .");
        }
        ArrayList<Body> movePoints = new ArrayList<>();

        for (Snake s: mainGrid.snakeMap.values()) {
            movePoints.add(s.bodyArray[0]);
            movePoints.add(s.bodyArray[s.getLength()]);
        }
        PrintGrid.printIndented(mainGrid.layer, "Pause");
        for (Body movePoint: movePoints) {

//            if (movePoint.getSnake_ID() == 2 ){
//                System.out.println("pause");
//            }
            ArrayList<Integer> moves;
            try {
                 moves = getValidMoves(mainGrid, movePoint);
            }
            catch (Exception e) {
                throw new Exception("End has been reached");
            }
            for(Integer moveDirection : moves) {

                Grid newGrid = mainGrid.deepCopy();
                int currentMoveCount = newGrid.lastMove.data.getMoveCount();
                Move newMove = new Move(movePoint.getSnake_ID(), moveDirection,
                        movePoint.isHead(), currentMoveCount);


                newGrid.moveSnake(movePoint.getSnake_ID(), moveDirection, movePoint.isHead());

                if (newMove.getSnakeID() == newGrid.lastMove.data.getSnakeID()
                        && newMove.isHead() == newGrid.lastMove.data.isHead()) {
                    //no new move
                    //loop has ocurred
                    if (!verifyState(newGrid.hashCode(currentMoveCount, newMove.getSnakeID()))) {
                        PrintGrid.printIndented(mainGrid.layer,"Repeated State");
                        //-857074242
                        continue;
                    }
                }
                else {
                    //new move

                    //snake tried to move back (check if it is similar to 2 moves ago)
                    int stateHash = newGrid.hashCode(mainGrid.layer - 2);
//                    int abc = newGrid.hashCode(Grid.layer - 2);
//                    int abc1 = newGrid.hashCode(Grid.layer - 3);
//                    int abc2 = newGrid.hashCode(Grid.layer - 4);


                    //check if backmove
                    if (newGrid.backMoveList.size() > 0 && newGrid.backMoveList.get(0) == stateHash) { continue; }

                    PrintGrid.printIndented(mainGrid.layer,"stateHash: " + stateHash + " backMove: " + newGrid.backMoveList.get(0));
                    PrintGrid.printIndented(mainGrid.layer,"New Move");

                    //add to total move list if valid
                    moveList.add(newGrid.hashCode(currentMoveCount));


                    currentMoveCount++;
                }

//                moveCounter++;
                newGrid.addBackMove(newGrid.hashCode(mainGrid.layer));

                newMove.setMoveCount(currentMoveCount);

                newGrid.lastMove = MoveTree.insert(newMove, newGrid.lastMove);


                if (movePoint.isHead()) {
//                    System.out.println("Head Move");
                }
                else {
//                    System.out.println("Tail Move");
                }
//                System.out.println(currentMoveCount);
                PrintGrid.printIndented(mainGrid.layer,"Movecount: " + currentMoveCount);
                PrintGrid.printIndented(mainGrid.layer,"Total moveCount: " + mainGrid.layer);
                PrintGrid.printIndented(mainGrid.layer,"         " + newGrid.backMoveList.get(0) +
                        "                                 " + newGrid.backMoveList.get(1));

//                if (newGrid.backMoveList.get(0) == -998436057 && newGrid.backMoveList.get(1) == -33681082) {
//                    System.out.println("Stop");
//                }
                PrintGrid.printSideBySide(mainGrid.grid, newGrid.grid, newGrid.layer);



                newGrid.layer++;
                move(newGrid);
            }



//            PrintGrid.PrintArray(moves);
        }
        //finished all moves
    }

    private ArrayList<Integer> getValidMoves (Grid g, Body b) throws Exception {
        int[] coords = b.getCoords();

        //down => 0, right => 1, up => 2, left => 3
        ArrayList<Integer> vaildMoves = new ArrayList<>();

        try {
            //down
            if (validSquare(g, b, coords[0] + 1, coords[1], 0)) {
                vaildMoves.add(0);
            }
            //right
            if (validSquare(g, b, coords[0], coords[1] + 1, 1)) {
                vaildMoves.add(1);
            }
            //up
            if (validSquare(g, b, coords[0] - 1, coords[1], 2)) {
                vaildMoves.add(2);
            }
            //left
            if (validSquare(g, b, coords[0], coords[1] - 1, 3)) {
                vaildMoves.add(3);
            }
        }
        catch (Exception e) {
            PrintGrid.printIndented(g.layer, e.getMessage());
            throw new Exception("End has been reached");
        }

        return vaildMoves;

    }

    private boolean validSquare (Grid g, Body movePoint, int row, int col, int direction) throws Exception{

        GridObject current_obj = g.grid[row][col];

        if (current_obj.getType() == GridObjectType.EXIT) {
//            System.out.println("End has been reached");
            if (movePoint.getSnake_ID() == 0) {
                g.lastMove.data.setEnd(true);
                throw new Exception("End has been reached");
            }
//            System.exit(0);
        }

        switch (current_obj.getType()) {

            case EMPTY: return true;
            case WALL: return false;
            case APPLE:
                if (movePoint.isHead()) { return true; }
                return false;
            case MUSHROOM:
                if (movePoint.isHead()) { return true; }
                return false;

            case BLOCK:
                return validBlock(g, ((Block) current_obj).getBlock_ID(), direction);

            case EXIT: return false;
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

    private boolean validBlock (Grid g, int block_id, int direction) {
        BlockGroup bg = g.blockMap.get(block_id);

       for (int i = 0; i < bg.blockCount+1; i++) {
            int[] blockCoords = bg.blockArray[i].getCoords();
            int[] newCoords = new int[2];

            if (direction == 0) {
                newCoords[0] = blockCoords[0] + 1;
                newCoords[1] = blockCoords[1];
            } else if (direction == 1) {
                newCoords[0] = blockCoords[0];
                newCoords[1] = blockCoords[1] + 1;
            } else if (direction == 2) {
                newCoords[0] = blockCoords[0] - 1;
                newCoords[1] = blockCoords[1];
            } else if (direction == 3) {
                newCoords[0] = blockCoords[0];
                newCoords[1] = blockCoords[1] - 1;
            }

            if (g.grid[newCoords[0]][newCoords[1]].getType() != GridObjectType.EMPTY) {

                if (g.grid[newCoords[0]][newCoords[1]].getType() != GridObjectType.BLOCK) {
                    return false;
                }
                else {
                    if (((Block) g.grid[newCoords[0]][newCoords[1]]).getBlock_ID() != block_id) {
                        return false;
                    }
                }
            }
        }
        return true;
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
