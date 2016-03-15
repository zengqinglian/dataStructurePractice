package eric.zeng.trie.test2;

import java.util.ArrayList;
import java.util.List;

public class TrieNode
{
    private TrieNode parent;
    private TrieNode[] children;
    private boolean isLeaf; // Quick way to check if any children exist
    private boolean isWord; // Does this node represent the last character of a word
    private char character; // The character this node represents

    public TrieNode() {
        children = new TrieNode[26];
        isLeaf = true;
        isWord = false;
    }

    public TrieNode( char character ) {
        this();
        this.character = character;
    }

    public void addWord( String word ) {
        if( word == null ) {
            return;
        }

        this.isLeaf = false;

        char first = word.charAt( 0 );
        int charPos = first - 'a';

        if( children[charPos] == null ) {
            TrieNode newNode = new TrieNode( first );
            children[charPos] = newNode;
            newNode.parent = this;
        }

        if( word.length() == 1 ) {
            children[charPos].isWord = true;
        } else {
            children[charPos].addWord( word.substring( 1 ) );
        }
    }

    public TrieNode getNode( char c ) {
        return children[c - 'a'];
    }

    public List<String> getWords() {
        List<String> words = new ArrayList<>();
        if( this.isWord ) {
            words.add( toString() );
        }
        if( !this.isLeaf ) {
            for( TrieNode child : children ) {
                if( child != null ) {
                    words.addAll( child.getWords() );
                }
            }
        }
        return words;
    }

    public String toString() {
        if( this.parent == null ) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        sb.append( this.parent.toString() ).append( this.character );
        return sb.toString();
    }
}
