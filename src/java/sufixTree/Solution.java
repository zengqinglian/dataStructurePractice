package sufixTree;

public class Solution
{
    public Node generateTree( String text ) {
        Node Root = new Node();
        int length = text.length();

        Node longest = Root;
        for( int i = 0; i < length; i++ ) {
            char c = text.charAt( i );
            Node newNode = new Node( c );
            if( longest == Root ) {
                longest.addChild( newNode );
                newNode.backPointer = Root;
            } else {
                longest.addChild( newNode );
                Node loop = longest;
                while( loop.getBackPointer() != null ) {
                    Node backPointer = loop.getBackPointer();
                    if( !backPointer.getChildren().containsKey( newNode.getC() ) ) {
                        backPointer.addChild( newNode );
                        newNode.setBackPointer( backPointer );
                    } else {
                        newNode.setBackPointer( backPointer.getChildren().get( newNode.getC() ) );
                    }
                    loop = backPointer;
                }
            }
            longest = newNode;
        }
        return Root;
    }
}
