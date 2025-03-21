package org.example;

public class Secondhighest {
    public static void main(String[] args) {
        int [] arr = {2,4,24,2,42,40};
        int max = arr[0];
        int secMax = arr[0];

        for(int i =0;i<arr.length;i++){
            if(arr[i]>max){
                secMax=max;
                max =arr[i];

            }else if(secMax<arr[i] && secMax!=max){
                secMax=arr[i];
            }
        }
        System.out.println(secMax);
        System.out.println(max);

    }
}
