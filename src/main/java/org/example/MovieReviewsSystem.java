package org.example;

import java.util.*;

public class MovieReviewsSystem {
    public static void main(String[] args) {
        MovieReviewsSystem movie = new MovieReviewsSystem();
        movie.Moviereview();
    }

    public void  Moviereview(){
        Scanner s = new Scanner(System.in);
        Map<String, List<Double>> mp = new HashMap<>();
        System.out.println("how many movies you want to rate ");
        int Nummovies = s.nextInt();
        s.nextLine();
        for(int i =0;i<Nummovies;i++){
            System.out.println("Enter the name of the movie");
            String movieName = s.nextLine();
            System.out.println("Enter the number of viewers");
            int numUsers = s.nextInt();
            List<Double> ratings = new ArrayList<>();
            for(int j =0 ;j<numUsers;j++){
                System.out.println("enter the rating for "+movieName+" 3you want to give User"+(j+1));
                double rating = s.nextDouble();
                ratings.add(rating);



            }
            s.nextLine();
            mp.put(movieName,ratings);


        }
        System.out.println("Avg rating of movies by user");
        for(Map.Entry<String,List<Double>> entry: mp.entrySet()){
            String movies= entry.getKey();
            List<Double> ratings = entry.getValue();
            double avg = ratings.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
            System.out.println("avg rating for "+movies+":"+avg);
        }
    }
}
