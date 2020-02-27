import java.util.*;

public class MovieRunnerSimilarRatings {
    public String movieFile;
    public FourthRatings ratings;

    public MovieRunnerSimilarRatings(){
        this.movieFile = "ratedmoviesfull.csv";
        this.ratings = new FourthRatings();
        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize("ratings.csv");
    }

    public void printAverageRatings(){
        List<Rating> averageRatings = ratings.getAverageRatings(0);
        Collections.sort(averageRatings);

        System.out.println("Found " + averageRatings.size() + " ratings");
        for(Rating r : averageRatings){
            System.out.println(r.getValue() + "\t\t\t" + MovieDatabase.getTitle(r.getItem()));
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

    public void printSimiliarRatings(){
        List<Rating> list = ratings.getSimilarRatings("65", 20,5);

        for(Rating rating : list){
            System.out.println(MovieDatabase.getMovie(rating.getItem()));
        }
    }

    public void printSimilarRatingsByFilter(Filter filter){
        List<Rating> list = ratings.getSimilarRatingsByFilter("65", 10,5, filter);

        for(Rating rating : list){
            System.out.println(MovieDatabase.getMovie(rating.getItem()));
        }
    }

    public void printSimilarRatingsByGenre(){
        GenreFilter genre = new GenreFilter("Action");
        printSimilarRatingsByFilter(genre);
    }

    public void printSimilarRatingsByDirector(){
        ArrayList<String> list = new ArrayList<>();
        list.add("Clint Eastwood");
        list.add("Sydney Pollack");
        list.add("David Cronenberg");
        list.add("Oliver Stone");
        DirectorsFilter filter = new DirectorsFilter(list);
        printSimilarRatingsByFilter(filter);
    }

    public void printSimilarRatingsByGenreAndMinutes(){
        AllFilters f = new AllFilters();
        f.addFilter(new GenreFilter("Adventure"));
        f.addFilter(new MinutesFilter(100, 200));
        printSimilarRatingsByFilter(f);
    }

    public void printSimilarRatingsByYearAfterAndMinutes(){
        AllFilters f = new AllFilters();
        f.addFilter(new YearAfterFilter(2000));
        f.addFilter(new MinutesFilter(80, 100));
        printSimilarRatingsByFilter(f);
    }

    public static void main(String[] args) {
        MovieRunnerSimilarRatings t = new MovieRunnerSimilarRatings();
        t.printSimilarRatingsByYearAfterAndMinutes();
    }
}
