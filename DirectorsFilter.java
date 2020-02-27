import java.util.*;
public class DirectorsFilter implements Filter{
    private List<String> directors;

    public DirectorsFilter(List<String> directors){
        this.directors = directors;
    }

    @Override
    public boolean satisfies(String id) {
        for(String name : this.directors){
            if (MovieDatabase.getDirector(id).contains(name)){
                return true;
            }
        }
        return false;
    }
}
