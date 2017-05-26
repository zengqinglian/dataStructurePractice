package trie.t9;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Trie {
    private Node root = new Node(false); // the root node

    // the t9 mapped array which maps number to string on the typing board
    private String[] t9 = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public void insert(String word) {
        Node current = root;
        for (int i = 0; i < word.toCharArray().length; i++) {
            if (current.children[word.charAt(i) - 'a'] != null) {
                current = current.children[word.charAt(i) - 'a'];
            } else {
                Node node = new Node(false);
                current.children[word.charAt(i) - 'a'] = node;
                current = node;
            }
        }
        current.isWord = true;
    }
    
    public void delete(String word) {
        if (word == null || word.isEmpty()) {
            return;
        }
        deleteHelper(word, root);
        System.out.println("deleted!");
    }

    private boolean deleteHelper(String word, Node node) {
        if (word.isEmpty()) {
            node.isWord = false;
            for (Node n : node.children) {
                if (n != null) {
                    return false;
                }
            }
            node = null;
            return true;
        } else {
            if (deleteHelper(word.substring(1), node.children[word.charAt(0) - 'a'])) {
                for (Node n : node.children) {
                    if (n != null) {
                        return false;
                    }
                }
                node.children[word.charAt(0) - 'a'] = null;
                return true;
            }
            return false;
        }
    }

    public List<String> search(String s) {
        List<String> words = new ArrayList<>();

        Queue<String> stringQueue = new LinkedList<>();
        Queue<Node> nodeQueue = new LinkedList<>();
        stringQueue.add("");
        nodeQueue.add(root);

        for (char c : s.toCharArray()) {
            int len = stringQueue.size();
            if (len == 0) {
                break;
            }
            String letters = t9[c - '0'];
            for (int i = 0; i < len; i++) {
                String preFix = stringQueue.poll();
                Node currentNode = nodeQueue.poll();
                for (char ch : letters.toCharArray()) {
                    if (currentNode.children[ch - 'a'] != null) {
                        String currentString = preFix + String.valueOf(ch);
                        nodeQueue.add(currentNode.children[ch - 'a']);
                        stringQueue.add(currentString);
                        if (currentNode.children[ch - 'a'].isWord) {
                            words.add(currentString);
                        }
                    }
                }
            }

        }

        return words;
    }
    
    private static class Node{
        private boolean isWord; 
        private Node[] children;

        public Node(boolean isWord){
            this.isWord=isWord;
            children = new Node[26];
        }
    }

    public static void main(String[] args) {
        Trie t = new Trie();
        // hell hello idea next supper
        t.insert("hell");
        t.insert("hello");
        t.insert("idea");
        t.insert("next");
        t.insert("supper");

        List<String> words = t.search("4355");
        words = t.search("3355");
        t.delete("supper");
        t.delete("hell");
        System.out.println("done!");
    }
}
