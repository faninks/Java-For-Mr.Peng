package com.test;

// import java.io.*;
import java.util.*;

public class MainP1909 {
    public static void main(String args[]) throws Exception {
        Scanner cin=new Scanner(System.in);
        int min = -1, k;
        int[] n = new int[7];
        for(int i=0; i<7; ++i){
            n[i] = cin.nextInt();
        }
        for(int i=1; i<7; i+=2){
            k = n[0]/n[i];
            if(n[0]%n[i]!=0)    k++;
            k *= n[i+1];
            if(min==-1||k<min)  min = k;
        }
        System.out.println(min);
        cin.close();
    }
}
