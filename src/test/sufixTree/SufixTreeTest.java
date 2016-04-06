package sufixTree;

import org.junit.Test;

import sufixTree.Node;
import sufixTree.Solution;

public class SufixTreeTest
{
    @Test
    public void testBuild() {
        String test = "ABBACABBA";
        Solution s = new Solution();
        Node root = s.generateTree( test );

        printNode( root.getChildren().get( 'C' ).getChildren().get( 'A' ).getChildren().get( 'B' ).getChildren().get( 'B' )
                .getChildren().get( 'A' ) );

    }

    private void printNode( Node node ) {
        for( Node no : node.getChildren().values() ) {
            System.out.println( "node is " + no.getC() );
            System.out.println( no.getChildren() );
        }
    }
}
