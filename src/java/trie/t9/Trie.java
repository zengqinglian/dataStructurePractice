package trie.t9;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Trie {
    private Node root = new Node(false); // the root node

    // the t9 mapped array which maps number to string on the typing board
    private String[] t9 = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public void insert(String s){
        Node current = root;
        for (int i = 0; i < s.toCharArray().length; i++) {
            if (current.children[s.charAt(i) - 'a'] != null) {
                current = current.children[s.charAt(i) - 'a'];
            } else {
                Node node = new Node(false);
                current.children[s.charAt(i) - 'a'] = node;
                current = node;
            }
        }
        current.isWord = true;
    }
    
    public void delete(Node node) {

    }

    public List<String> search(String s) {
        List<String> words = new ArrayList<>();

        Queue<String> sQ = new LinkedList<>();
        Queue<Node> nQ = new LinkedList<>();
        sQ.add("");
        nQ.add(root);

        for (char c : s.toCharArray()) {
            int len = sQ.size();
            if (len == 0) {
                break;
            }
            String letters = t9[c - '0'];
            for (int i = 0; i < len; i++) {
                String preFix = sQ.poll();
                Node currentNode = nQ.poll();
                for (char ch : letters.toCharArray()) {
                    if (currentNode.children[ch - 'a'] != null) {
                        String currentString = preFix + String.valueOf(ch);
                        nQ.add(currentNode.children[ch - 'a']);
                        sQ.add(currentString);
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
        System.out.println("done!");
    }
}
