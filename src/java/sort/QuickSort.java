package sort;

public class QuickSort
{
    public static void main( String[] args ) {
        int[] testArray = { 10, 12, 13, 14, 15, 10, 7, 8, 12, 13, 10 };
        sort( testArray );
    }

    public static void sort( int[] array ) {
        if( array == null ) {
            return;
        }

        if( array.length == 1 ) {
            return;
        }

        quickSort( array, 0, array.length - 1 );

    }

    private static void quickSort( int[] array, int leftIndex, int rightIndex ) {
        if( leftIndex == rightIndex ) {
            return;
        }

        if( (rightIndex - leftIndex) == 1 ) {
            if( array[rightIndex] < array[leftIndex] ) {
                exchange( array, leftIndex, rightIndex );
                return;
            }

            return;
        }

        int pivotIndex = leftIndex + (rightIndex - leftIndex) / 2;
        int pivot = array[pivotIndex];
        int i = leftIndex;
        int j = rightIndex;

        while( i <= j ) {

            while( array[i] > pivot ) {
                i++;
            }

            while( array[j] < pivot ) {
                j--;
            }

            if( i <= j ) {
                System.out.println( "i=" + i + " j=" + j );
                exchange( array, i, j );
                i++;
                j--;
            }

            for( int val : array ) {
                System.out.print( val + "," );
            }
            System.out.println( "--- pivotIndex=" + pivotIndex );
        }

        quickSort( array, leftIndex, j );
        quickSort( array, i, rightIndex );

    }

    private static void exchange( int[] array, int index1, int index2 ) {
        System.out.println( "exchange index " + index1 + " and " + index2 );
        System.out.println( "exchange " + array[index1] + " and " + array[index2] );
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
