package java.trie.test;

import java.util.Collection;

public class LowercaseTrieVocabulary implements Vocabulary
{
    private boolean isWord = false;
    // The number of possible children is the number of letters in the alphabet
    private LowercaseTrieVocabulary[] children = new LowercaseTrieVocabulary[Alphabet.LOWERCASE.size()];
    // This is the number of actual children
    private int numChildren = 0;

    public LowercaseTrieVocabulary() {}

    public LowercaseTrieVocabulary( Collection<String> words ) {
        for( String w : words ) {
            add( w );
        }
    }

    @Override
    public boolean add( String word ) {
        char first = word.charAt( 0 );
        int index = Alphabet.LOWERCASE.getIndex( first );
        if( index < 0 ) {
            System.out.println( "unacceptalbe word!" );
        }
        LowercaseTrieVocabulary child = children[index];
        if( child == null ) {
            // insert
            child = new LowercaseTrieVocabulary();
            children[index] = child;
            numChildren++;
            if( word.length() == 1 ) {
                isWord = true;
            }
            return true;
        } else {
            if( word.length() == 1 ) {
                return false;
            } else {
                return add( word.substring( 1 ) );
            }
        }
    }

    @Override
    public boolean isPrefix( String prefix ) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean contains( String word ) {
        char first = word.charAt( 0 );
        int index = Alphabet.LOWERCASE.getIndex( first );
        if( children[index] == null ) {
            return false;
        } else {
            return contains( word.substring( 1 ) );
        }
    }

    public String[] findAllWords( String prefix ) {

        return null;
    }

    public LowercaseTrieVocabulary getNode( String prefix ) {
        if( prefix == null ) {
            System.out.println( "invalid prefix" );
        }

        if( prefix.length() == 0 ) {
            return this;
        }

        char first = prefix.charAt( 0 );
        int index = Alphabet.LOWERCASE.getIndex( first );

        if( prefix.length() == 1 ) {
            if( children[index] == null ) {
                return null;
            } else {
                return children[index];
            }
        } else {
            return getNode( prefix.substring( 1 ) );
        }
    }

}
