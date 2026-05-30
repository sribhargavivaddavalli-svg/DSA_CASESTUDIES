import java.util.*;

class Movie {
    String name;
    int views;
    Movie(String n,int v){ name=n; views=v; }
}

public class CO5_Sorting_MoviePopularity {

    public static void main(String[] args){

        ArrayList<Movie> list = new ArrayList<>();
        list.add(new Movie("Inception",9800));
        list.add(new Movie("Avatar",15000));
        list.add(new Movie("Interstellar",12000));

        Collections.sort(list,(a,b)->b.views-a.views);

        System.out.println("------ MOVIE POPULARITY SORTING ------");

        for(Movie m:list){
            System.out.println("Movie Name : "+m.name);
            System.out.println("Views      : "+m.views);
            System.out.println("--------------------------------------");
        }
    }
}