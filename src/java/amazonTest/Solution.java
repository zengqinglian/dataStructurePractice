package amazonTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Solution
{
    public static class Movie
    {
        private final int movieId;
        private final float rating;
        private List<Movie> similarMovies; // Similarity is bidirectional

        public Movie( int movieId, float rating ) {
            this.movieId = movieId;
            this.rating = rating;
            similarMovies = new ArrayList<Movie>();
        }

        public int getId() {
            return movieId;
        }

        public float getRating() {
            return rating;
        }

        public void addSimilarMovie( Movie movie ) {
            similarMovies.add( movie );
            movie.similarMovies.add( this );
        }

        public List<Movie> getSimilarMovies() {
            return similarMovies;
        }
    }

    public static List<Movie> getMovieRecommendations( Movie movie, int numTopRatedSimilarMovies ) {
        List<Movie> resultList = new ArrayList<>( numTopRatedSimilarMovies );
        List<Movie> allRelatedMovies = new ArrayList<>();
        LinkedList<Movie> queue = new LinkedList<>();
        queue.add( movie );
        Movie current = null;
        while( !queue.isEmpty() ) {
            current = queue.pop();
            if( current != movie ) {
                allRelatedMovies.add( current );
            }
            for( Movie m : current.getSimilarMovies() ) {
                if( !allRelatedMovies.contains( m ) && !queue.contains( m ) ) {
                    queue.add( m );
                }
            }
        }

        Collections.sort( allRelatedMovies, new Comparator<Movie>() {

            public int compare( Movie o1, Movie o2 ) {
                if( o1.getRating() > o2.getRating() )
                    return -1;
                else if( o1.getRating() < o2.getRating() )
                    return 1;
                else
                    return 0;
            }
        } );

        int counter = Math.min( numTopRatedSimilarMovies, allRelatedMovies.size() );
        for( int i = 0; i < counter; i++ ) {
            resultList.add( allRelatedMovies.get( i ) );
        }
        return resultList;
    }

    private static void getSimilarMovies( Movie movie, List<Movie> allRelatedMovies ) {
        if( allRelatedMovies.containsAll( movie.getSimilarMovies() ) ) {
            return;
        }
        for( Movie m : movie.getSimilarMovies() ) {
            if( m != movie && !allRelatedMovies.contains( m ) ) {
                allRelatedMovies.add( m );
                getSimilarMovies( m, allRelatedMovies );
            }
        }
    }

    public static void main( String[] args ) {
        Movie move1 = new Movie( 1, 1.2f );
        Movie move2 = new Movie( 2, 3.6f );
        Movie move3 = new Movie( 3, 2.4f );
        Movie move4 = new Movie( 4, 4.8f );

        move1.addSimilarMovie( move2 );
        move1.addSimilarMovie( move3 );

        move2.addSimilarMovie( move4 );
        move3.addSimilarMovie( move4 );

        Solution.getMovieRecommendations( move1, 2 );

    }
}
