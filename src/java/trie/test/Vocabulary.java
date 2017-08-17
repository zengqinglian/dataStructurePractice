package java.trie.test;

public interface Vocabulary
{
    boolean add( String word );

    boolean isPrefix( String prefix );

    boolean contains( String word );
}
