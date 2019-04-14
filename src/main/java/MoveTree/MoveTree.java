package MoveTree;

public class MoveTree {

    public Node head;
    private int minMoves = 100;

    public MoveTree () {
        head = new Node( new Move(-1, -1, false, 0));
    }

    public Node insert (Move data, Node root) {
        Node n = new Node(data);
        root.children.add(n);
        return n;
    }

//    public void remove (Node root) {
//        root = null;
//    }

    public void clean () {
        markEnds(head);
        pruneEnds(head);
        resetEnds(head);
        findMinMoves(head);
        pruneMinMoves(head);
        markEnds(head);
        pruneEnds(head);
    }

    //recurses through tree to flag all end paths
    private boolean markEnds (Node root) {

        for (Node n: root.children) {
            if(n.data.isEnd()) {
                root.data.setEnd(true);
                return true;
            }
            boolean result = markEnds(n);
            if (result && root.data != null) {
                root.data.setEnd(true);
            }

        }
        return root.data.isEnd();
    }

    private void pruneEnds (Node root) {

        for (int i = root.children.size() - 1; i > -1; i--) {
            if (!root.children.get(i).data.isEnd()) {
                root.children.remove(i);
                continue;
            }
            pruneEnds(root.children.get(i));
        }
    }

    private void findMinMoves (Node root) {

        for (Node n: root.children) {
            findMinMoves(n);

            //if node is the end check if it is the smallest solution
            if (n.children.size() == 0) {
                if (n.data.getMoveCount() < minMoves) {
                    minMoves = n.data.getMoveCount();
                }

            }

        }

    }

    //set paths that don't end in minMove count to false
    private void pruneMinMoves (Node root) {

        for (int i = root.children.size() - 1; i > -1; i--) {
            if (root.children.get(i).data.getMoveCount() > minMoves) {
                root.children.remove(i);
                continue;
            }
            pruneMinMoves(root.children.get(i));
        }

    }

    private void resetEnds (Node root) {

        for (Node n: root.children) {
            resetEnds(n);
        }

        if (root.children.size() == 0) {
            //switch end flag to indicate end
            root.data.setEnd(true);
        }
        else {
            root.data.setEnd(false);
        }
    }

}
