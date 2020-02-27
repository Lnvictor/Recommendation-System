import java.util.*;

public class MovieRunnerAverage {

    public void printAverageRatings(){
        SecondRatings ratings = new SecondRatings("ratedmovies_short.csv",
                "ratings_short.csv");

        System.out.println("Number of movies: " + ratings.getMovieSize());
        System.out.println("Number of raters: " + ratings.getRaterSize() + "\n");

        List<Rating> averageRatings = ratings.getAverageRatings(3);
        Collections.sort(averageRatings);

        for(Rating r : averageRatings){
            System.out.println(r.getValue() + "\t\t\t" + ratings.getTitle(r.getItem()));
        }
    }

    public void getAverageRatingOneMovie(){
        SecondRatings ratings = new SecondRatings("ratedmovies_short.csv",
                "ratings_short.csv");

        final String TITLE = "The Godfather";
        final String ID = ratings.getID(TITLE);

        List<Rating> averageRatings = ratings.getAverageRatings(0);
        Collections.sort(averageRatings);

        for(Rating rating : averageRatings){
            if(rating.getItem().equals(ID)){
                System.out.println(TITLE + " " + rating.getValue());
            }
        }
    }

    public static void main(String[] args) {
        MovieRunnerAverage runner = new MovieRunnerAverage();
        runner.printAverageRatings();
        System.out.println("\n");
        runner.getAverageRatingOneMovie();
    }
}
