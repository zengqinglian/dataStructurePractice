package somePractices;
public class Quine
{
    public static void main( String[] args ) {

        char q = 34;
        char e = 13;
        String s = "public class Quine{    public static void main( String[] args ) {        char q = 34;        char e = 13;        String s =       System.out.print( s.substring( 0, 19 ) + e + s.substring( 20, 66 ) + e + s.substring( 67, 86 ) + e + s.substring( 87, 106 )+ e + s.substring( 107, 124 ) + q + s + q + e + s.substring( 125 ) } }";
        System.out.print( s.substring( 0, 19 ) + e + s.substring( 20, 66 ) + e + s.substring( 67, 86 ) + e + s.substring( 87, 106 )
                + e + s.substring( 107, 124 ) + q + s + q + e + s.substring( 124 ) );
    }
}