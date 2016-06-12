package capcoTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution2
{
    public int solution( String S ) {
        String[] records = S.split( "[\\r\\n]+" );
        Map<String, Integer> callMap = new HashMap<>();
        List<String> phoneNumbers = new ArrayList<>();
        List<Integer> charges = new ArrayList<>();

        for( String r : records ) {
            String number = getPhoneNumber( r );
            int totalSec = getTotalSeconds( r );
            phoneNumbers.add( number );
            charges.add( getTotalCharges( totalSec ) );
            if( !callMap.containsKey( number ) ) {
                callMap.put( number, totalSec );
            } else {
                callMap.put( number, callMap.get( number ) + totalSec );
            }

        }

        // find promotion
        int maxCallTime = 0;
        String longestCallNumber = "";
        for( String number : callMap.keySet() ) {
            if( callMap.get( number ) > maxCallTime ) {
                longestCallNumber = number;
            } else if( callMap.get( number ) == maxCallTime ) {
                if( compareNumber( longestCallNumber, number ) > 0 ) {
                    longestCallNumber = number;
                }
            }
        }

        System.out.println( longestCallNumber );

        int total = 0;
        for( int i = 0; i < phoneNumbers.size(); i++ ) {
            if( !phoneNumbers.get( i ).equals( longestCallNumber ) ) {
                total += charges.get( i );
            }
        }

        return total;
    }

    private int compareNumber( String number1, String number2 ) {
        String[] num1 = number1.split( "-" );
        String[] num2 = number2.split( "-" );

        for( int i = 0; i < 3; i++ ) {
            if( Integer.valueOf( num1[i] ) > Integer.valueOf( num2[i] ) ) {
                return 1;
            }
        }

        return -1;

    }

    private int getTotalSeconds( String record ) {
        int index = record.indexOf( ',' );
        String time = record.substring( 0, index );

        return getTotalTime( time );
    }

    private int getTotalCharges( int sec ) {
        if( sec < 300 ) {
            return sec * 3;
        } else {
            int min = sec / 60;
            if( sec % 60 != 0 ) {
                min++;
            }
            return 150 * min;
        }

    }

    private int getTotalTime( String time ) {
        String[] timeArray = time.split( ":" );
        return Integer.valueOf( timeArray[0] ) * 3600 + Integer.valueOf( timeArray[1] ) * 60 + Integer.valueOf( timeArray[2] );

    }

    private String getPhoneNumber( String record ) {
        int index = record.indexOf( ',' );

        return record.substring( index + 1 );
    }

    public static void main( String[] args ) {
        Solution2 s = new Solution2();
        s.solution( "00:01:07,400-234-090\n00:05:01,701-080-080\n00:05:00,400-234-090" );
    }
}
