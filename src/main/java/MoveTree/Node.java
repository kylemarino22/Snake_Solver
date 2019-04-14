package MoveTree;

import java.util.ArrayList;

public class Node {

    public ArrayList<Node> children = new ArrayList<>();
    public Move data;

    public Node (Move data) {
        this.data = data;
    }

    public Node () {}

}
