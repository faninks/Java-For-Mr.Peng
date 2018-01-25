package com.test;

//import java.io.*;
import java.util.*;

public class MainP1085 {
	
	public static void main(String args[]) throws Exception {
		Scanner cin = new Scanner(System.in);
		int[] a = new int[7];
		int max = 8, flag = 0;
		for(int i=0; i<7; ++i) { 
			a[i] = cin.nextInt();
			a[i] += cin.nextInt();
		}
		for(int i=0; i<7; ++i) {
			if(a[i]>max) {
				max = a[i];
				flag = i+1;
			}
		}
		System.out.println(flag);
	}
}
