package avltree;

public class Solution
{

    public TreeNode insert( TreeNode node, TreeNode root ) {
        if( root.left == null && root.value > node.value ) {
            root.left = node;
            node.parent = root;
            root.metaData.leftDepth = 1;
        } else if( root.right == null && root.value < node.value ) {
            root.right = node;
            node.parent = root;
            root.metaData.rightDepth = 1;
        } else if( root.left != null && root.value > node.value ) {
            insert( node, root.left );

            root.metaData.leftDepth = root.left.metaData.max() + 1;
        } else if( root.right != null && root.value < node.value ) {
            insert( node, root.right );
            root.metaData.rightDepth = root.right.metaData.max() + 1;
        } else {
            return root;
        }

        return root.balaceYourself();
    }

    public void delete( TreeNode node ) {

    }

    private static class TreeNode
    {
        int value;
        TreeNode left;
        TreeNode right;
        TreeNode parent;
        MetaData metaData = new MetaData();

        TreeNode( int val ) {
            value = val;
        }

        public TreeNode balaceYourself() {
            TreeNode root = this;
            if( metaData.balance() == 2 ) {
                root = doRightRotation();
            } else if( metaData.balance() == -2 ) {
                root = doLeftRotation();
            }

            if( metaData.balance() == 2 ) {
                root.left = root.left.doLeftRotation();
                return root.doRightRotation();
            } else if( metaData.balance() == -2 ) {
                // doLeftRotation();
            }

            throw new RuntimeException();
        }

        private TreeNode doLeftRotation() {
            // TODO Auto-generated method stub
            return null;
        }

        public TreeNode doRightRotation() {
            TreeNode a = this;
            TreeNode b = left;
            TreeNode c = b.right;

            a.left = c;
            b.right = a;

            c = b.right;

            return b;

        }
    }

    private static class MetaData
    {
        int leftDepth;
        int rightDepth;

        public int max() {
            return Math.max( leftDepth, rightDepth );
        }

        public int balance() {
            return leftDepth - rightDepth;
        }

    }

    public static void main( String[] args ) {
        Solution s = new Solution();
        TreeNode root = new TreeNode( 5 );
        root.metaData = new MetaData();
        s.insert( new TreeNode( 3 ), root );
        s.insert( new TreeNode( 4 ), root );
        s.insert( new TreeNode( 6 ), root );
        s.insert( new TreeNode( 8 ), root );
        System.out.print( "finished" );

    }
}
