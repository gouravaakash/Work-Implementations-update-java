package org.example;

import java.util.*;

public class StudentGradeAveragebySubject {
    public static void main(String[] args) {
        StudentGradeAveragebySubject avg = new StudentGradeAveragebySubject();
        avg.AvgScore();
    }

    public void AvgScore(){
        Scanner s = new Scanner(System.in);
        Map<String, List<Integer>> mp = new HashMap<>();

        System.out.println("Enter the number of subjects");
        int numSub = s.nextInt();
        s.nextLine();
        for(int i =0;i<numSub;i++){
            System.out.println("Enter subject name:");
            String sub = s.nextLine();
            System.out.println("Enter number of students for "+sub);
            int numStudents = s.nextInt();
            List<Integer> scores = new ArrayList<>();
            for(int j =0;j<numStudents;j++){
                System.out.println("Enter the score of student"+(j+1));
                int score = s.nextInt();
                scores.add(score);

            }
            s.nextLine();
           mp.put(sub,scores);


        }
        System.out.println("average scores by subject");
        for(Map.Entry<String,List<Integer>> entry:mp.entrySet()){
            String sub = entry.getKey();
           List<Integer> scores = entry.getValue();
           double average = scores.stream().mapToInt(Integer::intValue).average().orElse(0.0);
            System.out.println(sub+" : "+average);
        }
        s.close();

    }
}
