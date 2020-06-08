package MaxFrolov_RPIS82;

public class Node {
    public Service service;
    public Node next;
    public Node previous;


    public Node() {
    }

    public Node(Node previous)
    {
        this.previous=previous;
    }

}
