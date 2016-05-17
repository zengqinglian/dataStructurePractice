package avltree;

@SuppressWarnings("unused")
public class Solution2
{
    // immutable treeNode
    public static class TreeNode
    {
        final int value;
        final TreeNode leftNode;
        final TreeNode rightNode;

        public TreeNode( int value, TreeNode leftNode, TreeNode rightNode ) {
            this.value = value;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }
    }

    public TreeNode insert( final int value, final TreeNode node ) {
        TreeNode newNode = null;
        if( value > node.value ) {
            if( node.rightNode != null ) {
                newNode = new TreeNode( node.value, node.leftNode, insert( value, node.rightNode ) );
            } else {
                newNode = new TreeNode( node.value, node.leftNode, new TreeNode( value, null, null ) );
            }
        } else if( value < node.value ) {
            if( node.leftNode != null ) {
                newNode = new TreeNode( node.value, insert( value, node.leftNode ), node.rightNode );
            } else {
                newNode = new TreeNode( node.value, new TreeNode( value, null, null ), node.rightNode );
            }
        }

        return newNode;
    }

    public boolean contains( int value, final TreeNode node ) {
        if( node == null ) {
            return false;
        }

        if( value == node.value ) {
            return true;
        } else if( value > node.value ) {
            return contains( value, node.rightNode );
        } else {
            return contains( value, node.leftNode );
        }
    }

    public TreeNode delete( final int value, TreeNode node ) {

        if( node.value == value ) { // current node is to be deleted //5!=10, 10 == 10, 15!=20, 15 ==15
            if( node.leftNode == null ) {
                return node.rightNode; // return 16
            }
            if( node.rightNode == null ) {
                return node.leftNode;
            }

            // find smallest value
            Integer smallestNode = getSmallestNode( node.rightNode ); // find smallest on 20 -> 15
            TreeNode newRight = delete( smallestNode, node.rightNode ); // delete 15 from 20
            // right child replace itself
            return new TreeNode( smallestNode, node.leftNode, newRight );

        } else if( value > node.value ) { // 5>10, 15<20
            return new TreeNode( node.value, node.leftNode, delete( value, node.rightNode ) );
        } else {
            return new TreeNode( node.value, delete( value, node.leftNode ), node.rightNode );
        }

    }

    private Integer getSmallestNode( TreeNode node ) {
        if( node.leftNode == null ) {
            return node.value;
        } else {
            return getSmallestNode( node.leftNode );
        }
    }

    // private TreeNode deleteLeaf( final int value, TreeNode node ) {
    //
    // }

    public static void main( String[] args ) {
        Solution2 sol2 = new Solution2();
        TreeNode root = new TreeNode( 5, null, null );
        TreeNode newRoot = sol2.insert( 10, root );
        sol2.insert( 20, newRoot );

    }
}
