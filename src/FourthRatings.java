import java.lang.reflect.Array;
import java.util.*;

public class FourthRatings {
    private double getAverageByID(String id, int minimalRaters){
        int countRaters = 0;
        double sum = 0;

        for(Rater rater: RaterDatabase.getRaters()){
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

    private Double dotProduct(Rater me, Rater r){
        double dotProduct = 0.0;

        for(String meID: me.getItemsRated()){
            if(r.hasRating(meID)){
                double meValue = me.getRating(meID) - 5;
                double rValue = r.getRating(meID) - 5;

                dotProduct += (meValue * rValue);
            }
        }

        return dotProduct;
    }

    private ArrayList<Rating> getSimilarities(String id){
        Rater rater = RaterDatabase.getRater(id);
        ArrayList<Rating> similarities = new ArrayList<>();

        for (Rater r : RaterDatabase.getRaters()){
            if(!r.equals(rater)){
                Double dotProduct = dotProduct(rater, r);
                if(dotProduct > 0.0){
                    similarities.add(new Rating(r.getID(), dotProduct));
                }
            }
        }
        Collections.sort(similarities, Collections.reverseOrder());
        return similarities;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
       return getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, new TrueFilter());
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters,
                                                       int minimalRaters, Filter filterCriteria){

        List<Rating> similarRatings = getSimilarities(id);
        ArrayList<Rating> ret = new ArrayList<>();

        for(String movieID: MovieDatabase.filterBy(filterCriteria)){
            int countRaters = 0;
            double sumRatings = 0.0;

            for(int k = 0; k < numSimilarRaters; k++){
                Rating rating = similarRatings.get(k);
                Rater rater = RaterDatabase.getRater(rating.getItem());

                if(rater.hasRating(movieID)){
                    sumRatings += (rater.getRating(movieID) * rating.getValue());
                    countRaters++;
                }
            }
            if(countRaters >= minimalRaters){
                ret.add(new Rating(movieID, (sumRatings / (double) countRaters)));
            }
        }

        Collections.sort(ret, Collections.reverseOrder());
        return ret;
    }
}
