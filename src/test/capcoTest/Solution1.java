package capcoTest;

class Solution1
{
    int solution( int M, int[] A ) {
        int N = A.length;
        int[] count = new int[M + 1];
        for( int i = 0; i <= M; i++ )
            count[i] = 0;
        int maxOccurence = 1;
        int index = 0;
        for( int i = 0; i < N; i++ ) {
            if( count[A[i]] > 0 ) {
                int tmp = count[A[i]] + 1;
                if( tmp > maxOccurence ) {
                    maxOccurence = tmp;
                    index = i;
                }
                count[A[i]] = tmp;
            } else {
                count[A[i]] = 1;
            }
        }
        return A[index];
    }

    public static void main( String[] args ) {
        Solution1 s = new Solution1();
        int[] nums = { 2, 2, 2 };
        System.out.print( s.solution( 3, nums ) );
    }
}