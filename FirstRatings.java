import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {

    public ArrayList<Movie> loadMovies(String fileName){
        if(fileName == null){
            throw new NullPointerException();
        }

        if(fileName.isEmpty()){
            throw new IllegalArgumentException();
        }

        ArrayList<Movie> movies = new ArrayList<>();
        FileResource file = new FileResource(fileName);
        CSVParser parser = file.getCSVParser();

        for(CSVRecord record : parser){
            String movieId = record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String country = record.get("country");
            String genre = record.get("genre");
            String director = record.get("director");
            int minutes = Integer.parseInt(record.get("minutes"));
            String poster = record.get("poster");

            movies.add(new Movie(movieId, title, year, genre, director, country, poster, minutes));
        }
        return movies;
    }

    public void testLoadMovies(){
        String fileName = "data/ratedmovies_short.csv";
        List<Movie> movies = loadMovies(fileName);

        for(Movie movie : movies){
            System.out.println(movie);

            if(movie.getGenres().contains("Comedy")){
                System.out.println(movie);
            }

            if(movie.getMinutes() > 150){
                System.out.println(movie);
            }

            System.out.println(movie.getDirector());
        }
    }

    public ArrayList<Rater> loadRaters(String fileName){
        if(fileName == null){
            throw new NullPointerException();
        }

        if(fileName.isEmpty()){
            throw new IllegalArgumentException();
        }

        ArrayList<Rater> raters = new ArrayList<>();
        FileResource file = new FileResource(fileName);
        CSVParser parser = file.getCSVParser();
        Map<String, EfficientRater> raterMap = new HashMap<>();

        for(CSVRecord record : parser){
            String id = record.get("rater_id");
            String movieID = record.get("movie_id");
            double rating = Double.parseDouble(record.get("rating"));

            if(!raterMap.containsKey(id)){
                raterMap.put(id, new EfficientRater(id));
                raters.add(raterMap.get(id));
            }
            raterMap.get(id).addRating(movieID, rating);
        }

        return raters;
    }

    public void testLoadRaters(){
        String fileName = "data/ratings_short.csv";
        List<Rater> raters = loadRaters(fileName);
        List<String> ratingsToFind = new ArrayList<>();
        List<String> allMovies = new ArrayList<>();

        int maxRatings = 0;
        int countRat = 0;
        String idToFind = "2";
        String movieToFind = "1798709";

        System.out.println("Number of raters: " + raters.size());

        for (Rater rater: raters){
            System.out.println("Id: " + rater.getID()+ "\tNumber of ratings: " + rater.getItemsRated().size());

            if(rater.getItemsRated().size() > maxRatings){
                maxRatings = rater.getItemsRated().size();
            }
            if(rater.getID().equals(idToFind)){
                ratingsToFind = rater.getItemsRated();
            }

            for(String item : rater.getItemsRated()){
                if(!allMovies.contains(item)) {
                    allMovies.add(item);
                }
                if(item.equals(movieToFind)){
                    countRat++;
                }
                double rating = rater.getRating(item);
                System.out.println(item + " " + rating);
            }
        }

        System.out.println("\n" + ratingsToFind);
        System.out.println("\nMax Number of Ratings: " + maxRatings);
        System.out.println("\n"+movieToFind+": "+ countRat);
        System.out.println("\nAll Movies: "+ allMovies);

    }

    public static void main(String[] args) {
        FirstRatings t = new FirstRatings();
        t.testLoadRaters();
    }
}
