package sufixTree;

import java.util.HashMap;
import java.util.Map;

public class Node
{
    private char c;
    Map<Character, Node> children = new HashMap<Character, Node>();
    Node backPointer;

    public Node() {

    }

    public Node( char c ) {
        this.c = c;
    }

    public char getC() {
        return c;
    }

    public void setC( char c ) {
        this.c = c;
    }

    public Map<Character, Node> getChildren() {
        return children;
    }

    public void setChildren( Map<Character, Node> children ) {
        this.children = children;
    }

    public Node getBackPointer() {
        return backPointer;
    }

    public void setBackPointer( Node backPointer ) {
        this.backPointer = backPointer;
    }

    public void addChild( Node node ) {
        this.children.put( node.getC(), node );
    }
}
