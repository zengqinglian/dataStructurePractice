package eric.zeng.trie.test2;

import java.util.List;

public class TrieTest
{
    public static void main( String[] args ) {
        Trie trieTest = new Trie();
        trieTest.addWord( "house" );
        trieTest.addWord( "home" );
        trieTest.addWord( "hot" );
        trieTest.addWord( "hit" );
        trieTest.addWord( "hat" );
        trieTest.addWord( "test" );
        trieTest.addWord( "teacher" );

        List<String> testCase1 = trieTest.getWords( "h" );
        System.out.println( "testCase1=" + testCase1 );

        List<String> testCase2 = trieTest.getWords( "hi" );
        System.out.println( "testCase2=" + testCase2 );

        List<String> testCase3 = trieTest.getWords( "ho" );
        System.out.println( "testCase3=" + testCase3 );

    }
}
