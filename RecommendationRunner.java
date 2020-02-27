/**
 *
 * This class comes ready...
 */

import java.util.*;

public class RecommendationRunner implements Recommender {
    @Override
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> items = new ArrayList<String>();
        Random myRandom = new Random();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(int i = 0; i < 10; i++) {
            int index = myRandom.nextInt(movies.size());
            String item = movies.get(index);
            if(items.contains(item)) {
                index = myRandom.nextInt(movies.size());
                item = movies.get(index);
                items.add(item);
            } else {
                items.add(item);
            }
        }
        return items;
    }

    @Override
    public void printRecommendationsFor(String webRaterID) {
        try {
            FourthRatings fr = new FourthRatings();
            ArrayList<Rating> ratings = fr.getSimilarRatings(webRaterID, 1, 1);
            if(ratings.size() == 0) {
                System.out.println("<h1>No movies recommended</h1>");
            } else {
                System.out.println("<h1>Top Movies Recommended for You</h1>");
                System.out.println("<table border='1' align='center'>");
                System.out.println("<tr>");
                System.out.println("<th>Title</th>");
                System.out.println("<th>Year</th>");
                System.out.println("<th>Runtime(mins)</th>");
                System.out.println("</tr>");
                for(int i = 0; i < 10; i++) {
                    String item = ratings.get(i).getItem();
                    String title = MovieDatabase.getTitle(item);
                    int year = MovieDatabase.getYear(item);
                    int minutes = MovieDatabase.getMinutes(item);
                    System.out.println("<tr>");
                    System.out.println("<td><b>" + title + "</b></td>");
                    System.out.println("<td>" + year + "</td>");
                    System.out.println("<td>" + minutes + "</td>");
                    System.out.println("</tr>");
                }
                System.out.println("</table>");
            }
        } catch(IndexOutOfBoundsException e) {

        }
    }
}
