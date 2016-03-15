package eric.zeng.trie.test2;

import java.util.ArrayList;
import java.util.List;

public class Trie
{
    private TrieNode root;

    /**
     * Constructor
     */
    public Trie() {
        root = new TrieNode();
    }

    public void addWord( String word ) {
        root.addWord( word );
    }

    public List<String> getWords( String prefix ) {
        List<String> wordsList = new ArrayList<>();
        if( prefix == null || prefix.length() == 0 ) {
            return root.getWords();
        }

        int len = prefix.length();
        TrieNode currentNode = root;
        for( int i = 0; i < len; i++ ) {
            TrieNode next = currentNode.getNode( prefix.charAt( i ) );
            if( next == null ) {
                return wordsList;
            } else {
                currentNode = next;
            }
        }
        return currentNode.getWords();

    }
}
