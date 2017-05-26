package convertTreeToDoubleLinkedList;

public class Solution {

    public LinkedNode convert(TreeNode root) {
        if (root == null) {
            return null;
        }
        LinkedNode[] result = linkNode(root);

        result[0].previous = result[1];
        result[1].next = result[0];

        return result[0];

    }

    public LinkedNode[] linkNode(TreeNode root) {
        LinkedNode[] result = new LinkedNode[2]; // keep head and tail

        LinkedNode currentNode = toLinkedNode(root);
        LinkedNode headNode = currentNode;
        LinkedNode tailNode = currentNode;
        if (root.left == null && root.right == null) {
            result[0] = currentNode;
            result[1] = currentNode;
            return result;
        } else {
            if (root.left != null) {
                LinkedNode[] nodes = linkNode(root.left);
                currentNode.previous = nodes[1];
                nodes[1].next = currentNode;
                headNode = nodes[0];
                tailNode = currentNode;
            }

            if (root.right != null) {
                LinkedNode[] nodes = linkNode(root.right);
                currentNode.next = nodes[0];
                nodes[0].previous = currentNode;
                tailNode = nodes[1];
            }
        }

        result[0] = headNode;
        result[1] = tailNode;

        return result;
    }

    private LinkedNode toLinkedNode(TreeNode node) {
        LinkedNode lNode = new LinkedNode();
        lNode.val = node.val;

        return lNode;
    }

    private static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }


        @Override
        public String toString() {
            return "TreeNode [val=" + val + ", left=" + left + ", right=" + right + "]";
        }

    }

    private static class LinkedNode {
        private int val;
        private LinkedNode next;
        private LinkedNode previous;

        @Override
        public String toString() {
            return "LinkedNode [val=" + val + ", next=" + next + ", previous=" + previous + "]";
        }

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        TreeNode n12 = new TreeNode(12);
        TreeNode n15 = new TreeNode(15);
        TreeNode n25 = new TreeNode(25);
        TreeNode n30 = new TreeNode(30);
        TreeNode n36 = new TreeNode(36);

        root.left = n12;
        root.right = n15;

        n12.left = n25;
        n12.right = n30;

        n15.right = n36;

        Solution s = new Solution();
        s.convert(root);

    }
}
