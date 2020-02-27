
/**
 * Write a description of SecondRatings here.
 *
 * @author Victor Pereira
 * @version 1.0
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }

    public SecondRatings(String movieFile, String ratingsFile){
        FirstRatings temp = new FirstRatings();
        this.myMovies = temp.loadMovies("data/" + movieFile);
        this.myRaters = temp.loadRaters("data/" + ratingsFile);
    }

    public int getRaterSize(){
        return this.myRaters.size();
    }

    public int getMovieSize(){
        return this.myMovies.size();
    }

    public String getTitle(String ID){
        for(Movie movie : myMovies){
            if(movie.getID().equals(ID)){
                return movie.getTitle();
            }
        }
        return "Non Exist";
    }

    public String getID(String title){
        for(Movie movie : myMovies){
            if(movie.getTitle().equals(title)){
                return movie.getID();
            }
        }
        return "NO SUCH TITLE";
    }

    private double getAverageByID(String id, int minimalRaters){
        int countRaters = 0;
        double sum = 0;

        for(Rater rater: myRaters){
            if(rater.getItemsRated().contains(id)){
                countRaters++;
                sum += rater.getRating(id);
            }
        }

        if(countRaters < minimalRaters){
            return 0.0;
        }
        return sum / countRaters;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> averageRatings = new ArrayList<>();
        for (Movie movie : myMovies){
            double rating = getAverageByID(movie.getID(), minimalRaters);
            if(rating > 0.0){
                averageRatings.add(new Rating(movie.getID(), rating));
            }
        }

        return averageRatings;
    }
}

