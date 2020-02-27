import java.util.*;

public class MovieRunnerWithFilters {
    public String movieFile;
    public ThirdRatings ratings;

    public  MovieRunnerWithFilters(){
        this.movieFile = "ratedmovies_short.csv";
        this.ratings = new ThirdRatings("ratings_short.csv");
        System.out.println("Number of raters: " + ratings.getRaterSize() + "\n");
        MovieDatabase.initialize(movieFile);
        System.out.println("Number of movies: " + MovieDatabase.size() + "\n");
    }

    public void printAverageRatings(){
        List<Rating> averageRatings = ratings.getAverageRatings(0);
        Collections.sort(averageRatings);

        System.out.println("Found " + averageRatings.size() + " ratings");
        for(Rating r : averageRatings){
            System.out.println(r.getValue() + "\t\t\t" + MovieDatabase.getTitle(r.getItem()));
        }
    }

    public void printAverageRatingsByYear(){
        List<Rating> averageRatings = ratings.getAverageRatingsByFilter(new YearAfterFilter(2000), 0);
        Collections.sort(averageRatings);

        System.out.println("Found " + averageRatings.size() + " ratings");
        for(Rating r : averageRatings){
            System.out.println(r.getValue() + " " +MovieDatabase.getYear(r.getItem())+
                    " " + MovieDatabase.getTitle(r.getItem()));
        }
    }

    public void printAverageRatingsByGenre(){
        GenreFilter genreFilter = new GenreFilter("Crime");

        List<Rating> averageRatings = ratings.getAverageRatingsByFilter(genreFilter, 0);
        Collections.sort(averageRatings);
        System.out.println("Found " + averageRatings.size()+" movie(s)");
        for(Rating r : averageRatings){
            System.out.println(r.getValue() + " " +MovieDatabase.getGenres(r.getItem())+
                    "\n\t" + MovieDatabase.getTitle(r.getItem()));
        }
    }

    public void printAverageRatingsByMinutes(){
        MinutesFilter minutesFilter = new MinutesFilter(110, 170);

        List<Rating> averageRatings = ratings.getAverageRatingsByFilter(minutesFilter, 0);
        Collections.sort(averageRatings);
        System.out.println("Found " + averageRatings.size()+" movie(s)");

        for(Rating r : averageRatings){
            System.out.println(r.getValue() + " " + "Time: "+ MovieDatabase.getMinutes(r.getItem())+
                    " " + MovieDatabase.getTitle(r.getItem()));
        }
    }

    public void printAverageRatingsByDirectors(){
        List<String> names = new ArrayList<>();
        names.add("Charles Chaplin");
        names.add("Michael Mann");
        names.add("Spike Jonze");
        DirectorsFilter directorsFilter = new DirectorsFilter(names);

        List<Rating> averageRatings = ratings.getAverageRatingsByFilter(directorsFilter, 0);
        Collections.sort(averageRatings);
        System.out.println("Found " + averageRatings.size()+" movie(s)");

        for(Rating r : averageRatings){
            System.out.println(r.getValue() + " " + MovieDatabase.getDirector(r.getItem())+
                    "\n\t" + MovieDatabase.getTitle(r.getItem()));
        }
    }

    public void printAverageRatingsByYearAfterAndGenre(){
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new YearAfterFilter(1980));
        allFilters.addFilter(new GenreFilter("Romance"));

        List<Rating> averageRatings = ratings.getAverageRatingsByFilter(allFilters, 0);
        Collections.sort(averageRatings);

        System.out.println("Found " + averageRatings.size()+" movie(s)");

        for(Rating r : averageRatings){
            System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem())+" "+
                    MovieDatabase.getGenres(r.getItem())+
                    "\n\t" + MovieDatabase.getTitle(r.getItem()));
        }
    }

    public void printAverageRatingsByDirectorsAndMinutes(){
        List<String> directors = new ArrayList<>();
        directors.add("Spike Jonze");
        directors.add("Michael Mann");
        directors.add("Francis Ford Coppola");
        directors.add("Charles Chaplin");
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new DirectorsFilter(directors));
        allFilters.addFilter(new MinutesFilter(30, 170));

        List<Rating> averageRatings = ratings.getAverageRatingsByFilter(allFilters, 1);
        Collections.sort(averageRatings);

        System.out.println("Found " + averageRatings.size()+" movie(s)");

        for(Rating r : averageRatings){
            System.out.println(r.getValue() + " " + MovieDatabase.getDirector(r.getItem())+" "+
                    MovieDatabase.getMinutes(r.getItem())+
                    "\n\t" + MovieDatabase.getTitle(r.getItem()));
        }
    }

    public static void main(String[] args) {
        MovieRunnerWithFilters t = new MovieRunnerWithFilters();
        t.printAverageRatingsByDirectorsAndMinutes();
    }
}
