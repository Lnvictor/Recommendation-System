import java.util.ArrayList;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;

    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }

    public ThirdRatings( String ratingsFile){
        FirstRatings temp = new FirstRatings();
        this.myRaters = temp.loadRaters("data/" + ratingsFile);
    }

    public int getRaterSize(){
        return this.myRaters.size();
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
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());

        ArrayList<Rating> averageRatings = new ArrayList<>();
        for (String id : myMovies){
            double rating = getAverageByID(id, minimalRaters);
            if(rating > 0.0){
                averageRatings.add(new Rating(id, rating));
            }
        }

        return averageRatings;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(Filter filterCriteria, int minimalRaters){
        ArrayList<String> myMovies = MovieDatabase.filterBy(filterCriteria);

        ArrayList<Rating> averageRatings = new ArrayList<>();
        for (String id : myMovies){
            double rating = getAverageByID(id, minimalRaters);
            if(rating > 0.0){
                averageRatings.add(new Rating(id, rating));
            }
        }
        return averageRatings;
    }
}
